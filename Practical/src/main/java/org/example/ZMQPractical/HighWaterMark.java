package org.example.ZMQPractical;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class HighWaterMark {

    public static void main(String[] args) {

        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.PULL))
        {
            System.out.println(socket.connect("tcp://10.20.40.158:5555"));;

            while (!Thread.currentThread().isInterrupted())
            {
                System.out.println("Received: "+ socket.recvStr(0));

                Thread.sleep(40000);
            }
        }
         catch (Exception exception)
         {
             System.out.println("Exception: "+exception);

             exception.printStackTrace();
         }

    }

}
