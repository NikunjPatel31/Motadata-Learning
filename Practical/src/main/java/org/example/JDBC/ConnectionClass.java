package org.example.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass
{
    public static Connection getConnection()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "");

            return connection;
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }

        return null;
    }
}
