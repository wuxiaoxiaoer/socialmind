package com.sicdlib.util.PhoenixUtil;

import com.sicdlib.web.DataCleanAction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PhoenixUtil{

    //连接phoniex
    public Connection GetConnection(){
        Connection cc = null;
        String driver = "org.apache.phoenix.jdbc.PhoenixDriver";
        String url = "jdbc:phoenix:192.168.100.201:2181";

        try {
            Class.forName(driver);
            cc = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return cc;
    }

//    public static void main(String[] args){
//
//        PhoenixUtil util =new PhoenixUtil();
//        util.upsertColumnWithRegex("test2","content","\\n","");
////        util.deleteColumn("test","author_name");
////        util.upsertColumn("test","name","青岛人","河北省石家庄");
//
//            //增加一列
////            String sql ="ALTER TABLE \"bbs_china_author_test\" ADD \"info\".\"yiming\" varchar(30) VERSIONS=5";
////            Statement stmt =conn.createStatement();
////            stmt.execute(sql);
//
////            String sql ="ALTER TABLE \"bbs_china_author_test\" ADD \"info\".\"yiming\" varchar(30) VERSIONS=5";
////            String sal="ALTER TABLE \"bbs_china_author_test\" DROP COLUMN info.yiming", parent_id";
//            //删除一行
//
//
//    }
    //phoenix的查询方法,目前限制查询一列
    public List<Map<String, Object>> Select(String tableName,int limits){
        PhoenixUtil util =new PhoenixUtil();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try{
            Connection conn =util.GetConnection();
            String sql="Select * FROM \""+tableName+"\" limit "+limits;

            //将一列的字符串中的<>及其中内容删掉
//            int col = rs.getMetaData().getColumnCount();
            Statement stmt =conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
//            List list =new ArrayList();
//            while(set.next()){
////                System.out.println(set.getString(1));
////                System.out.println(set.getString(2));
//                result.add(set);
//            }
            GetList getList=new GetList();
            result = getList.getListFromRs(rs);
            rs.close();
            stmt.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    //删除一行,列名columnName的值为value的一行.
    public Boolean deleteRow(String tableName,String columnName,String value){
        PhoenixUtil util =new PhoenixUtil();
        try{
            Connection conn =util.GetConnection();
            // check connection
            if (conn == null) {
                System.out.println("conn is null...");
                return false;
            }
            String sql = "DELETE FROM \""+tableName+"\" WHERE \"info\".\""+columnName+"\"=\'"+value+"\'";
            PreparedStatement stmt =conn.prepareStatement(sql);
            //如何判断删除成功或失败?
            int msg =stmt.executeUpdate();
            conn.commit();
            stmt.close();
            conn.close();

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    //删除一列
    public Boolean deleteColumn(String tableName,String columnName) {
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

            // 默认所有列族都是info
            String sql = "ALTER TABLE \""+tableName+"\" DROP COLUMN \"info\".\""+columnName+"\"";

            PreparedStatement ps = conn.prepareStatement(sql);

            // execute upsert
            String msg = ps.executeUpdate() > 0 ? "delete success..."
                    : "delete fail...";
            if(msg =="insert fail..."){
                return false;
            }
            // you must commit
            conn.commit();
            System.out.println(msg);

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

    //替换一列,可选择某个值来填充所有空
    public Boolean upsertColumn(String tableName,String columnName,String oldValue,String newValue) {
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
            String count="SELECT COUNT(*) FROM \""+tableName+"\"";
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
                    String sql ="upsert into \""+tableName+"\"(\"PK\",\"info\".\""+columnName+"\" ) SELECT \"PK\",\'"+newValue+"\' FROM \""+tableName+"\" WHERE \"info\".\""+columnName+"\" =\'"+oldValue+"\' LIMIT 400000 OFFSET "+i;
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
    //正则修改一列,暂时没写where子句
    public Boolean upsertColumnWithRegex(String tableName,String columnName,String replaceRegex,String replaceTo) {
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
            String count="SELECT COUNT(*) FROM \""+tableName+"\"";
            PreparedStatement ps = conn.prepareStatement(count);
            ResultSet result=ps.executeQuery();
            conn.commit();
            int totleRow=0;
            while(result.next()){
                totleRow=result.getInt(1);
            }
            //一次提交的限制是50万，这里一次提交40万,循环来完成所有的清洗
            if (totleRow >400000){
                for (int i=0;i<totleRow;i+=400000){
//                    String sql = "upsert into \""+tableName+"\"(\"PK\",\"info\".\""+columnName+"\" ) SELECT \"PK\",REGEXP_REPLACE(\""+columnName+"\",\'"+replaceRegex+"\'+\'"+replaceTo+"\') FROM \""+tableName+"\" ";
//                    String sql ="UPSERT INTO \"test2\"(\"PK\",\"info\".\"comment_id\") SELECT \"PK\",REGEXP_REPLACE(\"info\".\"comment_id\", '[0-9]+', '111') FROM \"test2\" OFFSET DECODE(\'"+i+"\','HEX')";
                    String sql ="UPSERT INTO \""+tableName+"\"(\"PK\",\"info\".\""+columnName+"\") SELECT \"PK\",REGEXP_REPLACE(\"info\".\"comment_id\", '"+replaceRegex+"', '"+replaceTo+"') FROM \""+tableName+"\" LIMIT 400000 OFFSET "+i;
                    PreparedStatement ps2 = conn.prepareStatement(sql);

                    // execute upsert
                    String msg = ps2.executeUpdate() > 0 ? "insert success..."
                            : "insert fail...";

                    // you must commit
                    conn.commit();
                    System.out.println(msg);
                    if(msg =="insert fail..."){
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            //报错未必不执行，如socket超时，因此不在这里retrun false
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
}
