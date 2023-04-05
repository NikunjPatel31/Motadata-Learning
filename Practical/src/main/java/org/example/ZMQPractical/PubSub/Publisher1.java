package org.example.ZMQPractical.PubSub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Publisher1 {

    public static void main(String[] args) {

        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.PUB))
        {
            socket.bind("tcp://*:7000");

            socket.connect("tcp://*:6000");

            Thread.sleep(3000);

            for (int i = 0; i < 10; i++)
            {
                socket.send(("hey dude...Mihirrr"+i));

                socket.send("Mihirrr");
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
