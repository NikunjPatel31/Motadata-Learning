package org.example.Threading;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

class ExecutorTask extends Thread {

    long timeOut;

    Runnable task;

    String name;

    ExecutorTask(Runnable task, long timeOut, String name)
    {
        this.task = task;

        this.timeOut = timeOut;

        this.name = name;
    }

    @Override
    public void run()
    {
        long startTime = System.currentTimeMillis();

        Thread thread = new Thread(task, name);

        thread.setDaemon(true);

        thread.start();

        while (true)
        {
            if (!thread.isAlive()) {
                break;
            }
            if((startTime + timeOut) < System.currentTimeMillis())
            {
                System.out.println(name+" - Time out");
                break;
            }
        }
    }
}

public class TimeOutThreadTask extends Thread
{
    public static void main(String[] args) throws InterruptedException
    {
        ExecutorTask thread1 =  new ExecutorTask(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(4000);

                    System.out.println("Nikunj Thread execution completed");
                } catch (Exception exception)
                {
                }
            }
        }, 2000, "Nikunj");

        try
        {
            thread1.start();
        }
        catch (Exception exception)
        {
            System.out.println(exception);
        }

        new ExecutorTask(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(50000);

                    System.out.println(Thread.currentThread().getName()+" - Thread execution completed");
                } catch (Exception exception)
                {
                }
            }
        }, 12000, "Harsh").start();

    }

}
