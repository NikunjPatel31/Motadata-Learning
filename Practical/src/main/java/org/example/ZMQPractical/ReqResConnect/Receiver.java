package org.example.ZMQPractical.ReqResConnect;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Receiver
{
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.REP))
        {
            socket.bind("tcp://localhost:5555");

            System.out.println("Sender: "+new String(socket.recv()));

            System.out.println("Sending message...!");

            socket.send("Message received...");
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
