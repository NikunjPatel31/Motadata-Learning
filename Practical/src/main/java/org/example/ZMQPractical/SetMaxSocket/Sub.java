package org.example.ZMQPractical.SetMaxSocket;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Sub
{
    public static void main(String[] args) {

        try (ZContext context = new ZContext();
             ZMQ.Socket socket = context.createSocket(SocketType.SUB))
        {

            System.out.println("Max socket: "+context.getContext().setMaxSockets(1));

            socket.connect("tcp://localhost:6000");

            ZMQ.Socket socket1 = context.createSocket(SocketType.SUB);

            System.out.println("Socket: "+context.getSockets().size());

            socket.connect("tcp://localhost:7020");

            socket.connect("tcp://localhost:7000");

            System.out.println("waiting for response...");
            socket.subscribe("");

            Thread.sleep(5000);

            //socket.send("".getBytes());

            //System.out.println("Message sent");

            while (!Thread.currentThread().isInterrupted())
            {
                byte[] receivedBytes = socket.recv();

                System.out.println("Publisher: "+ new String(receivedBytes));
            }
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }

    }
}
