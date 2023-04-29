package com.example.starter.verticles.TCP.NormalTCP;

import io.vertx.core.Vertx;

public class MainVerticle
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();

    vertx.deployVerticle(new Server()).onComplete(handler -> {

      if(handler.succeeded()) {
        System.out.println("pruthvi bc cmc fakir chand");

        System.out.println("pruthvina -400");

      } else {
        System.out.println("vedant chutiyo che");

      }
    });
  }
}
