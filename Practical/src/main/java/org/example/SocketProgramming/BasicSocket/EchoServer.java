package org.example.SocketProgramming.BasicSocket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {

        System.out.println("Waiting for client...");

        ServerSocket serverSocket = new ServerSocket();

        serverSocket.bind(new InetSocketAddress(5555));

        Socket socket = serverSocket.accept();

        System.out.println(socket.isConnected());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println("Message from client: "+bufferedReader.readLine());

        System.out.println("Connection established successfully");
    }
}
