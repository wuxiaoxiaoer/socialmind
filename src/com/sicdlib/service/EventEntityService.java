package com.sicdlib.service;

import com.sicdlib.entity.EventEntity;
import com.sicdlib.util.DBUtil.DBUtil;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
