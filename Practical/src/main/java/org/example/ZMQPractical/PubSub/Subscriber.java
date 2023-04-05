package org.example.ZMQPractical.PubSub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.StringTokenizer;

public class Subscriber {

    public static void main(String[] args) {

        try (ZContext context = new ZContext();
             ZMQ.Socket socket = context.createSocket(SocketType.SUB))
        {
            socket.bind("tcp://localhost:6000");

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

//    public static void main(String[] args)
//    {
//
//        try (ZContext context = new ZContext(); ZMQ.Socket subscriber = context.createSocket(SocketType.SUB)) {
//
//            if (subscriber.connect("tcp://10.20.40.158:9999")) {
//
//                System.out.println("Subscriber connected to port 9999");
//
//            } else {
//
//                System.out.println("Subscriber connection failed");
//
//                System.exit(0);
//            }
//
//            subscriber.subscribe("btc");
//
//            subscriber.subscribe("sol");
//
//            while (!Thread.currentThread().isInterrupted()) {
//                String message = subscriber.recvStr(0);
//
//                StringTokenizer tokenizer = new StringTokenizer(message, " ");
//
//                String currency = tokenizer.nextToken();
//
//                long price = Long.parseLong(tokenizer.nextToken());
//
//                System.out.println(currency + " " + price);
//            }
//
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }

}
