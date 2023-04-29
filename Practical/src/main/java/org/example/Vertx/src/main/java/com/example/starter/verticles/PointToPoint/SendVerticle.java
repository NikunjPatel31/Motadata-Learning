package com.example.starter.verticles.PointToPoint;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class SendVerticle extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    var eventBus = vertx.eventBus();

    vertx.setPeriodic(1000, handler -> {
      eventBus.send("message.send.nikunj", "Hola... this is nikunj");
    });

    vertx.setPeriodic(5000, handler -> {
      vertx.undeploy(context.deploymentID())
        .onComplete(result -> {
          if (result.succeeded())
          {
            System.out.println(SendVerticle.class.getName()+" undeployed");
          }
          else
          {
            System.out.println("Undeployed failed: "+result.cause().getMessage());
          }
        });
    });

    startPromise.complete();
  }
}
