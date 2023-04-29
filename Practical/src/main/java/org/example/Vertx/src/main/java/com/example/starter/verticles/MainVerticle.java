package com.example.starter.verticles;

import com.example.starter.verticles.PeriodicAndDelayed.OneShotTimer;
import com.example.starter.verticles.PeriodicAndDelayed.Periodic;
import com.example.starter.verticles.Practicals.VerticleA;
import com.example.starter.verticles.Practicals.WorkerVerticleA;
import io.vertx.core.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MainVerticle extends AbstractVerticle
{
  private static final Logger LOG = LoggerFactory.getLogger(MainVerticle.class);

  public static void main(String[] args)
  {
    var v = Vertx.vertx();

    v.deployVerticle(new MainVerticle());

//    v.close();
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
//    vertx.deployVerticle(OneShotTimer.class.getName());

    vertx.deployVerticle(Periodic.class.getName(), new DeploymentOptions().setWorker(false));

//    vertx.deployVerticle(WorkerVerticleA.class.getName(),
//      new DeploymentOptions()
//        .setWorker(true)
//        .setInstances(1)
//    );

    // here we are adding verticalA as a child
//    vertx.deployVerticle(new VerticleA(), new DeploymentOptions())
//      .onComplete(res ->
//      {
//        if (res.succeeded())
//        {
//          System.out.println("Deployment completed: " + res.result());
//        } else
//        {
//          System.out.println("Deployment failed: " + res.cause().getMessage());
//        }
//      });
//    vertx.deployVerticle(new VerticleB());
//    vertx.deployVerticle(new FileCreationVerticles(), whenDeployed -> {
//      vertx.undeploy(whenDeployed.result());
//    });
    startPromise.complete();
  }
}
