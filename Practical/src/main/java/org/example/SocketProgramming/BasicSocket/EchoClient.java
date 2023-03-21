package org.example.SocketProgramming.BasicSocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) {
        try
        {
            Socket socket = new Socket("localhost", 5555);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter a message: ");

            String message = bufferedReader.readLine();

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Message from server: "+bufferedReader.readLine());

            printWriter.println(message);

        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);
        }
    }
}
