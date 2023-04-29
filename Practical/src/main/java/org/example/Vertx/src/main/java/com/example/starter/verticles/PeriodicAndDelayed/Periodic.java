package com.example.starter.verticles.PeriodicAndDelayed;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class Periodic extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    long timerID = vertx.setPeriodic(3000, id -> {
      try
      {
        //Thread.sleep(3000);

        System.out.println("Periodic task executed");
      } catch (Exception e)
      {
        throw new RuntimeException(e);
      }
    });

//    vertx.setTimer(10000, handler -> {
//      vertx.undeploy(context.deploymentID())
//        .onComplete(result -> {
//          if (result.succeeded())
//          {
//            System.out.println("Undeployed");
//          }
//          else
//          {
//            System.out.println("Failed to undeployed: "+result.cause().getMessage());
//          }
//        }).onFailure(result -> {
//          System.out.println("FAILURE: "+result.getMessage());
//        });
//    });

    System.out.println("TimerID: "+timerID);

    startPromise.complete();
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception
  {
    System.out.println("Stop method of Periodic");

    stopPromise.complete();
  }
}
