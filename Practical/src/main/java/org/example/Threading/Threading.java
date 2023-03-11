package org.example.Threading;

class CustomThread implements Runnable {
    Thread threadToInterrupt;
    @Override
    public void run() {
        try {

            for (int i = 0; i < 10; i++) {


                System.out.println(Thread.currentThread().getName()+" - "+i);

            }

        } catch (Exception exception) {

            System.out.println(Thread.currentThread().getName()+" - "+exception);

        }
//        try {
//            System.out.println("Inside the run method");
//            Thread.sleep(20000);
//        } catch (Exception exception) {
//            System.out.println(exception);
//        }
    }
}
public class Threading {
    public static void main(String[] args) throws InterruptedException {

        Thread t = Thread.currentThread();

        System.out.println(t);

        t.wait();

        CustomThread customThread = new CustomThread();

        Thread thread = new Thread(customThread);

        thread.start();

        Thread thread1 = new Thread(thread);

        thread1.start();

        Thread thread2 = new Thread(thread1);

        thread2.start();


//        Thread thread = new Thread(new CustomThread());
//        thread.start();
//        thread.interrupt();
//        thread.join();
////        thread.join();
//        Thread.sleep(2000);
    }
}
