package org.example.ZMQPractical.HighWaterMark;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class HighWaterMarkServer {

    public static void main(String[] args) {

        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.PUB))
        {
            System.out.println(socket.bind("tcp://localhost:5555"));

            Thread.sleep(3000);
            for (int i = 0; i < 100; i++) {
                socket.send("Heyyyy"+i);
                //Thread.sleep(100);
            }

            Thread.sleep(5000);
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }

    }

}
