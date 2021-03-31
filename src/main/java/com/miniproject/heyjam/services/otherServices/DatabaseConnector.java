package com.miniproject.heyjam.services.otherServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector
{
    public static Connection getConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/heyjam";
        String user="####";
        String pass="####";
        Connection connection= DriverManager.getConnection(url,user,pass);
        return connection;
    }
}