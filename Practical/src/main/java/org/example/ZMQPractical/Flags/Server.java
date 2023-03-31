package org.example.ZMQPractical.Flags;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Server {

    public static void main(String[] args) {

        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.PUB))
        {
            System.out.println("Connected: "+socket.bind("tcp://localhost:5555"));

            Thread.sleep(3000);

            while (!Thread.currentThread().isInterrupted())
            {
                socket.send("heey");
            }

            System.out.println("Message sent");
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }

    }

}
