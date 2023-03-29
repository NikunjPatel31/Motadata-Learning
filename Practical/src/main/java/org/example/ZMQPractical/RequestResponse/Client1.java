package org.example.ZMQPractical.RequestResponse;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.util.List;

public class Client1 {
    public static void main(String[] args) {

        List<Student> list = null;

        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(SocketType.REP))
        {
            System.out.println("Connect: "+socket.bind("tcp://localhost:5555"));

            System.out.println("Listening...");

            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(socket.recv()));

            list = (List<Student>) objectInputStream.readObject();

            FileWriter fileWriter = new FileWriter("output.txt");

            System.out.println("Size: "+list.size());

            for (Student student : list) {
                fileWriter.write(String.valueOf(student)+"\n");
            }

            System.out.println("completed");

        }
               catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }

    }
}