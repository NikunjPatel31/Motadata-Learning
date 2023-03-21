package org.example.Threading;

public class ThreadJoin extends Thread {

    ThreadJoin(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadJoin thread1 = new ThreadJoin("Thread1");

        ThreadJoin thread2 = new ThreadJoin("Thread2");

        ThreadJoin thread3 = new ThreadJoin("Thread3");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {

                int count = 0;
                while (true) {
                    System.out.println(" thread 2");
                    count++;

                    if (count >= 30) break;
                }
            }
        });

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    t2.join();
                    System.out.println("ahfkjdsanhfkjdsaf");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("I am Thread 1");
            }
        });

        Thread t3 = new Thread(thread3);

        t1.start();

        //thread1.join();

        t2.start();

//        t3.start();

        //  thread3.start();

        /*
         in the output we will clearly able to see that output order of thread1 is in proper order
         while output order of thread2 and thread3 are not. because join() is not called on them.
        */
        t1.join();
        System.out.println("Harsh kumar");
//        thread2.join();
//
//        thread3.join();

    }
}
