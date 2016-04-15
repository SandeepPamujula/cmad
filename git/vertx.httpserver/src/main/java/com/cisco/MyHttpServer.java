package com.cisco;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
//import io.vertx.rxjava.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.BodyHandler;
//import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.StaticHandler;


class UserDetails {
	
	String uName;
	String passWord;
	
	public String getuName(){
		return uName;
		
	}
	public String getpassWord(){
		return uName;
		
	}
}

public class MyHttpServer extends AbstractVerticle{
	
	@Override
	public void start(Future<Void> startFuture){
		System.out.println("my vertex started");
		
		HttpServer server = vertx.createHttpServer();
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		
		router.post("/services/user").handler(rc->{
			
			
			String jsonStr = rc.getBodyAsString();
			
			ObjectMapper mapper = new ObjectMapper();
			UserDetails dto;
			try {
				dto = mapper.readValue(jsonStr, UserDetails.class);
				JsonNode node = mapper.valueToTree(dto);
				HttpServerResponse response = rc.response();
				response.end(node.toString()+"  response");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		});
		router.route().handler(StaticHandler.create()::handle);
		router.get("/services/users/:id").handler(new UserLoader());
	    server.requestHandler(router::accept).listen(8080);
	    
	    startFuture.complete();
		
		
		
		
		startFuture.complete();
		
	}
	@Override
	public void stop(Future stopFuture){
		System.out.println("my vertex stoped..!!!!!");
		
	}
	
	public static void main(String[] args){
		VertxOptions options = new VertxOptions().setWorkerPoolSize(10);
		Vertx vertx = Vertx.vertx(options);
		vertx.deployVerticle("com.cisco.MyHttpServer", message -> {
	        System.out.println("MyVerticle deployment complete");
	        vertx.deployVerticle(new ProducerVerticle());}
		);
				
	}
	
}

 

class UserLoader implements Handler<RoutingContext> {
	
	public void handle(RoutingContext routingContext) {
		String id = routingContext.request().getParam("id");
		HttpServerResponse response = routingContext.response();
		String msg = "hellow there...................";
//		response.setWriteQueueMaxSize(1000);
		response.putHeader("content-type", "application/json");
//		response.	
//		response.write(msg);
//		response.write("");
		response.end(msg);
	}
} 

