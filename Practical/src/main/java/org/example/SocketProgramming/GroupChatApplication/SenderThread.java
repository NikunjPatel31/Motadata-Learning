package org.example.SocketProgramming.GroupChatApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SenderThread extends Thread
{
    Socket socket;

    SenderThread(Socket socket)
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
                var reader = new BufferedReader(new InputStreamReader(System.in));

                var printWriter = new PrintWriter(socket.getOutputStream());

                printWriter.println(reader.readLine());
            }
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
