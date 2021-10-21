package web;

import java.util.Map;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import services.MongoPage;
import services.MongoPost;

public class WebAPI {

	private int port = 4567;
	private MongoPage pages;
	private MongoPost posts;
	
	public WebAPI(MongoPage pages, MongoPost posts) throws IllegalArgumentException {
		if(pages==null) {
			throw new IllegalArgumentException("Error initializing DB service: Pages");
		}
		if(posts==null) {
			throw new IllegalArgumentException("Error initializing DB service: Posts");
		}
		
		this.pages = pages;
		this.posts = posts;
	}
	
	public void start() {
		Javalin app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
		}).start(this.port);
		
		app.get("/pages/{id}", findPage());
		app.get("/posts/latest", findLatestPosts());
		app.get("/byauthor/", byAuthor());
		app.get("/posts/author/{author}", postsByAuthor());
		app.get("/search/{text}", search());
		app.get("/posts/{id}", findPost());
		
	}
	
	private Handler findPage() {
		return ctx -> {
			String page = pages.find(ctx.pathParam("id"));
			
			ctx.html(page);
			
//			res.header("Access-ControlAllow-Origin", "*")	
		};
	}
	
	private Handler findLatestPosts() {
		return ctx -> {
			String posts = this.posts.findLatestPosts();
			
			ctx.html(posts)
;		};
	}
	
	private Handler byAuthor() {
		return ctx -> {
			String byAuthor = this.posts.byAuthor();
			
			ctx.html(byAuthor);
		};
	}
	
	private Handler postsByAuthor() {
		return ctx -> {
			String postsByAuthor = this.posts.postsByAuthor(ctx.pathParam("author"));
			
			ctx.html(postsByAuthor);
		};
	}
	
	private Handler search() {
		return ctx -> {
			String postsWithText = this.posts.search(ctx.pathParam("text"));
			
			ctx.html(postsWithText);
			
		};
	}
	
	private Handler findPost() {
		return ctx -> {
			String post = this.posts.find(ctx.pathParam("id"));
			
			ctx.html(post);
			
		};
	}
}
