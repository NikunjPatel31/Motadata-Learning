package org.example.SocketProgramming;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicSocket {
    public static void main(String[] args) throws IOException {

        System.out.println("Waiting for client...");

        ServerSocket serverSocket = new ServerSocket(5555);

        Socket socket = serverSocket.accept();

        System.out.println("Connection established successfully");
    }
}
