package com.example.starter.verticles.Practicals;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerVerticleA extends AbstractVerticle
{
  private static final Logger LOG = LoggerFactory.getLogger(WorkerVerticleA.class);
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    LOG.debug("start {} "+getClass().getName());

    System.out.println("This is nikunj patel");

    Context context =  vertx.getOrCreateContext();

    System.out.println("Context count: "+context.getInstanceCount());



    System.out.println("isWorker: "+context.isWorkerContext());

    startPromise.complete();
  }
}
