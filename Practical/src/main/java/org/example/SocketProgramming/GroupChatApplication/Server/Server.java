package org.example.SocketProgramming.GroupChatApplication.Server;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class Server
{
    public static HashMap<String, Socket> socketMap = new HashMap<>();

    private boolean insertUser(String userName, Socket socket) throws Exception
    {
        if (socketMap.containsKey(userName))
        {
            return false;
        }

        socketMap.put(userName, socket);

        return true;
    }

    public void start() throws IOException
    {
        ServerSocket serverSocket = null;

        Socket socket = null;

        try
        {
            serverSocket = new ServerSocket(6000);

            while (true)
            {
                socket = serverSocket.accept();
//
                var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String username = reader.readLine();

                if (insertUser(username, socket))
                {
                    // ack all for new user
                    sendMessage("All", username+" entered the chat");

                    System.out.println("inserted");
                }
                else
                {
                    // send acknowledgement
                    var printWriter = new PrintWriter(socket.getOutputStream());

                    printWriter.println("User name already exists");

                    socket.close();
                }

                var readerThread = new ReaderThread(socket);

                readerThread.start();

                readerThread.join();
            }

        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
        finally
        {
            serverSocket.close();
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

            }
            catch (Exception exception)
            {
                System.out.println("Exception: "+exception);

                exception.printStackTrace();
            }
        }
    }
}
