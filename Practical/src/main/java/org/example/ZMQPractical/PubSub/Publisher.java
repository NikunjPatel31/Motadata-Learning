package org.example.ZMQPractical.PubSub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Publisher {

    public static void main(String[] args) {

        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.PUB))
        {
            socket.bind("tcp://*:6000");

            Thread.sleep(3000);

            for (int i = 0; i < 10; i++)
            {
                socket.send(("hey dude..."+i));

                socket.send("Nikunj");
                //System.out.println("Message sent");
                Thread.sleep(1000);
            }

            System.out.println("All Message sent");

//            Thread.sleep(5000);

        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }

    }

}
