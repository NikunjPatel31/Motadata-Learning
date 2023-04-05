package org.example.ZMQPractical.InProc;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Sub
{
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.SUB))
        {
            socket.connect("inproc://mihir");

            socket.subscribe("");

            System.out.println("Received: "+new String(socket.recv()));
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
