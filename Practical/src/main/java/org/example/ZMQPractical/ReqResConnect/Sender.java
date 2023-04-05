package org.example.ZMQPractical.ReqResConnect;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Sender
{
    public static void main(String[] args)
    {
        try(ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.REQ))
        {
            socket.bind("tcp://localhost:5555");

            socket.send("This is Nikunj Patel");

            System.out.println("Message sent...!");

            System.out.println("Receiver: "+new String(socket.recv()));
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
