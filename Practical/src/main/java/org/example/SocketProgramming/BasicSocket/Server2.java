package org.example.SocketProgramming.BasicSocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        Socket socket = null;

        try
        {
            serverSocket = new ServerSocket();

            serverSocket.bind(new InetSocketAddress("localhost", 6001));

            socket = serverSocket.accept();

            if (socket.isConnected()) {
                System.out.println("It is connected successfully");
            } else {
                System.out.println("It is not connected");
            }

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
