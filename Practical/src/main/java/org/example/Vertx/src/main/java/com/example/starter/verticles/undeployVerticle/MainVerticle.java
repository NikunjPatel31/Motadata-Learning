package com.example.starter.verticles.undeployVerticle;

import io.vertx.core.Vertx;

public class MainVerticle
{
  public static void main(String[] args)
  {
    Vertx.vertx().deployVerticle(SimpleVerticle.class.getName(), handler -> {
      System.out.println("Verticle deployed");
      if (handler.succeeded())
      {
        System.out.println("Handler registered... "+handler.result());
      }
      else
      {
        System.out.println("Deployment failed: "+handler.cause().getMessage());
      }
    });
  }
}
