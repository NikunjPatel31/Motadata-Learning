package org.example.JDBC;

import java.sql.*;

public class FirstPractical {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Connection connection = null;

        Statement statement = null;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            Class.forName("com.clickhouse.jdbc.ClickHouseDriver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "");

            if (connection.isClosed())
            {
                System.out.println("Connection not established");
            }
            else
            {
                System.out.println("Connection is established");
            }

            statement = connection.createStatement();

            String query = "CREATE TABLE demo (" +
                            " name varchar(20));";

            statement.execute(query);

        }
        catch (Exception exception)
        {
            System.out.println("Exception: "+exception);

            exception.printStackTrace();
        }
        finally
        {
            connection.close();

            statement.close();
        }

    }

}
