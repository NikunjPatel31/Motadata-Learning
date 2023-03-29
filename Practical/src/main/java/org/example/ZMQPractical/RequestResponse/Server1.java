package org.example.ZMQPractical.RequestResponse;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Student implements Serializable {
    int id, age;
    String name;

    public Student() {}

    public Student(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{id: "+id+", name: "+name+", age: "+age+"}";
    }
}

public class Server1 {
    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();

        List<String> l = new ArrayList<>();
        {

            for (int i = 0; i < 100;i++)
            {
                for (int j = 0; j < 100; j++)
                {
                    for (int k = 0; k < 10; k++)
                    {
                        for (int x = 0; x < 10; x++)
                        {
                            l.add(new String("Hello there...!"+i+j+k+x));
                        }
                    }
                }
            }
//            for (int i = 0; i < 10;i++)
//            {
//                list.add(new Student(i, i+1, "Nikunj"));
////                for (int j = 0; j < 100;j++)
////                {
////                    for (int k = 0; k < 100;k++)
////                    {
////                        for (int x = 0; x < 10; x++)
////                        {
////                            list.add(new Student(i, i+1, "Nikunj"));
////                        }
////                    }
////                }
//            }

            list.add(new Student(1, 22, "Nikunj"));

//            l.add(new String("hello"));

        }

        try (ZContext context = new ZContext(); ZMQ.Socket socket = context.createSocket(ZMQ.REQ))
        {
            System.out.println(socket.connect("tcp://10.20.40.158:5555"));

            System.out.println("Sending...");

            //socket.send("heey");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            ObjectOutputStream oos = new ObjectOutputStream(baos);

            oos.writeObject(l);



            socket.send(baos.toByteArray());

            Thread.sleep(4000);

            System.out.println("Message sent...");
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
