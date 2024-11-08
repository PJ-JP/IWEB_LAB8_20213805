package com.example.iweb_lab8.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoBase {
    public Connection getConnection() throws SQLException{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        String user = "root";
        String password = "Lookmedfg321*";
        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
        return DriverManager.getConnection(url, user, password);
    }
}
