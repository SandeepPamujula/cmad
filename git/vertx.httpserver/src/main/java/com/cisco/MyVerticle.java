package com.cisco;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

//public class MyVerticle extends AbstractVerticle{
//	
//	@Override
//	public void start(Future<Void> startFuture){
//		System.out.println("my vertex started");
//		
//	}
//	@Override
//	public void stop(Future stopFuture){
//		System.out.println("my vertex stoped..!!!!!");
//		
//	}
//	
//	public static void main(String[] args){
//		VertxOptions options = new VertxOptions().setWorkerPoolSize(10);
//		Vertx vertx = Vertx.vertx(options);
//		vertx.deployVerticle("com.cisco.MyVerticle");
//				
//	}
//	
//}

// ====================================================================

public class MyVerticle extends AbstractVerticle{
	
	@Override
	public void start(Future<Void> startFuture){
		System.out.println("my vertex started");
		
		vertx.eventBus().consumer("channel1", message -> {
            System.out.println("1 received message.body() = "
                + message.body());
        });
		
		
		
		startFuture.complete();
		
	}
	@Override
	public void stop(Future stopFuture){
		System.out.println("my vertex stoped..!!!!!");
		
	}
	
	public static void main(String[] args){
		VertxOptions options = new VertxOptions().setWorkerPoolSize(10);
		Vertx vertx = Vertx.vertx(options);
		vertx.deployVerticle("com.cisco.MyVerticle", message -> {
	        System.out.println("MyVerticle deployment complete");
	        vertx.deployVerticle(new ProducerVerticle());}
		);
				
	}
	
}