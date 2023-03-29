package org.example.ZMQPractical.IPC;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Client2 {

    public static void main(String[] args) {

        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(ZMQ.REQ))
        {
            System.out.println(socket.connect("ipc://yash"));

            System.out.println("Sending message");

            socket.send("Hello i am connecting to yash");

            System.out.println("Server: "+new String(socket.recv()));
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }

    }

}
