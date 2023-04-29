package com.example.starter.verticles.PeriodicAndDelayed;

import io.vertx.core.Future;
import io.vertx.core.Vertx;

public class MainVerticle
{
  public static void main(String[] args) throws InterruptedException
  {
    var vertx = Vertx.vertx();

    Future<String> stringFuture = vertx.deployVerticle(Periodic.class.getName());

    Thread.sleep(10000);

    vertx.undeploy(stringFuture.result());
  }
}
