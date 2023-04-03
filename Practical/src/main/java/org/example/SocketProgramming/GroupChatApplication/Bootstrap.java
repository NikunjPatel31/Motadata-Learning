package org.example.SocketProgramming.GroupChatApplication;

import org.example.SocketProgramming.GroupChatApplication.Server.Server;

import java.io.IOException;

public class Bootstrap
{
    public static void main(String[] args) throws IOException
    {
        Server server = new Server();

        server.start();
    }
}
