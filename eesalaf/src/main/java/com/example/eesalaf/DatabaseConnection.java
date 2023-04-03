package com.example.eesalaf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
public class DatabaseConnection {
    public  static Connection databaselink;

    public static Connection getConnection(){
        String databaseName = "esalaf";
        String databaseUser = "root";
        String databasePassword = "Marouan.2002";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaselink = DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return databaselink;
    }
}
