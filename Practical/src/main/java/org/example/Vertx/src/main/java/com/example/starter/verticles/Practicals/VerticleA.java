package com.example.starter.verticles.Practicals;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

import java.util.UUID;

public class VerticleA extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    System.out.println("Start "+getClass().getName());
//    vertx.deployVerticle(new VerticleAA(), whenDeployed -> {
//
//      // second parameter is asynchronous callable which will be executed when
//      // verticle deployment is complete.
//      System.out.println("deployed "+VerticleAA.class.getName());
//
//      // undeploy() needs ID which will be inside whenDeployed
//      vertx.undeploy(whenDeployed.result());
//    });
//    vertx.deployVerticle(new VerticleAB());
//    vertx.deployVerticle(VerticleN.class.getName(),
//      new DeploymentOptions()
//          .setInstances(4)
//          .setConfig(new JsonObject()
//            .put("id", UUID.randomUUID().toString())
//            .put("name", VerticleN.class.getSimpleName())));
    startPromise.complete();
  }
}
