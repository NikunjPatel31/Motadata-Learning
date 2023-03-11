package org.example.Threading;

public class ThreadGroup extends Thread {

    ThreadGroup(java.lang.ThreadGroup threadGroup, String name) {

        super(threadGroup,name);

    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {

            try {

                // Thread.sleep(1000);

                if (Thread.currentThread().getName().equals("Thread2")) {

                    Thread.currentThread().interrupt();

                }

            } catch (Exception exception) {

                System.out.println(Thread.currentThread().getName()+" | Exception caught: "+exception.getLocalizedMessage());

            }
        }

    }

    public static void main(String[] args) {

        java.lang.ThreadGroup threadGroup = new java.lang.ThreadGroup("Thread-group");

        java.lang.ThreadGroup childThreadGroup = new java.lang.ThreadGroup(threadGroup,"Child-Thread-Group");

        var thread1 = new ThreadGroup(threadGroup,"Thread1");

        var thread2 = new ThreadGroup(threadGroup, "Thread2");

        var thread3 = new ThreadGroup(threadGroup, "Thread3");

        System.out.println("Before start activeCount(): "+threadGroup.activeCount());

        System.out.println("Before start activeCountGroup(): "+threadGroup.activeGroupCount());

        thread1.start();

        thread2.start();

        thread3.start();

        System.out.println("After start activeCount(): "+threadGroup.activeCount());

        System.out.println("After start activeCountGroup(): "+threadGroup.activeGroupCount());

        Thread thread = new Thread();

    }

}
