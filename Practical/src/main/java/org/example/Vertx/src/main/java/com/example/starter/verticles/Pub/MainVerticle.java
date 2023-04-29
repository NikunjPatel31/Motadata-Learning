package com.example.starter.verticles.Pub;

import io.vertx.core.Vertx;

public class MainVerticle
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();

    vertx.deployVerticle(Consumer.class.getName(), whenDeployed -> {
      System.out.println(whenDeployed.result());
    });

    vertx.deployVerticle(PublisherVerticle.class.getName());
  }
}
