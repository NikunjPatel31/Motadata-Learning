package org.example.ZMQPractical.ChatApplication;

import org.zeromq.ZMQ;

public class ReaderThread extends Thread
{
    ZMQ.Socket socket;

    ReaderThread(ZMQ.Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                System.out.println("Mes: " + new String(socket.recv()));
            }
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
