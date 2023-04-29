package com.example.starter.verticles.Pub;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.MessageProducer;

/**
 * if there is no handler for the address, then message will not be send.
 */

public class PublisherVerticle extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    var eventBus = vertx.eventBus();

    System.out.println("Sending message");

    MessageProducer<String> producer = eventBus.publisher("message.nikunj");

//    producer.write("This is nikunj patel")
//      .onComplete(result -> {
//        if (result.succeeded())
//        {
//          System.out.println("message is send successfully: This is nikunj patel");
//        }
//        else {
//          System.out.println("Error in sending message: "+result.cause());
//        }
//      }).onFailure(result -> {
//        System.out.println("FAILURE: "+result.getMessage());
//      });
//
//    vertx.setTimer(1, handler -> {
//      System.out.println("i am here");
//      producer.write("Nikunj patel",h->{
//          if (h.succeeded())
//          {
//            System.out.println("Message send...");
//          }
//          else
//          {
//            h.cause().printStackTrace();
//          }
//        });
//    });

    vertx.setPeriodic(1000, handler ->{
      eventBus.publish("message.nikunj", "Hey! this is nikunj patel");
    });

    startPromise.complete();
  }
}
