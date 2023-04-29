package com.example.starter.verticles.undeployVerticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class SimpleVerticle extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    vertx.setTimer(5000, handler -> {
      vertx.undeploy(context.deploymentID()).onComplete(result -> {
        if (result.succeeded())
        {
          System.out.println("Successed in undeploy: "+context.deploymentID());
        }
        else
        {
          System.out.println("Failed: "+result.cause().getMessage()+" : "+context.deploymentID());
        }
      });
    });

    startPromise.complete();
  }
}
