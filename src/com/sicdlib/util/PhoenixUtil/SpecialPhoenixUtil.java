package com.sicdlib.util.PhoenixUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpecialPhoenixUtil {
    PhoenixUtil phoenixUtil;

    //获取一列的平均值，注意值都是String类型，需要转成int，最后再改成String返回
    public String getAverage(String tableName, String columnName) {
        phoenixUtil = new PhoenixUtil();
        Connection conn = null;
        String result = "";
        try {
            conn = phoenixUtil.GetConnection();
            String sql = "Select AVG(TO_NUMBER(\""+columnName+"\")) FROM \""+tableName+"\"";

            //将一列的字符串中的<>及其中内容删掉
//            int col = rs.getMetaData().getColumnCount();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
//                System.out.println(rs.getBigDecimal(1));
                result = String.valueOf(rs.getBigDecimal(1));
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }

    //获取一列的最大值
    public String getMaxNum(String tableName, String columnName){
        phoenixUtil = new PhoenixUtil();
        Connection conn = null;
        String result = "";
        try {
            conn = phoenixUtil.GetConnection();
            String sql = "Select Max(TO_NUMBER(\""+columnName+"\")) FROM \""+tableName+"\"";

            //将一列的字符串中的<>及其中内容删掉
//            int col = rs.getMetaData().getColumnCount();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
//                System.out.println(rs.getBigDecimal(1));
                result = String.valueOf(rs.getBigDecimal(1));
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }

    //获取一列最小值
    public String getMinNum(String tableName, String columnName){
        phoenixUtil = new PhoenixUtil();
        Connection conn = null;
        String result = "";
        try {
            conn = phoenixUtil.GetConnection();
            String sql = "Select Min(TO_NUMBER(\""+columnName+"\")) FROM \""+tableName+"\"";

            //将一列的字符串中的<>及其中内容删掉
//            int col = rs.getMetaData().getColumnCount();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
//                System.out.println(rs.getBigDecimal(1));
                result = String.valueOf(rs.getBigDecimal(1));
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }



    //重置一列，参数为当前表名，源表名，列名
    public Boolean resetColumn(String currentTableName,String sourceTable,String columnName) {
        PhoenixUtil util =new PhoenixUtil();
        Connection conn = null;
        try {
            // get connection
            conn = util.GetConnection();

            // check connection
            if (conn == null) {
                System.out.println("conn is null...");
                return false;
            }
            String count="SELECT COUNT(*) FROM \""+currentTableName+"\"";
            PreparedStatement ps = conn.prepareStatement(count);
            ResultSet result=ps.executeQuery();
            conn.commit();
            int totleRow=0;
            while(result.next()){
                totleRow=result.getInt(1);
            }
            //一次提交的限制是50万，这里一次提交40万,循环来完成所有的清洗

            for (int i=0;i<totleRow;i+=400000){
//                    String sql = "upsert into \""+tableName+"\"(\"PK\",\"info\".\""+columnName+"\" ) SELECT \"PK\",REGEXP_REPLACE(\""+columnName+"\",\'"+replaceRegex+"\'+\'"+replaceTo+"\') FROM \""+tableName+"\" ";
//                    String sql ="UPSERT INTO \"test2\"(\"PK\",\"info\".\"comment_id\") SELECT \"PK\",REGEXP_REPLACE(\"info\".\"comment_id\", '[0-9]+', '111') FROM \"test2\" OFFSET DECODE(\'"+i+"\','HEX')";
                String sql ="upsert into \""+currentTableName+"\"(\"PK\",\"info\".\""+columnName+"\" ) SELECT \"PK\",\"info\".\""+columnName+"\" FROM \""+sourceTable+"\" LIMIT 400000 OFFSET "+i;
                PreparedStatement ps2 = conn.prepareStatement(sql);

                // execute upsert
                String msg = ps2.executeUpdate() > 0 ? "insert success..."
                        : "insert fail...";
                if(msg =="insert fail..."){
                    return false;
                }
                // you must commit
                conn.commit();
                System.out.println(msg);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return true;
    }

    //重置整张表
    public Boolean resetTable(String currentTableName,String sourceTable) {
        PhoenixUtil util =new PhoenixUtil();
        Connection conn = null;
        try {
            // get connection
            conn = util.GetConnection();

            // check connection
            if (conn == null) {
                System.out.println("conn is null...");
                return false;
            }
            String count="SELECT COUNT(*) FROM \""+currentTableName+"\"";
            PreparedStatement ps = conn.prepareStatement(count);
            ResultSet result=ps.executeQuery();
            conn.commit();
            int totleRow=0;
            while(result.next()){
                totleRow=result.getInt(1);
            }
            //一次提交的限制是50万，这里一次提交40万,循环来完成所有的清洗

            for (int i=0;i<totleRow;i+=400000){
//                    String sql = "upsert into \""+tableName+"\"(\"PK\",\"info\".\""+columnName+"\" ) SELECT \"PK\",REGEXP_REPLACE(\""+columnName+"\",\'"+replaceRegex+"\'+\'"+replaceTo+"\') FROM \""+tableName+"\" ";
//                    String sql ="UPSERT INTO \"test2\"(\"PK\",\"info\".\"comment_id\") SELECT \"PK\",REGEXP_REPLACE(\"info\".\"comment_id\", '[0-9]+', '111') FROM \"test2\" OFFSET DECODE(\'"+i+"\','HEX')";
                String sql ="upsert into \""+currentTableName+"\"(*) SELECT * FROM \""+sourceTable+"\" LIMIT 400000 OFFSET "+i;
                PreparedStatement ps2 = conn.prepareStatement(sql);

                // execute upsert
                String msg = ps2.executeUpdate() > 0 ? "insert success..."
                        : "insert fail...";
                if(msg =="insert fail..."){
                    return false;
                }
                // you must commit
                conn.commit();
                System.out.println(msg);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return true;
    }

    public int getRowCount(Connection conn,String tableName){
        String count="SELECT COUNT(1) FROM \""+tableName+"\"";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(count);
            ResultSet result=ps.executeQuery();
            conn.commit();
            int totleRow=0;
            while(result.next()){
                totleRow=result.getInt(1);
            }
            return totleRow;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

    }
}
