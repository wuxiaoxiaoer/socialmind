package com.sicdlib.service;

import com.sicdlib.entity.EventEntity;
import com.sicdlib.entity.ObjectEntity;
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
public class ObjectEntityService extends DefaultEntityManager<ObjectEntity> {

    /**
     * 获得舆情对象列表
     * @param objectType      舆情对象类型  事件/实体
     * @param addTime         开始时间      2017-11-07 15:11:00
     * @return
     */
    public List<ObjectEntity> getObjectIndicators(String objectType, String place, String addTime){
        String hql = "from ObjectEntity o where o.objectType = '"+ objectType +  "' and place like '%"+ place +"' and o.addTime > '"+ addTime +"'";
        List<ObjectEntity> objects = getEntityDao().find(hql);
        return objects;
    }

    //前台查找对象
    public List<ObjectEntity> findObject(){
        String hql = "from ObjectEntity o";
        List<ObjectEntity> object = getEntityDao().find(hql);
        return object;
    }

    //前台查找对象
    public List<ObjectEntity> findObjectInfo(String objectId){
        String hql = "from ObjectEntity o where o.objectId = '" +objectId+"'";
        List<ObjectEntity> objectInfo = getEntityDao().find(hql);
        return objectInfo;
    }

    //前台根据关键字模糊查找对象
    public List<ObjectEntity> findObject(String object){
        String hql = "from ObjectEntity o where o.name like '%" +object+"%'";
        List<ObjectEntity> objectInfo = getEntityDao().find(hql);
        return objectInfo;
    }

    //后台查找实体总数
    public List<String> findObjectList(){
        try {
            List<String> result = new ArrayList<>();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select name from object o where o.objectType = '实体' ";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                result.add(rs.getString(1));
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return result;
        }catch (Exception e){
        }
        return null;
    }

    //后台查找所有事件的类型
    public List<Map> objectType(){
        try {
            List<Map> list = new ArrayList<>();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select name,objectID from object o where ISNULL(o.objectFatherID)";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                Map map = new HashMap();
                map.put("name",rs.getString(1));
                map.put("objectID",rs.getString(2));
                list.add(map);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){
        }
        return null;

    }

    //查询不同类型的事件数量
    public String objectNumofType(String objectId){
        try {
            Connection conn = new DBUtil().GetConnection();
            String sql = "select count(o.name) from object o where o.objectFatherID = '"+objectId+"' ";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                return rs.getString(1);
            }

        }catch (Exception e){
        }
        return null;

    }

    //不同类型的事件
    public List<Map> eventOfType(){
        try {
            List<Map> list = new ArrayList<>();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select a.name,o.name,o.objectID from object o,(select name,objectID from object o where ISNULL(o.objectFatherID)) a " +
                    "where o.objectFatherID = a.objectID";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                Map map = new HashMap();
                map.put("type",rs.getString(1));
                map.put("eventName",rs.getString(2));
                map.put("objectID",rs.getString(3));
                list.add(map);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){
        }
        return null;
    }


}
