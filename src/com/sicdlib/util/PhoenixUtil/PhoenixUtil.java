package com.sicdlib.util.PhoenixUtil;

import com.sicdlib.util.DBUtil;
import org.ansj.library.UserDefineLibrary;
import org.ansj.splitWord.analysis.ToAnalysis;

import javax.xml.stream.events.StartDocument;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.regex.Pattern;


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
@org.junit.Test
public void test01(){
    //数据源表与website建立关系
    String sql = "select * from \"bbs_china_post_copy\" limit 10 offset 1";
    List<Map<String, Object>> result = selectHbaseBySql(sql);
//        List<Map<String, Object>> result = Select("bbs_china_post_copy", 10);
    System.out.println("结果：" + result.size());

}

    //查询
    public static List<Map<String, Object>> selectHbaseBySql(String sql){
        PhoenixUtil util =new PhoenixUtil();
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Connection conn =util.GetConnection();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            GetList getList=new GetList();
            result = getList.getListFromRs(rs);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    //phoenix的查询方法
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
    //正则匹配列来删除行
    public Boolean deleteRowWithRegex(String tableName,String columnName,String regex){
        PhoenixUtil util =new PhoenixUtil();
        try{
            Connection conn =util.GetConnection();
            // check connection
            if (conn == null) {
                System.out.println("conn is null...");
                return false;
            }
            String sql = "DELETE FROM \""+tableName+"\" WHERE REGEXP_SUBSTR(\"info\".\""+columnName+"\", \'"+regex+"\') is NOT null" ;
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

    //根据某列的值去掉重复的行
    public Boolean deleteRepeatRow(String tableName,String columnName){
        PhoenixUtil util =new PhoenixUtil();
        try{
            Connection conn =util.GetConnection();
            // check connection
            if (conn == null) {
                System.out.println("conn is null...");
                return false;
            }
            String sql = "DELETE FROM \""+tableName+"\" WHERE \""+columnName+"\" in (SELECT \""+columnName+"\" FROM \""+tableName+"\" GROUP BY \""+columnName+"\" HAVING COUNT(\""+columnName+"\")>1) AND \"PK\" not in (SELECT MIN(\"PK\") FROM \""+tableName+"\" GROUP BY \""+columnName+"\" HAVING COUNT(*)>1)";
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

                for (int i=0;i<totleRow;i+=400000){
//                    String sql = "upsert into \""+tableName+"\"(\"PK\",\"info\".\""+columnName+"\" ) SELECT \"PK\",REGEXP_REPLACE(\""+columnName+"\",\'"+replaceRegex+"\'+\'"+replaceTo+"\') FROM \""+tableName+"\" ";
//                    String sql ="UPSERT INTO \"test2\"(\"PK\",\"info\".\"comment_id\") SELECT \"PK\",REGEXP_REPLACE(\"info\".\"comment_id\", '[0-9]+', '111') FROM \"test2\" OFFSET DECODE(\'"+i+"\','HEX')";
                    String sql ="UPSERT INTO \""+tableName+"\"(\"PK\",\"info\".\""+columnName+"\") SELECT \"PK\",REGEXP_REPLACE(\"info\".\""+columnName+"\", '"+replaceRegex+"', '"+replaceTo+"') FROM \""+tableName+"\" LIMIT 400000 OFFSET "+i;
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

    //change time format, the third value is the value matches "when", the forth value is which after "then". In this process, we just clean data, not unify the length of every kind of data.
    public Boolean changeTimeFormat(String tableName, String columnName,String strategyID){
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
//            for (int i = 0; (start+10000)<count; ){
//                int num =10000;
//                int start = i*num;
//                select(start, num);
//            }
            SpecialPhoenixUtil specialPhoenixUtil = new SpecialPhoenixUtil();
            int rowCount = specialPhoenixUtil.getRowCount(conn, tableName);
            Connection connPhoenix = null;
            //每次最多处理5000条数据，避免内存溢出
            for (int i = 0; i < rowCount;i+=5000) {
                //1.首先在表中读出PK和对应的时间，存到map中，每一行是一个map(两个键值对，键是列名),然后放到一个list里
                List<Map<String, Object>> oldValueList = SelectPKAndOneColumn(conn, tableName, columnName,i);
                List oldResult = new ArrayList();
                //得到包含主键和时间的list
                for (int w = 0; w < oldValueList.size(); w++) {
                    org.json.JSONObject jsonObject = new org.json.JSONObject(oldValueList.get(w));
                    Iterator iterator = jsonObject.keys();
                    while (iterator.hasNext()) {
                        String key = (String) iterator.next();
//                    System.out.println(key);
                        //result.add(key);
                        oldResult.add(jsonObject.getString(key));
                    }
                }

                // 2然后对list中的所有时间值转化为时间格式并格式化为标准格式，并转化回字符串格式，然后将主键和日期结果对应起来存到map里
                Map<String, String> transferResult = new HashMap<String, String>();
                SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
                SimpleDateFormat sdf2 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.UK);
                SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm EEE");
                SimpleDateFormat sdf4 = new SimpleDateFormat("(yyyy-MM-dd HH:mm:ss)");
                SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                SimpleDateFormat sdf7 = new SimpleDateFormat("yyyy/MM/dd");
                SimpleDateFormat sdf8 = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
                SimpleDateFormat sdf9 = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdf10 = new SimpleDateFormat("yy-MM-dd");
                SimpleDateFormat sdf11 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                SimpleDateFormat toDateFormate27 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat toDateFormate28 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                String connect = "";
                Date date = new Date();
                String str = "";
                //j是pk，k是日期
                for (int j = 0; j < oldResult.size(); ) {
                    int k = j + 1;
                    String key = oldResult.get(j).toString();
                    String value = oldResult.get(k).toString();
                    //正则判断日期字符串类型来相应的匹配
                    if (Pattern.matches("\\w{3} \\w{3} \\d{2} [0-9]{2}:[0-9]{2}:[0-9]{2} \\w{3} \\d{4}", value)) {
                        date = sdf1.parse(value);
                    } else if (Pattern.matches("\\w{3}, \\d{1} \\w{3} \\d{4} [0-9]{2}:[0-9]{2}:[0-9]{2} \\w{3}", value)) {
                        date = sdf2.parse(value);
                    } else if (Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2} [星][期][一二三四五六日].*?", value)) {
                        date = sdf3.parse(value);
                        //http://blog.csdn.net/z991876960/article/details/53117260   必须在5前面
                    } else if (Pattern.matches("\\([0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}\\)", value)) {
                        date = sdf4.parse(value);
                        //http://blog.csdn.net/lxcnn/article/details/4362500  必须在8前面
                    } else if (Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}", value)) {
                        date = sdf5.parse(value);
                        //必须在7前面
                    } else if (Pattern.matches("\\d{4}/\\d{2}/\\d{2} [0-9]{2}:[0-9]{2}:[0-9]{2}", value)) {
                        date = sdf6.parse(value);
                        //http://blog.csdn.net/dl020840504/article/details/17055531      https://www.cnblogs.com/guyezhai/p/6729663.html
                    } else if (Pattern.matches("\\d{4}/\\d{2}/\\d{2}", value)) {
                        date = sdf7.parse(value);
                    } else if (Pattern.matches("[0-9]{2}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}", value)) {
                        date = sdf8.parse(value);
                        //http://blog.csdn.net/cwlmxmz/article/details/45045961  https://zhidao.baidu.com/question/578661060.html?qbl=relate_question_4&word=%B8%F7%D6%D6%C8%D5%C6%DA%B8%F1%CA%BD%D5%FD%D4%F2  必须在10前面
                    } else if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}", value)) {
                        date = sdf9.parse(value);
                    } else if (Pattern.matches("\\d{2}-\\d{2}-\\d{2}", value)) {
                        date = sdf10.parse(value);
                        //https://zhidao.baidu.com/question/410315343.html
                    } else if (Pattern.matches("[0-9]{4}[年|\\-|/][0-9]{1,2}[月|\\-|/][0-9]{1,2}[日|\\-|/] [0-9]{2}:[0-9]{2}:[0-9]{2}", value)) {
                        date = sdf11.parse(value);
                    } else {
                        str = value;
                    }
//                //test
//                date=sdf7.parse(value);
                    if (str != value) {
                        if (strategyID == "27") {
                            str = toDateFormate27.format(date);
                        } else if (strategyID == "28") {
                            str = toDateFormate28.format(date);
                        }
                    }
                    System.out.println(str);
                    //将当期的主键和处理后的地址名存入Map
                    transferResult.put(key, str);
                    j = j + 2;
                }
                //4.插入回原phoenix表
                Iterator<Map.Entry<String, String>> entries = transferResult.entrySet().iterator();
                util = new PhoenixUtil();

                try {
                    // get connection
                    connPhoenix = util.GetConnection();

                    // check connection
                    if (connPhoenix == null) {
                        System.out.println("conn is null...");
                        return false;
                    }
                    while (entries.hasNext()) {
                        Map.Entry<String, String> entry = entries.next();
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                        upsertOneColumn(connPhoenix, tableName, columnName, entry.getKey(), entry.getValue());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }



            }
            if (connPhoenix != null) {
                try {
                    connPhoenix.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            /*
            String count="SELECT COUNT(*) FROM \""+tableName+"\"";
            PreparedStatement ps = conn.prepareStatement(count);
            ResultSet result=ps.executeQuery();
            conn.commit();
            int totleRow=0;
            while(result.next()){
                totleRow=result.getInt(1);
            }
            //一次提交的限制是50万，这里一次提交40万,循环来完成所有的清洗
            //When we use the judge sentence case, when it maches the first case, it won't execute else.so we match which has detail time first and then maches which doesn't have detail time.

            for (int i=0;i<totleRow;i+=400000){

                String sql ="";


                //直接用sql的方法，部分日期无法解析
                String sql ="UPSERT INTO \""+tableName+"\"(\"PK\",\"info\".\""+columnName+"\") SELECT \"PK\",CASE " +
                        //if the format is like   Sat Jul 26 09:59:57 CST 2014,currently this order are unable to use here, but useful in squirrel
//                        "WHEN \"info\".\""+columnName+"\" LIKE '___, _ ___ ____ __:%:% ___' THEN REGEXP_SUBSTR(TO_CHAR(TO_DATE(\"info\".\""+columnName+"\", 'EEE, d MMM yyyy HH:mm:ss z', 'GMT')),'[^\\.]+') " +
                        //2017-11-22 16:38星期三   bbs_tianya_post
                        "WHEN \"info\".\""+columnName+"\" LIKE \'%-%-%星期%\' THEN REGEXP_SUBSTR(TO_CHAR(TO_DATE(\"info\".\""+columnName+"\", 'yyyy-MM-dd HH:mm EEE','CST+8:00')),'[^\\.]+') " +
                        //(2017-11-22 16:52:12)  blog_china_post
                        "WHEN \"info\".\""+columnName+"\" LIKE \'(____-__-__ __:__:__)\' THEN REGEXP_SUBSTR(TO_CHAR(TO_DATE(\"info\".\""+columnName+"\", '(yyyy-MM-dd HH:mm:ss)','CST+8:00)')),'[^\\.]+') " +
                        //deal with true format, to avoid it matches other format so as to be changed.
                        "WHEN \"info\".\""+columnName+"\" LIKE '____-__-__ __:%:__' THEN REGEXP_SUBSTR(TO_CHAR(TO_DATE(\"info\".\""+columnName+"\", 'yyyy-MM-dd HH:mm:ss','CST+8:00')),'[^\\.]+') " +
                        //deal with 2017/11/21 06:12:24, change / to -
                        "WHEN \"info\".\""+columnName+"\" LIKE '%/%/% %:%:%' THEN REGEXP_SUBSTR(TO_CHAR(TO_DATE(\"info\".\""+columnName+"\", 'yyyy/MM/dd HH:mm:ss','CST+8:00')),'[^\\.]+') " +
                        //deal with 2017/11/21, change / to -
                        "WHEN \"info\".\""+columnName+"\" LIKE '%/%/%' THEN REGEXP_SUBSTR(TO_CHAR(TO_DATE(\"info\".\""+columnName+"\", 'yyyy/MM/dd','CST+8:00')),'[^\\.]+') " +
                        //deal with 17-11-21 06:39:28, change 17 to 2017 ,if the yy represents 00-17, it will be filled to 2000-2017,or be filled to 19..
                        "WHEN \"info\".\""+columnName+"\" LIKE '__-%-% %:%:%' THEN REGEXP_SUBSTR(TO_CHAR(TO_DATE(\"info\".\""+columnName+"\", 'yy-MM-dd HH:mm:ss','CST+8:00')),'[^\\.]+') " +
                        //deal with 2017-11-21
                        "WHEN \"info\".\""+columnName+"\" LIKE '____-%-%' THEN REGEXP_SUBSTR(TO_CHAR(TO_DATE(\"info\".\""+columnName+"\", 'yyyy-MM-dd','CST+8:00')),'[^\\.]+') " +
//                        //deal with 17-11-21, change 17 to 2017 ,if the yy represents 00-17, it will be filled to 2000-2017,or be filled to 19..
                        "WHEN \"info\".\""+columnName+"\" LIKE '__-%-%' THEN REGEXP_SUBSTR(TO_CHAR(TO_DATE(\"info\".\""+columnName+"\", 'yy-MM-dd','CST+8:00')),'[^\\.]+') " +
//                        //2017年11月21日 23:54:12,将汉字替换为-
                        "WHEN \"info\".\""+columnName+"\" LIKE \'%年%月%日 __:__:__\' THEN REGEXP_SUBSTR(TO_CHAR(TO_DATE(\"info\".\""+columnName+"\", 'yyyy年MM月dd日 HH:mm:ss','CST+8:00')),'[^\\.]+') " +
                        "ELSE \"info\".\""+columnName+"\" END FROM \""+tableName+"\" LIMIT 400000 OFFSET "+i;

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

        } catch (SQLException e) {
            //报错未必不执行，如socket超时，因此不在这里retrun false
            e.printStackTrace();             */
            }catch(ParseException e){
                e.printStackTrace();
            } finally{
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
    //get PK and address in a table, to serve for the next function
    public List<Map<String, Object>> SelectPKAndOneColumn(Connection conn,String tableName, String columnName,int i){
            List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
            try {
                String sql = "Select \"PK\",\"" + columnName + "\" FROM \"" + tableName + "\" LIMIT 5000 OFFSET "+i;

                //将一列的字符串中的<>及其中内容删掉
//            int col = rs.getMetaData().getColumnCount();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
//            List list =new ArrayList();
//            while(set.next()){
////                System.out.println(set.getString(1));
////                System.out.println(set.getString(2));
//                result.add(set);
//            }
                GetList getList = new GetList();
                result = getList.getListFromRs(rs);
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return result;
    }
    //upsert table with rowkey and name
    public Boolean upsertOneColumn(Connection conn, String tableName, String columnName, String rowkey, String newValue) {

        try {

            String sql = "upsert into \"" + tableName + "\"(\"PK\",\"info\".\"" + columnName + "\" ) SELECT \"PK\",\'" + newValue + "\' FROM \"" + tableName + "\" WHERE \"PK\" =\'" + rowkey + "\'";
            PreparedStatement ps2 = conn.prepareStatement(sql);

            // execute upsert
            String msg = ps2.executeUpdate() > 0 ? "insert success..."
                    : "insert fail...";
            if (msg == "insert fail...") {
                return false;
            }
            // you must commit
            conn.commit();
            System.out.println(msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
    //29 deal with location which has no label"省""市"
    public Boolean changeLocationFormat(String tableName, String columnName) {
        //1.取mysql中的地址数据，一个是省地址，一个是市地址，分别存到list中
//        ProvinceService provinceService= new ProvinceService();
//        List<ProvinceEntity> provinces =provinceService.getProvinces();
//        System.out.println(provinces.get(1).getName());

        List<String> provinceNameList = new ArrayList<>();
        List<String> cityNameList = new ArrayList<>();
        DBUtil dbUtil = new DBUtil();
        Connection conn = dbUtil.GetConnection();
        String sql1 = "select id,name from province";
        String sql2 = "select id,name from city";
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
        try {
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql1);
            pstmt2 = conn.prepareStatement(sql2);
            rs = pstmt.executeQuery();
            rs2 = pstmt2.executeQuery();
            conn.commit();
            while (rs.next()) {
                //Retrieve by column name
                int provinceId = rs.getInt("id");
                String provinceName = rs.getString("name");
//                System.out.println(provinceId+":"+provinceName);
                provinceNameList.add(provinceName);
            }
            while (rs2.next()) {
                //Retrieve by column name
                int cityId = rs2.getInt("id");
                String cityName = rs2.getString("name");
//                System.out.println(cityId+":"+cityName);
                cityNameList.add(cityName);
            }
            rs2.close();
            pstmt2.close();
            dbUtil.closeConn(rs, pstmt, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //2.将省市信息添加到分词词库
        for (int i = 0; i < provinceNameList.size(); i++) {
            String provinceName = provinceNameList.get(i);
            UserDefineLibrary.insertWord(provinceName, "n", 1000);
        }
        for (int i = 0; i < cityNameList.size(); i++) {
            String cityName = cityNameList.get(i);
            UserDefineLibrary.insertWord(cityName, "n", 1000);
        }

        //3.对hbase中对应地址数据中文分词
        //3.1首先在表中读出PK和对应的地址值，存到map中，每一行是一个map(两个键值对，键是列名),然后放到一个list里
        SpecialPhoenixUtil specialPhoenixUtil = new SpecialPhoenixUtil();
        int rowCount = specialPhoenixUtil.getRowCount(conn, tableName);
        //每次最多处理5000条数据，避免内存溢出
        for (int i = 0; i < rowCount;i+=5000) {

            List<Map<String, Object>> oldValueList = SelectPKAndOneColumn(conn, tableName, columnName, i);
            List oldResult = new ArrayList(oldValueList.size());
            //存成键值对，将主键和地址名对应起来
//        Map<String, String> mapResult = new HashMap<String, String>();
            //得到包含主键和地址名的list
            for (int j = 0; j < oldValueList.size(); j++) {
                org.json.JSONObject jsonObject = new org.json.JSONObject(oldValueList.get(j));
                Iterator iterator = jsonObject.keys();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    //result.add(key);
                    oldResult.add(jsonObject.getString(key));
                }
            }
            // 3.2然后对list中的所有值分词处理，与地址表分别对比并补全,然后将主键和地址名的分词结果对应起来存到map里
            Map<String, String> splitResult = new HashMap<String, String>();
            for (int j = 0; j < oldResult.size(); ) {
                int k = j + 1;
                String key = oldResult.get(j).toString();
                String value = oldResult.get(k).toString();
                String valueTerms = ToAnalysis.parse(value).toStringWithOutNature(" ");
                //将valueTerms中分好的词分别取出存到数组中和mysql中的province和city表分别比对，如果包含在该词表中则返回词表中完整的地址
                String[] splitAddress = valueTerms.split(" ");
//bug:吉林吉林？吉林省？吉林市？
                String connect = "";
                for (String str : splitAddress) {
                    String tmp = "";
                    for (String standredProvinceName : provinceNameList) {
                        if (standredProvinceName.contains(str)) {
                            tmp = standredProvinceName;
                        }
                    }
                    if (tmp == "") {
                        for (String standredcityName : cityNameList) {
                            if (standredcityName.contains(str)) {
                                tmp = standredcityName;
                            }
                        }
                    }
                    if (tmp == "") {
                        tmp = str;
                    }
                    connect = connect + tmp;
                }
                //将当期的主键和处理后的地址名存入Map
                splitResult.put(key, connect);
                j = j + 2;
            }
            //4.插入回原phoenix表
            Iterator<Map.Entry<String, String>> entries = splitResult.entrySet().iterator();
            PhoenixUtil util = new PhoenixUtil();
            Connection connPhoenix = null;
            try {
                // get connection
                connPhoenix = util.GetConnection();

                // check connection
                if (connPhoenix == null) {
                    System.out.println("conn is null...");
                    return false;
                }
                while (entries.hasNext()) {
                    Map.Entry<String, String> entry = entries.next();
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                    upsertOneColumn(connPhoenix, tableName, columnName, entry.getKey(), entry.getValue());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (connPhoenix != null) {
                    try {
                        connPhoenix.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return true;
    }
}
