package org.example.SocketProgramming.ChatApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadMessage extends Thread
{
    private Socket socket;

    private String user;

    public ReadMessage(Socket socket, String user) {
        this.socket = socket;
        this.user = user;
    }

    @Override
    public void run()
    {
        BufferedReader bufferedReader;
        try
        {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true)
            {
                try {

                    String message = bufferedReader.readLine();

                    if (message.equals("quit")) System.exit(0);

                    System.out.println((user.equals(Constant.CLIENT) ? Constant.SERVER : Constant.CLIENT )+" - "+message);
                }
                catch (Exception exception)
                {
                    System.out.println("Exception in WriteMessage - run: " + exception);
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
}
