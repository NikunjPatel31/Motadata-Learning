package org.example.SocketProgramming.Practice1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args) throws IOException
    {

        try (ServerSocket serverSocket = new ServerSocket(6000); Socket socket = serverSocket.accept(); BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {

            String message = reader.readLine();

            System.out.println("Client: " + message);
        } catch (Exception exception)
        {
            System.out.println("Exception: " + exception);

            exception.printStackTrace();
        }
    }
}
