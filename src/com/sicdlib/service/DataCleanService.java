package com.sicdlib.service;


import com.eharmony.pho.api.DataStoreApi;
import com.eharmony.pho.query.builder.QueryBuilder;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import org.apache.hadoop.hbase.snapshot.CreateSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;

@Repository("dataCleanService")
public class DataCleanService {

    //num是查询的行数
    public List queryResult(String tablename,int num){
        try{
            String name = tablename.replaceAll("\'","");
//            Class<?> TBTableEntityType =Class.forName("com.sicdlib.dto.phoenixEntity."+name);
//            List headJson= Lists.newArrayList(dataStoreApi.findAll(QueryBuilder
//                    .builderFor(TBTableEntityType)
//                    .setMaxResults(num)
//                    .select().build()));
            List headJson = new ArrayList();

            return headJson;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    }
    //没用这个函数
    public List getThead(String tablename){
        //直接用getEntity(tablename);
        try {
            //原来直接查hbase数据库的代码
            //            HBaseData hBaseData = new HBaseData(tablename.replaceAll("\'",""));
            //            List<String> fields = hBaseData.getKeys(tablename.replaceAll("\'",""));
            //            System.out.println(fields);
            //            return fields;

            List headJson=queryResult(tablename,20);
            Gson gson = new Gson();
            String strHead = gson.toJson(headJson);
            strHead = strHead.replace("[","");
            strHead = strHead.replace("]","");

            //
//            StringBuilder sb = new StringBuilder();
            List headResult=new ArrayList();
            org.json.JSONObject jsonObject = new org.json.JSONObject(strHead);
            Iterator iteratorHead = jsonObject.keys();
            while(iteratorHead.hasNext()){
                String key = (String) iteratorHead.next();
                headResult.add(key);
//                sb.append("="+jsonObject.getString(key));
            }
//            return headResult;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    //将类的属性名转化为表名字，如authorName转化为author_name
    public String turnToTableName(String s){
        //实体类中的属性如author_ID的处理,与其他统一成camel命名来一起转化
        s=s.replaceAll("IDS","Ids");
        s=s.replaceAll("ID","Id");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isUpperCase(s.charAt(i)))
                sb.append("_");
//                sb.append((char)(ch + 32));
            sb.append(ch);
        }
        return sb.toString().toLowerCase();
    }
    public List getTbody(String tablename){
//        String entityname = getEntity(tablename);

            List bodyJson=queryResult(tablename,500);
            List bodyResult = new ArrayList(500);
            //获得表头
            List headResult=new ArrayList();
            try{
                org.json.JSONObject jsonHead = new org.json.JSONObject(bodyJson.get(0));

            Iterator iteratorHead = jsonHead.keys();
            while(iteratorHead.hasNext()){
                String property = (String) iteratorHead.next();
                //结果中是键值对，键是属性名，需要用下面的函数来处理成真正的列名。
                String key = turnToTableName(property);
                headResult.add(key);
    //                sb.append("="+jsonObject.getString(key));
            }
            }catch (Exception e){
                e.printStackTrace();
        }
            //获得表的内容，也就是json的值
            for(int i=0;i<bodyJson.size();i++) {
                //list转json类型字符串
//                Gson gson = new Gson();
//                String strBody = gson.toJson(bodyJson.get(i));
//                strBody = strBody.replace("[", "");
//                strBody = strBody.replace("]", "");

                //
//            StringBuilder sb = new StringBuilder();

                org.json.JSONObject jsonObject = new org.json.JSONObject(bodyJson.get(i));
                Iterator iterator = jsonObject.keys();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    //result.add(key);
                        bodyResult.add(jsonObject.getString(key));
                }
            }
            List result =new ArrayList();
            result.add(headResult);
            result.add("+");
            result.add(bodyResult);
            return result;
    }
    //连接phoniex
    public Connection GetConnection(){
        Connection cc = null;
        String driver = "org.apache.phoenix.jdbc.PhoenixDriver";
        String url = "jdbc:phoenix:192.168.100.201:2181";

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (cc == null) {
            try {
                cc = DriverManager.getConnection(url);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cc;
    }
    //复制表为一张新表
    public boolean copyNewTable(String tableName){
        try{
            //用copytable复制表
//            CopyTable copyTable= new CopyTable(new Configuration());
//            String command ="org.apache.hadoop.hbase.mapreduce.CopyTable --new.name='bbs_china_comment_copy' 'bbs_china_comment'";
//            String[] arr=command.split(" ");
//            copyTable.createSubmittableJob(arr);

            //用snapshot复制表
            CreateSnapshot shot=new CreateSnapshot();
            String shotCommand = "snapshot 'bbs_china_post' 'bbs_china_post_shot'";
            String[] shotArray=shotCommand.split(" ");
            shot.main(shotArray);

            String newTableComment ="clone_snapshot 'bbs_china_post_sot' 'bbs_china_post_copy'";
            String[] newTableArray = newTableComment.split(" ");


            tableName=tableName.replaceAll("'","");
            //先写死
            String sql ="";
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
    //根据表名、列名，查那一列里数目最多的5个数返回给前台,封装成的list格式为都是单个的项，奇数为项的名，偶数为对应的数目
    public Map<String,Integer> getOrder(String tableName,String columnName){

        try {
//            System.out.println("列名"+columnName);

            Connection conn =GetConnection();
//            Statement stmt =conn.createStatement();
//            String sql ="select * from \"bbs_china_comment\" limit 5";
            columnName=columnName.replaceAll("'","");
            tableName=tableName.replaceAll("'","");
//            String sql ="select"+"\""+columnName+"\""+","+"count(*)"+"from "+"\""+tableName+"\""+"GROUP BY"+"\""+columnName+"\""+"ORDER BY "+"count(*)"+"DESC"+"limit 10";
            String sql ="select"+"\""+columnName+"\""+",count(1)"+"from "+"\""+tableName+"\""+"GROUP BY "+"\""+columnName+"\""+"ORDER BY count(*) DESC limit 5";
//            String sql="select \"author_id\", count(*) from (\"bbs_china_comment\") group by \"author_id\" order by count(*) desc limit 20";
            PreparedStatement ps =conn.prepareStatement(sql);
            ResultSet rs =ps.executeQuery();
//            int col = rs.getMetaData().getColumnCount();
            Map<String, Integer> result = new LinkedHashMap<>();
            while (rs.next()){
                result.put(rs.getString(1), rs.getInt(2));
            }
//
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    }
}
