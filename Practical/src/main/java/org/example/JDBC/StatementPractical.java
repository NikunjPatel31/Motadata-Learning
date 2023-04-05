package org.example.JDBC;

import java.sql.Connection;
import java.sql.Statement;

public class StatementPractical
{
    public static void main(String[] args)
    {
        try
        {
            Connection connection = ConnectionClass.getConnection();

            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();

            String name = "Manas";

            int age = 16;

            String email = "manas@gmail.com";

            String insertQuery = "INSERT INTO demo VALUES(" +
                    "NULL, '"
                    + name +"',"
                    + age + ",'"
                    + email + "')";

            String select = "SELECT * FROM demo";

            System.out.println("Query Result: "+statement.execute(insertQuery));

            connection.commit();

        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
    }
}
