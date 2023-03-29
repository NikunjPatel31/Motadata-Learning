package org.example.SocketProgramming.ChatApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WriteMessage extends Thread
{
    private Socket socket;

    private String user;

    public WriteMessage(Socket socket, String user) {
        this.socket = socket;
        this.user = user;
    }

    @Override
    public void run()
    {
        PrintWriter printWriter;

        BufferedReader bufferedReader;

        while (true)
        {
            try
            {
                bufferedReader = new BufferedReader(new InputStreamReader(System.in));

                String message = bufferedReader.readLine();

                printWriter = new PrintWriter(socket.getOutputStream(), true);

                printWriter.println(message);

                if (message != null)
                {
                    if (message.equals("quit")) {
                        System.exit(0);
                    }
                }

            }
            catch (Exception exception)
            {
                System.out.println("Exception: "+exception);
            }
        }
    }
}
