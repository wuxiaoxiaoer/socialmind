//package com.sicdlib.util.PhoenixUtil;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Types;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class GetList {
//
//    public List<Map<String, Object>> getListFromRs(ResultSet rs) throws SQLException {
//        ResultSetMetaData md = rs.getMetaData();//得到结果集列的属性
//        int columns = md.getColumnCount();//得到记录有多少列
//        int i;
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        while(rs.next()){
//            Map<String, Object> map = new HashMap<String, Object>();
//            for(i = 0; i < columns; i++){
//                map.put(md.getColumnName(i + 1), getValueByType(rs, md.getColumnType(i + 1), md.getColumnName(i + 1)));
//            }
//            list.add(map);
//        }
//        return list;
//    }
//
//    private static Object getValueByType(ResultSet rs, int type, String name) throws SQLException{
//        switch(type){
//            case Types.NUMERIC:
//                return rs.getLong(name);
//            case Types.VARCHAR:
//                //if(rs.getString(name)==null){
//                //return "";
//                //}
//                return rs.getString(name);
//            case Types.DATE:
//                //if(rs.getDate(name)==null){
//                //return System.currentTimeMillis();
//                //  }
//                return rs.getDate(name);
//            case Types.TIMESTAMP:
//                return rs.getTimestamp(name).toString().substring(0,rs.getTimestamp(name).toString().length()-2);
//            case Types.INTEGER:
//                return rs.getInt(name);
//            case Types.DOUBLE:
//                return rs.getDouble(name);
//            case Types.FLOAT:
//                return rs.getFloat(name);
//            case Types.BIGINT:
//                return rs.getLong(name);
//            default:
//                return rs.getObject(name);
//        }
//    }
//}
