package org.example.ZMQPractical.RequestResponse;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Client {


    public static void main(String[] args) {

        ZContext context = null;

        ZMQ.Socket socket = null;

        try {
            context = new ZContext();

            socket = context.createSocket(SocketType.REQ);

            var isConnected = socket.connect("tcp://localhost:5555");

            if (isConnected) System.out.println("Connected");
            else System.out.println("Not Connected");

            var request = "Hello this is client";

            socket.send(request.getBytes(ZMQ.CHARSET), 0);

//            Thread.sleep(5000);

            byte[] reply = socket.recv(0);

            System.out.println("Received: "+ new String(reply, ZMQ.CHARSET));

        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
        finally {
            context.close();

            socket.close();
        }
    }

//    public static void main(String[] args) {
//
//        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.REP)) {
//
//            var isConnected = socket.connect("tcp://localhost:5555");
//
//            if (isConnected) System.out.println("Connected");
//            else System.out.println("Not Connected");
//
//            var request = "Hello this is client";
//
//            socket.send(request.getBytes(ZMQ.CHARSET), 0);
//
////            Thread.sleep(5000);
//
//            byte[] reply = socket.recv(0);
////
//            System.out.println("Received: " + new String(reply, ZMQ.CHARSET));
//
//        } catch (Exception exception) {
//            System.out.println("Exception: " + exception);
//
//            exception.printStackTrace();
//        }
//
//    }
}
