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
import java.util.List;

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

    //前台查找事件列表
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

}
