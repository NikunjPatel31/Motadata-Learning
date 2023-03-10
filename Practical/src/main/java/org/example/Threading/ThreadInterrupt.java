package org.example.Threading;

public class ThreadInterrupt extends Thread {

    @Override
    public void run() {

        try {

            interrupt();

            System.out.println("Interrupt status: "+isInterrupted());

            interrupted();

            System.out.println("Interrupted status: "+isInterrupted());

            interrupted();

            System.out.println("Interrupted status: "+isInterrupted());


        } catch (Exception exception) {

            System.out.println("Exception: "+exception);

        }

    }

    public static void main(String[] args) {

        ThreadInterrupt thread1 = new ThreadInterrupt();

        thread1.start();

        System.out.println("Main method completed execution");

    }

}
