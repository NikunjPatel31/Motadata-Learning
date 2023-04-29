package com.example.starter.verticles.RequestReply;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.DeliveryOptions;

public class Sender extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    var eventBus = vertx.eventBus();

    vertx.setPeriodic(1000, h -> {
      eventBus.request("message.req.nikunj", "Hola receiver...")
        .onComplete(handler -> {
          if (handler.succeeded())
          {
            System.out.println("Reply: "+handler.result().body());
          }
          else
          {
            System.out.println("Handler was unable to send request: "+handler.cause().getMessage());
          }
        });
    });
  }
}
