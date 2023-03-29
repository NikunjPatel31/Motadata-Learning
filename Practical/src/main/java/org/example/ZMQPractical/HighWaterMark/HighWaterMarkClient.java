package org.example.ZMQPractical.HighWaterMark;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class HighWaterMarkClient {

    public static void main(String[] args) {

        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.SUB))
        {
            System.out.println(socket.connect("tcp://localhost:5555"));

            socket.subscribe("H");

            socket.setRcvHWM(1);

            //Thread.sleep(2000);

            while (!Thread.currentThread().isInterrupted())
            {
                System.out.println("entered...");
                System.out.println("Received: "+ new String(socket.recv()));

                Thread.sleep(10000);
            }
        }
         catch (Exception exception)
         {
             System.out.println("Exception: "+exception);

             exception.printStackTrace();
         }

    }

}
