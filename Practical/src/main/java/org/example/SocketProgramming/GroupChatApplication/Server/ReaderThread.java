package org.example.SocketProgramming.GroupChatApplication.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import static org.example.SocketProgramming.GroupChatApplication.Server.Server.socketMap;

public class ReaderThread extends Thread
{
    private Socket socket;
    ReaderThread(Socket socket)
    {
        this.socket = socket;

        System.out.println(socket.isClosed());
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {
                var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String[] messages = reader.readLine().split(":");

                sendMessage(messages[0], messages[1]);
            }

        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }

    private void sendMessage(String sender, String message)
    {
        for (Map.Entry<String, Socket> entry : socketMap.entrySet())
        {
            try
            {
                var printWriter = new PrintWriter(entry.getValue().getOutputStream());

                printWriter.println(sender+":"+message);

                printWriter.close();
            }
            catch (Exception exception)
            {
                System.out.println("Exception: "+exception);

                exception.printStackTrace();
            }
        }
    }
}
