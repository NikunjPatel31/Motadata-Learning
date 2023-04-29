package org.example.ZMQPractical.RequestResponse;

import org.zeromq.SocketType;
import org.zeromq.ZContext;

public class Server {
    public static void main(String[] args) {
        try
        {
            var context = new ZContext();

            var socket = context.createSocket(SocketType.REP);

            var isBind = socket.bind("tcp://*:5555");

            if (isBind) System.out.println("Bind successful");
            else System.out.println("Bind not successful");

            byte[] receivedByte = socket.recv();

            System.out.println("Received: "+new String(receivedByte));

            socket.send("Hey client, your message is received.");

            socket.send("Heelo");

            socket.send("hellllo");

//            while (!Thread.currentThread().isInterrupted())
//            {
//                byte[] receivedByte = socket.recv(0);
//
//                System.out.println("Received Message: "+new String(receivedByte));
//            }

        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        try
//        {
//            var context = new ZContext();
//
//            var socket = context.createSocket(SocketType.REQ);
//
//            var isBind = socket.bind("tcp://*:5555");
//
//            if (isBind) System.out.println("Bind successful");
//            else System.out.println("Bind not successful");
//
//            byte[] receivedByte = socket.recv();
//
//            System.out.println("Received: "+new String(receivedByte));
//
//            socket.send("Hey client, your message is received.");
//
////            while (!Thread.currentThread().isInterrupted())
////            {
////                byte[] receivedByte = socket.recv(0);
////
////                System.out.println("Received Message: "+new String(receivedByte));
////            }
//
//        }
//        catch (Exception exception)
//        {
//            System.out.println("Exception: "+exception);
//
//            exception.printStackTrace();
//        }
//    }
}
