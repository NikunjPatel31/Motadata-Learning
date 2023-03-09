package org.example.Threading;

public class ThreadYield extends Thread
{

    @Override
    public void run()
    {

        System.out.println(Thread.currentThread().getName()+"Run method started");

    }

    public static void main(String[] args)
    {

    }

}
