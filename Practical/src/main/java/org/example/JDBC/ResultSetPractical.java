package org.example.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ResultSetPractical
{
    public static void main(String[] args)
    {
        try
        {
            Connection connection = ConnectionClass.getConnection();

            Statement statement = connection.createStatement();

            String selectQuery = "SELECT * FROM demo";

            System.out.println("Query Result: "+statement.execute(selectQuery));

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next())
            {
                System.out.println("id: "+resultSet.getInt("id")+
                        ", Name: "+resultSet.getString("name"));

            }
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
