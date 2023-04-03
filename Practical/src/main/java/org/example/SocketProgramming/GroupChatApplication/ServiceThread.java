package org.example.SocketProgramming.GroupChatApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServiceThread extends Thread
{
    Socket socket;
    ServiceThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {

        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
