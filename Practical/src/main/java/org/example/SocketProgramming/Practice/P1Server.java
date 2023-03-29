package org.example.SocketProgramming.Practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class P1Server {
    public static void main(String[] args) {
        try
        {
            ServerSocket serverSocket = new ServerSocket(6666);

            Socket socket = serverSocket.accept();

            socket.setSoTimeout(30000);

            DataInputStream dataOutputStream = new DataInputStream(socket.getInputStream());

            for (int i = 0; i < 5; i++) {
                System.out.println(dataOutputStream.readUTF());
            }
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);
        }
    }
}
