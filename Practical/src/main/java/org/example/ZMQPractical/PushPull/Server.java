package org.example.ZMQPractical.PushPull;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
    bind() and connect() both will work fine with client side
    Client will only be able to receive messages.
    When client will try to send message, program will throw "UnsupportedOperationException"
 */

public class Server {
    public static void main(String[] args) {
        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(ZMQ.PUSH)) {

            socket.bind("tcp://localhost:6000");

            socket.send("Heyy this is server");

            //System.out.println("Received: " + new String(socket.recv()));

            Thread.sleep(5000);

        } catch (Exception exception) {
            System.out.println("Exception: " + exception);

            exception.printStackTrace();
        }
    }
}
