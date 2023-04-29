package com.example.starter.verticles.Practicals;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.file.FileSystem;

public class FileCreationVerticles extends AbstractVerticle
{
  @Override
  public void start(Promise<Void> startPromise) throws Exception
  {
    FileSystem fs = vertx.fileSystem();

    Future<Void> future = fs
      .createFile("verticleFile.txt")
      .compose(v -> fs.createFile("verticleFile.txt"))
      .onComplete(event -> {
        System.out.println("we are inside onComplete1");
      })
      .onComplete(event -> {
        System.out.println("We are inside onComplete2");
      })
      .onComplete(event -> {
        System.out.println("We are inside onComplete3");
      });
  }
}
