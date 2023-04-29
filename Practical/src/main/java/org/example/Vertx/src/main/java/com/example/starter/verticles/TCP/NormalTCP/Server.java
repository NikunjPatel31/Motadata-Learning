package com.example.starter.verticles.TCP.NormalTCP;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.net.NetServer;

public class Server extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    NetServer server = vertx.createNetServer();

    System.out.println("baka");

    server.connectHandler(socket ->
    {
      System.out.println("check karie traineeo kewa che");

      socket.handler(h ->
      {
        System.out.println("traineeo sara che");
      });
    }).listen(0, "localhost", handler ->
    {
      if (handler.succeeded())
      {
        System.out.println("Server is now listening");

        startPromise.complete();
      }
      else
      {
        System.out.println("Faile to bind: "+handler.cause().getMessage());

        startPromise.fail("failed to bind");
      }
    });
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception
  {
    System.out.println("Stop called: "+getClass().getName());

    stopPromise.complete();
  }
}
