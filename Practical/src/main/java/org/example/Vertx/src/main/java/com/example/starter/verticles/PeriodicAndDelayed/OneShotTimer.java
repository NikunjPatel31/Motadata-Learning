package com.example.starter.verticles.PeriodicAndDelayed;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class OneShotTimer extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    long timerID = vertx.setTimer(3000, id -> {
      System.out.println("3 seconds have passed");
    });

    System.out.println("timerID: "+timerID);

    timerID = vertx.setTimer(2000, id -> {
      System.out.println("2 seconds have passed");
    });

    System.out.println("timerID: "+timerID);
  }
}
