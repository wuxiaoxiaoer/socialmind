package com.sicdlib.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    public Connection GetConnection(){
        Connection conn = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.1.249:3306/socialmind";
        String user = "root";
        String password = "root";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
