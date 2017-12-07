package com.sicdlib.service;


import com.eharmony.pho.api.DataStoreApi;
import com.eharmony.pho.query.builder.QueryBuilder;
import com.google.common.collect.Lists;
import com.google.gson.Gson;


import com.sicdlib.util.PhoenixUtil.ChushulingException;
import com.sicdlib.util.PhoenixUtil.MapToJson;
import com.sicdlib.util.PhoenixUtil.PhoenixUtil;
import com.sicdlib.util.PhoenixUtil.SpecialPhoenixUtil;
import com.sicdlib.util.aop.ForService;
//import com.sicdlib.util.aop2.SystemLog;
import org.apache.hadoop.hbase.snapshot.CreateSnapshot;
import org.hibernate.sql.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service("dataCleanService")
public class DataCleanService {
    PhoenixUtil util =new PhoenixUtil();
    SpecialPhoenixUtil specialPhoenixUtil=new SpecialPhoenixUtil();
    //num是查询的行数

    public List<Map<String, Object>> queryResult(String tablename,int num){
        try{
            String name = tablename.replaceAll("\'","");


//            Class<?> TBTableEntityType =Class.forName("com.sicdlib.dto.phoenixEntity."+name);
//            List headJson= Lists.newArrayList(dataStoreApi.findAll(QueryBuilder
//                    .builderFor(TBTableEntityType)
//                    .setMaxResults(num)
//                    .select().build()));


            return util.Select(tablename,num);

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    }
    //没用这个函数

//    public List getThead(String tablename){
//        //直接用getEntity(tablename);
//        try {
//            //原来直接查hbase数据库的代码
//            //            HBaseData hBaseData = new HBaseData(tablename.replaceAll("\'",""));
//            //            List<String> fields = hBaseData.getKeys(tablename.replaceAll("\'",""));
//            //            System.out.println(fields);
//            //            return fields;
//
//            List headJson=queryResult(tablename,20);
//            Gson gson = new Gson();
//            String strHead = gson.toJson(headJson);
//            strHead = strHead.replace("[","");
//            strHead = strHead.replace("]","");
//
//            //
////            StringBuilder sb = new StringBuilder();
//            List headResult=new ArrayList();
//            org.json.JSONObject jsonObject = new org.json.JSONObject(strHead);
//            Iterator iteratorHead = jsonObject.keys();
//            while(iteratorHead.hasNext()){
//                String key = (String) iteratorHead.next();
//                headResult.add(key);
////                sb.append("="+jsonObject.getString(key));
//            }
////            return headResult;
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
    //将类的属性名转化为表名字，如authorName转化为author_name
//    public String turnToTableName(String s){
//        //实体类中的属性如author_ID的处理,与其他统一成camel命名来一起转化
//        s=s.replaceAll("IDS","Ids");
//        s=s.replaceAll("ID","Id");
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < s.length(); i++) {
//            char ch = s.charAt(i);
//            if (Character.isUpperCase(s.charAt(i)))
//                sb.append("_");
////                sb.append((char)(ch + 32));
//            sb.append(ch);
//        }
//        return sb.toString().toLowerCase();
//    }
@ForService(description = "getBody")
    public List getTbody(String tablename){
//        String entityname = getEntity(tablename);
            List<Map<String, Object>> bodyMap=queryResult(tablename,50);

//            for(int i=0;i<bodyMap.size();i++) {
//                System.out.println("bodyjson:"+bodyMap.get(i));
//            }

            List bodyResult = new ArrayList(50);
            //获得表头
            List headResult=new ArrayList();
            try{

                //其中一行的map转化为json对象
                org.json.JSONObject jsonHead = new org.json.JSONObject(bodyMap.get(0));

            Iterator iteratorHead = jsonHead.keys();

            while(iteratorHead.hasNext()){
                String property = (String) iteratorHead.next();
                //结果中是键值对，键是属性名，需要用下面的函数来处理成真正的列名。
                headResult.add(property);

    //                sb.append("="+jsonObject.getString(key));
            }
            }catch (Exception e){
                e.printStackTrace();
        }
            //获得表的内容，也就是json的值

            for(int i=0;i<bodyMap.size();i++) {

                //list转json类型字符串
//                Gson gson = new Gson();
//                String strBody = gson.toJson(bodyJson.get(i));
//                strBody = strBody.replace("[", "");
//                strBody = strBody.replace("]", "");

                //
//            StringBuilder sb = new StringBuilder();

                //map对象转化为json对象
                org.json.JSONObject jsonObject = new org.json.JSONObject(bodyMap.get(i));

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
    public LinkedHashMap<String,Integer> getOrder(String tableName,String columnName){

        try {
//            System.out.println("列名"+columnName);

            Connection conn =GetConnection();
//            Statement stmt =conn.createStatement();
//            String sql ="select * from \"bbs_china_comment\" limit 5";
            columnName=columnName.replaceAll("'","");
            tableName=tableName.replaceAll("'","");
//            String sql ="select"+"\""+columnName+"\""+","+"count(*)"+"from "+"\""+tableName+"\""+"GROUP BY"+"\""+columnName+"\""+"ORDER BY "+"count(*)"+"DESC"+"limit 10";

            String sql ="select"+"\""+columnName+"\""+",count(1) "+"from "+"\""+tableName+"\""+"GROUP BY "+"\""+columnName+"\""+" ORDER BY count(*) DESC limit 10";

//            String sql="select \"author_id\", count(*) from (\"bbs_china_comment\") group by \"author_id\" order by count(*) desc limit 20";
            PreparedStatement ps =conn.prepareStatement(sql);
            ResultSet rs =ps.executeQuery();
//            int col = rs.getMetaData().getColumnCount();
            LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
            while (rs.next()){
                String key ="";
                try{
                    key=rs.getString(1);
                    System.out.println(key);
                    //传回前台的json中键不允许空，因此将null展示为null value,并非数据库中值为null value
                    if(key==null || key.isEmpty()){
                       key ="null value";
                    }else if(key=="null"|| "".equals(key)||key==""||"null".equals(key)){
                        key ="null value";
                    }
                }catch (SQLException e){
                    key="null value";
                    e.printStackTrace();
                }finally {
                    result.put(key, rs.getInt(2));
                }

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

    //清洗方法执行,12,14,15这三个方法仅仅sql不同，可考虑抽象出一个公共函数
//    @SystemLog(module = "数据清洗",methods = "doClean")
//    @DataCleanLog(currentTable="myTable")
//    @DataCleanLog(lala="myTable")
    public Boolean doClean(String currentTable, String  currentColumn, String strategyID, String oldValue, String newValue){
        String sourceTable=currentTable+"_reset";
        switch (strategyID)
        {
            /*空值的处理*/
            //填充空字符串为o
            case "11":
            {
                Boolean state=util.upsertNullColumn(currentTable,currentColumn,"0");
                if(state==false){
                    return false;
                }
                break;
            }
            //填充空字符串为均值
            case "12":
            {
                String avg=specialPhoenixUtil.getAverage(currentTable,currentColumn);
                System.out.println("平均值是:"+avg);
                Boolean state=util.upsertNullColumn(currentTable,currentColumn,avg);
                if(state==false){
                    return false;
                }
                break;
            }
            //填充空字符串为最频繁值
            case "13":
            {
                //用到上面查最频繁5项的函数
                LinkedHashMap<String,Integer> frequentValue =getOrder(currentTable,currentColumn);
                String value =frequentValue.entrySet().iterator().next().getKey().toString();
//                System.out.println("最频繁值是:"+value);
                Boolean state=util.upsertNullColumn(currentTable,currentColumn,value);
                if(state==false){
                    return false;
                }
                break;
            }
            //取最大值
            case "14":{
                String maxNum =specialPhoenixUtil.getMaxNum(currentTable,currentColumn);
//                System.out.println("最大值是:"+maxNum);
                Boolean state=util.upsertNullColumn(currentTable,currentColumn,maxNum);
                if(state==false){
                    return false;
                }
                break;
            }
            //取最小值
            case "15":{
                String minNum =specialPhoenixUtil.getMinNum(currentTable,currentColumn);
//                System.out.println("最小值是:"+minNum);
                Boolean state=util.upsertNullColumn(currentTable,currentColumn,minNum);
                if(state==false){
                    return false;
                }
                break;
            }
            case "16":{
                Boolean state=util.upsertNullColumn(currentTable,currentColumn,newValue);
                if(state==false){
                    return false;
                }
                break;
            }
            /*错误字符串的处理*/
            //删除该列
            case "17":{
                Boolean state=util.deleteColumn(currentTable,currentColumn);
                if(state==false){
                    return false;
                }
                break;
            }
            //删除字符串中的空格
            case "18":{
                util.upsertColumnWithRegex(currentTable,currentColumn," ","");
                util.upsertColumnWithRegex(currentTable,currentColumn,"\t","");
                util.upsertColumnWithRegex(currentTable,currentColumn,"\r","");
                break;
            }
            //删除字符串中的\n
            case "19":{
                Boolean state=util.upsertColumnWithRegex(currentTable,currentColumn,"\n","");
                if(state==false){
                    return false;
                }
                break;
            }
            //删除字符串中的双标签以及其中的值
            case "20":{
                Boolean state=util.upsertColumnWithRegex(currentTable,currentColumn,"<.*?>.*?<.*?>","");
                if(state==false){
                    return false;
                }
                break;
            }
            //删除字符串中的单标签及其中的值
            case "21":{
                Boolean state=util.upsertColumnWithRegex(currentTable,currentColumn,"<.*?>","");
                if(state==false){
                    return false;
                }
                break;
            }
            //修改某特定字符串
            case "22":{
                Boolean state=util.upsertColumn(currentTable,currentColumn,oldValue,newValue);
                if(state==false){
                    return false;
                }
                break;
            }
            //正则修改某字符串
            case "23":{
                Boolean state=util.upsertColumnWithRegex(currentTable,currentColumn,oldValue,newValue);
                if(state==false){
                    return false;
                }
                break;
            }
            /*行操作*/
            //删除某字段包含特定字符串的所有行
            case "24":{
                Boolean state=util.deleteRow(currentTable,currentColumn,oldValue);
                if(state==false){
                    return false;
                }
                break;
            }
            //正则匹配一列修改符合条件的行
            case "25":{
                Boolean state=util.deleteRowWithRegex(currentTable,currentColumn,oldValue);
                if(state==false){
                    return false;
                }
                break;
            }
            //针对一列删除重复的行
            case "26":{
                Boolean state=util.deleteRepeatRow(currentTable,currentColumn);
                if(state==false){
                    return false;
                }
                break;
            }
            //时间统一为xxxx-xx-xx xx:xx:xx
            case "27":{
                Boolean state=util.changeTimeFormat(currentTable,currentColumn,"27");
                if(state==false){
                    return false;
                }
                break;
            }
            //时间统一为xxxx年xx月xx日 xx:xx:xx
            case "28":{
                Boolean state=util.changeTimeFormat(currentTable,currentColumn,"28");
                if(state==false){
                    return false;
                }
                break;
            }
            //地址统一为陕西省西安市
            case "29":{
                Boolean state=util.changeLocationFormat(currentTable,currentColumn);
                if(state==false){
                    return false;
                }
                break;
            }
        //该列重置
            case "resetColumn":
            {
                Boolean state=specialPhoenixUtil.resetColumn(currentTable, sourceTable,currentColumn);
                if(state==false){
                    return false;
                }
                break;
            }
            //重置整个表,可能不能用……
            case "resetTable":
            {
                Boolean state=specialPhoenixUtil.resetTable(currentTable,sourceTable);
                if(state==false){
                    return false;
                }
                break;
            }
        }
        return true;
    }
}

