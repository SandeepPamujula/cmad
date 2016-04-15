package com.cisco;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class ProducerVerticle extends AbstractVerticle {
	
	@Override
	public void start(Future<Void> startFuture){
		System.out.println("ConsumerVerticle started");
		
		vertx.eventBus().publish("channel1","message1");
		vertx.eventBus().publish("channel1","message2");
		
	}
	@Override
	public void stop(Future stopFuture){
		System.out.println("my vertex stoped..!!!!!");
		
	}
		

}
