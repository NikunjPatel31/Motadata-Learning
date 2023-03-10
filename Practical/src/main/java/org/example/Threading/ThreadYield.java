package org.example.Threading;

public class ThreadYield extends Thread {

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName()+" Run method started");

        for (int i = 0; i < 5; i++) {

            System.out.println(Thread.currentThread().getName()+" - "+i);

        }

    }

    public static void main(String[] args) {

        ThreadYield thread  = new ThreadYield();

        thread.setPriority(10);

        thread.start();

        Thread.yield();

        System.out.println("Main method is yield");

        for (int i = 0; i < 100; i++) {

            System.out.println(Thread.currentThread().getName()+" - "+i);
        }

    }

}
