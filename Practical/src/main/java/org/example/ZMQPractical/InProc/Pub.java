package org.example.ZMQPractical.InProc;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Pub
{
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext(0); ZMQ.Socket socket = context.createSocket(SocketType.PUB))
        {
            socket.bind("inproc://mihir");

            ZMQ.Socket subSocket = context.createSocket(SocketType.SUB);

            subSocket.connect("inproc://mihir");

            subSocket.subscribe("");

            Thread.sleep(4000);

            socket.send("This is Dhaval bera...");

            System.out.println("Message sent...");

            System.out.println("Received: "+new String(subSocket.recv()));
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
