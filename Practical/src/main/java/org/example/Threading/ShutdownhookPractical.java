package org.example.Threading;

public class ShutdownhookPractical extends Thread {

    @Override
    public void run() {

        System.out.println("We are inside the run method and shutdownhook is getting execute");

    }

    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new ShutdownhookPractical());

        System.out.println("Before shutdownhook");

        System.out.println("Press ctrl+c to terminate program before 10 seconds");

        try {

            Thread.sleep(10000);

        } catch (Exception exception) {

            System.out.println("Exception: "+exception);

        }

        System.out.println("Program terminated");

    }

}
