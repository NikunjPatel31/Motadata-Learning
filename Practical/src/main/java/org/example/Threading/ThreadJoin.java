package org.example.Threading;

public class ThreadJoin extends Thread {

    ThreadJoin(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+" - "+i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadJoin thread1 = new ThreadJoin("Thread1");

        ThreadJoin thread2 = new ThreadJoin("Thread2");

        ThreadJoin thread3 = new ThreadJoin("Thread3");

        thread1.start();

        //thread1.join();

        thread2.start();

        thread3.start();

        /*
         in the output we will clearly able to see that output order of thread1 is in proper order
         while output order of thread2 and thread3 are not. because join() is not called on them.
        */


    }
}
