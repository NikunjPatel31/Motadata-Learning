package org.example.SocketProgramming.Practice;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ZMQClient {

    private static void startClient()
    {
        try
        {
            Socket socket = new Socket("localhost", 6666);

            DataOutputStream d = new DataOutputStream(socket.getOutputStream());
            d.writeUTF("Hello");
            d.flush();
            d.close();
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);
        }
    }

    private static void startServer()
    {
        try
        {
            ServerSocket ss = new ServerSocket(6666);

            Socket socket = ss.accept();

            System.out.println("Client is connected");
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);
        }
    }
    public static void main(String[] args) {
        ZMQClient.startServer();
        ZMQClient.startClient();
    }
}
