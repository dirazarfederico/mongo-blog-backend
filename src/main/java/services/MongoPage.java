package services;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoPage {
	
	String uri;
	
	public MongoPage(String uri) throws IllegalArgumentException {
		
		if(uri==null || uri.isEmpty()) {
			throw new IllegalArgumentException("You must provide a database url");
		}
		this.uri = uri;
	}
	
	public String find(String id) {
		
		String result = null;
		
		try(MongoClient mongoClient = MongoClients.create(uri)) {
			
			MongoDatabase database = mongoClient.getDatabase(IMongoConstants.DATABASE);
			
			MongoCollection<Document> collection = database.getCollection(IMongoConstants.PAGES);
			
			FindIterable<Document> d = collection.find(Filters.eq("_id", new ObjectId(id)));
			
			result = StreamSupport.stream(d.spliterator(), false).map(Document::toJson).collect(Collectors.joining(", ", "[", "]"));
		}
		
		return result;
	}
	
	public void insertPage(String title, String text, String author) {
		try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
			MongoDatabase database = mongoClient.getDatabase(IMongoConstants.DATABASE);
			MongoCollection<Document> collection = database.getCollection(IMongoConstants.PAGES);
			Document document = new Document("title", title).append("text", text)
			.append("author", author)
			.append("date", new Date());
			collection.insertOne(document);
		}
	}
	
}
