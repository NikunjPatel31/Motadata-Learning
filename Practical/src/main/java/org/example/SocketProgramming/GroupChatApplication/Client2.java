package org.example.SocketProgramming.GroupChatApplication;

import java.net.Socket;

public class Client2
{
    public static void main(String[] args)
    {
        Socket socket = null;

        try
        {
            socket = new Socket("localhost", 6000);

            var reader = new ReaderThread(socket);

            var sender = new SenderThread(socket);

            reader.start();

            sender.start();

            reader.join();

            sender.join();
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
