package com.sicdlib.service;

import com.sicdlib.entity.EventEntity;
import com.sicdlib.util.DBUtil.DBUtil;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class EventEntityService extends DefaultEntityManager<EventEntity> {

    //前台查找事件列表
    public List<EventEntity> findEvent(){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "SELECT e.objectID,e.eventBeginTime,e.eventEndTime,e.eventSummary,e.introduction " +
                    "from object o,`event` e where o.objectType = \"事件\" " +
                    "and o.objectID = e.objectID ORDER BY e.eventBeginTime desc";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                EventEntity event = new EventEntity();
                event.setObjectId(rs.getString(1));
                event.setEventBeginTime(rs.getString(2));
                event.setEventEndTime(rs.getString(3));
                event.setEventSummary(rs.getString(4));
                event.setIntroduction(rs.getString(5));
                list.add(event);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;
    }

    //前台根据对象ID查找事件
    public List<EventEntity> findEventById(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "SELECT e.objectID,e.eventBeginTime,e.eventEndTime,e.eventSummary,e.introduction " +
                    "from object o,`event` e where o.objectType = \"事件\" " +
                    "and o.objectID = e.objectID and o.objectID = '"+objectId+"' ORDER BY e.eventBeginTime desc";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                EventEntity event = new EventEntity();
                event.setObjectId(rs.getString(1));
                event.setEventBeginTime(rs.getString(2));
                event.setEventEndTime(rs.getString(3));
                event.setEventSummary(rs.getString(4));
                event.setIntroduction(rs.getString(5));
                list.add(event);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;
    }


    public List<EventEntity> findEventInfo(String objectId){
        String hql = "from EventEntity e where e.objectId = '" +objectId+"'";
        List<EventEntity> eventInfo = getEntityDao().find(hql);
        return eventInfo;
    }

    //后台统计新增事件数量
    public List<Map> eventByYear(){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select SUBSTR(o.addTime,1,4) as time,COUNT(SUBSTR(o.addTime,1,4)) " +
                    "from object o,`event` e where o.objectType=\"事件\" " +
                    "and o.objectID = e.objectID GROUP BY time;";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                Map map = new HashMap();
                map.put("time",rs.getString(1));
                map.put("count",rs.getString(2));
                list.add(map);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;
    }

    //后台根据年和月份查询事件数量
    public String eventByYearAndMonth(String year,int month){
        try {
            Connection conn = new DBUtil().GetConnection();
            String sql = "select count(o.objectID) from object o,event e where o.objectType=\"事件\" " +
                    "and o.objectID = e.objectID and o.addTime like '"+year+"-"+month+"-"+"%' ";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
               return rs.getString(1);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return null;
        }catch (Exception e){

        }
        return null;
    }

    //后台根据年和月份和日期查询事件数量
    public String eventByYearAndMonthAndDay(String year,String month,String day){
        try {
            Connection conn = new DBUtil().GetConnection();
            String sql = "select count(o.objectID) from object o,event e where o.objectType=\"事件\" " +
                    "and o.objectID = e.objectID and o.addTime like '"+year+"-"+month+"-"+day+"%' ";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                return rs.getString(1);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return null;
        }catch (Exception e){

        }
        return null;
    }

    //事件页面访问量的添加
    public int addClick(String objectId){
        try {
            Connection conn = new DBUtil().GetConnection();
            String sql2 = "INSERT INTO click_event (objectID,clickNum) VALUES (?,?)";
            PreparedStatement psmt2 = conn.prepareStatement(sql2);
            psmt2.setString(1, objectId);
            psmt2.setInt(2, 1);
            int result = psmt2.executeUpdate();
            new DBUtil().closeConn(null,psmt2,conn);
            return result;
        }catch (Exception e){

        }
        return 0;
    }

    //事件是否已经向数据库中添加了访问量
    public String isAddClickNum(String objectId){
        try {
            Connection conn = new DBUtil().GetConnection();
            String sql = "select c.clickNum from click_event c,object o where c.objectID = '"+objectId+"' and o.objectID = c.objectID";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);

            while (rs.next()){
                return rs.getString(1);
            }


        }catch (Exception e){

        }

        return null;
    }

    //修改事件页面访问量
    public int updateClick(String objectId){
        try {
            Connection conn = new DBUtil().GetConnection();
            String sql2 = "update click_event set clickNum = clickNum+1 where objectID = '"+objectId+"'";
            PreparedStatement psmt2 = conn.prepareStatement(sql2);
            int result = psmt2.executeUpdate();
            new DBUtil().closeConn(null,psmt2,conn);
            return result;
        }catch (Exception e){

        }
        return 0;
    }

    //事件是否已经向数据库中添加了访问量
    public List<Map> allClickNum(){
        try {
            List<Map> list = new ArrayList<>();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select o.name,c.clickNum,o.objectID from click_event c,object o where o.objectID = c.objectID";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);

            while (rs.next()){
                Map map = new HashMap();
                map.put("name",rs.getString(1));
                map.put("value",rs.getString(2));
//                map.put("objectID",rs.getString(3));
                list.add(map);
            }
            return list;

        }catch (Exception e){

        }

        return null;
    }

    //所有事件的时间差
    public List<Map> eventPeriod(){
        try {
            List<Map> list = new ArrayList<>();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select o.name,e.eventBeginTime,e.eventEndTime,e.introduction,o.objectID  from object o,event e " +
                    "where o.objectID = e.objectID and o.objectType = \"事件\"";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);

            while (rs.next()){
                Map map = new HashMap();
                map.put("name",rs.getString(1));
                map.put("beginTime",rs.getString(2));
                map.put("endTime",rs.getString(3));
                map.put("introduction",rs.getString(4));
                map.put("objectID",rs.getString(5));
                map.put("num",subTimeDay(rs.getString(2),rs.getString(3)));
                list.add(map);
            }
            return list;

        }catch (Exception e){

        }

        return null;

    }

    //求时间差
    public String subTimeDay(String time1,String time2){
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin= null;
        Date end = null;
        try {
            begin = dfs.parse(time1);
            end = dfs.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
        long day1=between/(24*3600);
        String time = day1+"";
        return time;
    }

    //根据事件名称查找事件ID
    public String findEventID(String eventName){
        try {
            Connection conn = new DBUtil().GetConnection();
            String sql = "select o.objectID from object o where o.`name` = '"+eventName+"'";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                return rs.getString(1);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return null;
        }catch (Exception e){

        }
        return null;
    }

}
