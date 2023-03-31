package org.example.ZMQPractical.Flags;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Client {

    public static void main(String[] args) {

        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.SUB))
        {
            System.out.println("Connected: "+socket.connect("tcp://localhost:5555"));

            socket.subscribe("");

            while (!Thread.currentThread().isInterrupted())
            {
                System.out.println("Received: "+new String(socket.recv()));
            }

            System.out.println("Receiving completed...");
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }

    }

}
