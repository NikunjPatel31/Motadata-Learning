package com.example.starter.verticles.RequestReply;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.DeliveryOptions;

public class Receiver extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    var eventBus = vertx.eventBus();

    eventBus.consumer("message.req.nikunj", handler -> {
      System.out.println("Message: "+handler.body());

      handler.reply("Hola sender... message received");
    });

    Buffer buff = Buffer.buffer(128);
   int pos = 15;
    buff.setUnsignedByte(19, (short) 200);
    System.out.println(buff.getUnsignedByte(pos));

    startPromise.complete();
  }
}
