package org.example.SocketProgramming.BasicSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        try {
            socket = new Socket();

            socket.connect(new InetSocketAddress("localhost", 5555));


//            socket.bind(new InetSocketAddress(5559));

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter a message: ");

            String message = bufferedReader.readLine();

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("we are inside ");

            System.out.println(socket.getPort());

            //bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // System.out.println("Message from server: "+bufferedReader.readLine());

            printWriter.println(message);

        }
         finally {
            socket.close();

//            if (socket.isClosed())
//                System.out.println("Closed");
//            else
//                System.out.println("Not closed");
//
//            if (socket.isBound())
//                System.out.println("Bounded");
//            else
//                System.out.println("Not Bounded");





        }
    }
}
