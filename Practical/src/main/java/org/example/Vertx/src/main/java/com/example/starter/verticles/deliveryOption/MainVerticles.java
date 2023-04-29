package com.example.starter.verticles.deliveryOption;
import io.vertx.core.Vertx;

public class MainVerticles
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();

    vertx.deployVerticle(SendVerticle.class.getName());

    vertx.deployVerticle(Consumer.class.getName());

  }
}
