package org.example.ZMQPractical.PushPull;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;

/**
 bind() and connect() both will work fine with client side
 Client will only be able to receive messages.
 When client will try to send message, program will throw "UnsupportedOperationException"
 */

public class Client {
    public static void main(String[] args) {

        try (ZContext context = new ZContext(); Socket socket = context.createSocket(ZMQ.PULL))
        {
            var isConnected = socket.connect("tcp://10.20.40.158:9999");

//            var isConnected = socket.bind("tcp://localhost:6000");

            if (isConnected) System.out.println("Client connected");
            else System.out.println("Client not connected");

            System.out.println("Server: "+new String(socket.recv()));

            System.out.println("This will execute....");

//            while (!Thread.currentThread().isInterrupted())
//            {
//                System.out.println("Server: "+new String(socket.recv()));
//                Thread.sleep(10000);
//            }

            socket.send("This is Nikunj Patel");


        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
