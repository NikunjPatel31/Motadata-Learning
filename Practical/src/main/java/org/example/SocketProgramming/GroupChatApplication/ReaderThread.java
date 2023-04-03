package org.example.SocketProgramming.GroupChatApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReaderThread extends Thread
{
    Socket socket;

    ReaderThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.println(reader.readLine());
            }
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
