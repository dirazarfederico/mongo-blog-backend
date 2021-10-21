package services;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.TextSearchOptions;

import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Projections.*;

public class MongoPost {
	String uri;
	
	public MongoPost(String uri) throws IllegalArgumentException {
		
		if(uri==null || uri.isEmpty()) {
			throw new IllegalArgumentException("You must provide a database url");
		}
		this.uri = uri;
	}
	
	public String findLatestPosts() {
		
		String result = null;
		
		try(MongoClient mongoClient = MongoClients.create(uri)) {
			
			MongoDatabase database = mongoClient.getDatabase(IMongoConstants.DATABASE);
			
			MongoCollection<Document> collection = database.getCollection(IMongoConstants.POSTS);
	
			Bson project, sort, limit;
			
			project = project(fields(include("_id", "title", "resume")));
			
			sort = sort(descending("date"));
			
			limit = limit(4);
			
			AggregateIterable<Document> d = collection.aggregate(asList(project, sort, limit));
			
//			FindIterable<Document> d = collection.find().sort(descending("date")).projection(project(fields(include("_id", "title", "resume")))).limit(4);
			
			result = StreamSupport.stream(d.spliterator(), false).map(Document::toJson).collect(Collectors.joining(", ", "[", "]"));
		}
		
		return result;
		
	}
	
	public void insertPost(String title, String resume, String text, List<String> tags, List<String> relatedLinks, String author) {
		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
			MongoDatabase database = mongoClient.getDatabase(IMongoConstants.DATABASE);
			MongoCollection<Document> collection = database.getCollection(IMongoConstants.POSTS);
			Document document = new Document("title", title).append("resume", resume)
			.append("text", text)
			.append("tags", tags)
			.append("relatedLinks", relatedLinks)
			.append("author", author)
			.append("date", new Date());
			collection.insertOne(document);
		}
	}
	
	public String byAuthor() {
		String result = null;
		
		try(MongoClient mongoClient = MongoClients.create(uri)) {
			
			MongoDatabase database = mongoClient.getDatabase(IMongoConstants.DATABASE);
			
			MongoCollection<Document> collection = database.getCollection(IMongoConstants.POSTS);
			
			AggregateIterable<Document> d = collection.aggregate(asList(sortByCount("$author")));
			
			result = StreamSupport.stream(d.spliterator(), false).map(Document::toJson).collect(Collectors.joining(", ", "[", "]"));
		}
		
		return result;
	}
	
	public String postsByAuthor(String author) {
		
		String result = null;
		
		try(MongoClient mongoClient = MongoClients.create(uri)) {
			
			MongoDatabase database = mongoClient.getDatabase(IMongoConstants.DATABASE);
			
			MongoCollection<Document> collection = database.getCollection(IMongoConstants.POSTS);
			
			FindIterable<Document> d = collection.find(Filters.eq("author", author));
			
			result = StreamSupport.stream(d.spliterator(), false).map(Document::toJson).collect(Collectors.joining(", ", "[", "]"));
		}
		
		return result;
	}
	
	public String search(String text) {
		
		String result = null;

		try(MongoClient mongoClient = MongoClients.create(uri)) {
			
			MongoDatabase database = mongoClient.getDatabase(IMongoConstants.DATABASE);
			
			MongoCollection<Document> collection = database.getCollection(IMongoConstants.POSTS);
			
			FindIterable<Document> d = collection.find(Filters.text("\"" + text + "\"", new TextSearchOptions().caseSensitive(false)));
			
			result = StreamSupport.stream(d.spliterator(), false).map(Document::toJson).collect(Collectors.joining(", ", "[", "]"));
		}
		
		return result;
	}
	
	public String find(String id) {

		String result = null;
		
		try(MongoClient mongoClient = MongoClients.create(uri)) {
			
			MongoDatabase database = mongoClient.getDatabase(IMongoConstants.DATABASE);
			
			MongoCollection<Document> collection = database.getCollection(IMongoConstants.POSTS);
			
			FindIterable<Document> d = collection.find(Filters.eq("_id", new ObjectId(id)));
			
			result = StreamSupport.stream(d.spliterator(), false).map(Document::toJson).collect(Collectors.joining(", ", "[", "]"));
		}
		
		return result;
	}
}
