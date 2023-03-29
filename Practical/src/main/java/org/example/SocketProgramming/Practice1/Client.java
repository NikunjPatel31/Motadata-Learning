package org.example.SocketProgramming.Practice1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = null;

        BufferedReader reader = null;

        try
        {
            socket = new Socket("localhost", 6000);

            reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter a message to sent: ");

            String message = reader.readLine();

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

            printWriter.println(message);

            System.out.println("Message sent successfully...!");
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
        finally
        {
            if (socket != null)
            {
                socket.close();
            }
        }
    }
}
