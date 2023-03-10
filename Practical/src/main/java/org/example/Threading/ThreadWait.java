package org.example.Threading;

class Yash extends Thread {
    @Override
    public void run() {

        synchronized (this) {
            for (int i = 1; i < 11; i++) {

                System.out.println(Thread.currentThread().getName() + " | Total: yash ");


            }
        }

    }
}

public class ThreadWait extends Thread {

    static int total;

    public static synchronized void method() {
            for (int i = 1; i < 11; i++) {

                System.out.println(Thread.currentThread().getName() + " | Total: " + total);

                total += 100;

            }
    }

    @Override
    public void run() {

        method();
//        System.out.println("Outside");
//
//        for (int i = 0; i < 100; i++) {
//            System.out.println(i);
//        }

//        synchronized (this) {
//
//            for (int i = 1; i < 11; i++) {
//
//                System.out.println(Thread.currentThread().getName()+" | Total: "+total);
//
//                total += 100;
//
//            }
//
//        }

    }

    public static void main(String[] args) throws InterruptedException {

        ThreadWait thread = new ThreadWait();

        ThreadWait thread1 = new ThreadWait();

        Yash yash = new Yash();

//        yash.start();


        thread.start();

        thread1.start();

        //Thread.sleep(2000);

        //Thread.sleep(2000);

        //System.out.println("Total final value: "+thread.total);

        synchronized (thread) {

            Thread.sleep(2000);

            System.out.println("Total final value: " + thread.total);

        }

    }

}