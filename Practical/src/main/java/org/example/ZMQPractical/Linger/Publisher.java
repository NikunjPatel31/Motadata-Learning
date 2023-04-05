package org.example.ZMQPractical.Linger;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Publisher
{
    public static void main(String[] args)
    {
        try(ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(ZMQ.PUB))
        {

        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
