package org.example.ZMQPractical.IPC;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Server {
    public static void main(String[] args) {

        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.REP))
        {
            socket.bind("ipc://nikunj");

            socket.bind("ipc://yash");

            System.out.println("Listening");

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Client: "+new String(socket.recv()));

                System.out.println("Sending message to client");

                socket.send("Message received");
            }
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }

    }
}