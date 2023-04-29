package com.example.starter.verticles.PointToPoint;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class Consumer extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    var eventBus = vertx.eventBus();

    eventBus.consumer("message.send.nikunj", handler -> {
      System.out.println("Message: "+handler.body());
    });

    startPromise.complete();
  }
}
