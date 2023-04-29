package org.example.SocketProgramming.ChatApplication;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(Constant.PORT_NUMBER);

        while (true)
        {
            System.out.println(Thread.currentThread().getThreadGroup());
        }

//        Socket socket = serverSocket.accept();
//
//        System.out.println("We are ready for the communication");
//
//        try
//        {
//
//            ReadMessage readMessage = new ReadMessage(socket, Constant.SERVER);
//
//            WriteMessage writeMessage = new WriteMessage(socket, Constant.SERVER);
//
//            readMessage.start();
//
//            writeMessage.start();
//
//            readMessage.join();
//
//            writeMessage.join();
//
//        }
//        catch (Exception exception)
//        {
//            System.out.println("Exception: "+exception);
//        }
//        finally
//        {
//            socket.close();
//        }
    }
}
