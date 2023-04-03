package org.example.ZMQPractical.ChatApplication;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Client
{
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.REP))
        {
            socket.connect("tcp://localhost:6000");

            var reader = new ReaderThread(socket);

            var sender = new SenderThread(socket);

            reader.start();

            sender.start();

            reader.join();

            sender.join();

        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
