package com.example.starter.verticles.Practicals;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class VerticleN extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    System.out.println("Start "+getClass().getName()+" with config "+config().toString()+" on Thread "+Thread.currentThread().getName());

    startPromise.complete();
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception
  {
    System.out.println("Stop "+getClass().getName());
  }
}
