package com.sicdlib.util.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection conn){
        try {
            if (rs!=null) {//如果返回的结果集对象不能为空,就关闭连接
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (pstmt!=null) {
                pstmt.close();//关闭预编译对象
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (conn!=null) {
                conn.close();//关闭结果集对象
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
