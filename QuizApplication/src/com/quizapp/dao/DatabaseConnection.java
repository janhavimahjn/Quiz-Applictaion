package com.quizapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
     private static final String URL = "jdbc:mysql://localhost:3306/quizzz";
     private static final String USER = "root";
     private static final String PASSWORD = "****";
     
     public static Connection getConnection() throws SQLException {
    	 return DriverManager.getConnection(URL, USER, PASSWORD);
     }
}
