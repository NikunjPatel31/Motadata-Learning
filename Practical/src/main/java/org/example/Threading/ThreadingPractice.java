package org.example.Threading;

class MyThread extends Thread {

    MyThread(String name) {

        super(name);

    }
    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {

            System.out.println(Thread.currentThread().getName()+" - "+i);

        }

    }

}

class MyThreadRunn implements Runnable {
    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {

            System.out.println(Thread.currentThread().getName()+" - "+i);

        }

    }

}

public class ThreadingPractice extends Thread {

    @Override
    public synchronized void start() {
        System.out.println("Yash");
    }

    @Override
    public void run() {
        System.out.println("Nikunj");
    }

    public static void main(String[] args) {

        ThreadingPractice thread = new ThreadingPractice();
        Thread thread24 = new Thread(thread);
        thread24.start();

        Thread thread1 = new Thread(new MyThreadRunn(), "Thread1");

        Thread thread2 = new Thread(new MyThreadRunn(), "Thread2");

        thread1.start();

        thread2.start();

//        MyThread thread1 = new MyThread("Thread1");

//        MyThread thread2 = new MyThread("Thread2");
//
//        thread1.start();

//        thread2.start();
    }
}
