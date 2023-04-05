package org.example.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PreparedStatementPractical
{
    public static void main(String[] args)
    {
        try
        {
            Connection connection = ConnectionClass.getConnection();

            String name = "Dhaval";

            int age = 28;

            String email = "Dhaval@gmail.com";

            String insertQuery = "INSERT INTO demo VALUES (NULL, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setInt(2, age);

            preparedStatement.setString(1, name);

            preparedStatement.setString(3, email);

            System.out.println("Rows: "+preparedStatement.executeUpdate());
        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
