package org.example.ZMQPractical.ChatApplication;

import org.zeromq.ZMQ;
import org.zeromq.ZSocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SenderThread extends Thread
{
    ZMQ.Socket socket;

    SenderThread(ZMQ.Socket socket)
    {
        this.socket = socket;
    }
    public void run()
    {
        try
        {
            var reader = new BufferedReader(new InputStreamReader(System.in));
            while (true)
            {
                socket.send(reader.readLine());
            }
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
