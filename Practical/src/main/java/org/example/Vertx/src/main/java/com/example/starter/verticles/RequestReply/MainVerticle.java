package com.example.starter.verticles.RequestReply;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class MainVerticle
{
  public static void main(String[] args)
  {
    var vertx = Vertx.vertx();

    vertx.deployVerticle(Receiver.class.getName(), new DeploymentOptions().setWorker(true));

    vertx.deployVerticle(Sender.class.getName());
  }
}
