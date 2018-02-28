package com.sicdlib.service;

import com.sicdlib.entity.EventEntity;
import com.sicdlib.entity.KeywordRelatedDegreeEntity;
import com.sicdlib.util.DBUtil.DBUtil;
import com.sicdlib.util.UUIDUtil.UUIDUtil;
import org.junit.Test;
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
public class EventOpinionService {

    //查询事件的观点
    public List<Map> findEventOpinion(String objectId){

        try {
            List<Map> list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "SELECT eo.content,w.websiteName " +
                    "from event_opinion eo,article a,website w " +
                    "WHERE eo.objectID = '"+objectId+"' " +
                    "and a.articleID = eo.articleID " +
                    "and a.websiteID = w.websiteID " +
                    "ORDER BY eo.weight " +
                    "DESC LIMIT 5 ";
            /*String sql = "SELECT eo.content,w.websiteName,d.attributeName " +
                    "from event_opinion eo,article a,website w,data_dictionary d " +
                    "WHERE eo.objectID = '"+objectId+"' " +
                    "and a.articleID = eo.articleID " +
                    "and a.websiteID = w.websiteID " +
                    "and w.websiteID = d.dataDictionaryID  " +
                    "ORDER BY eo.weight " +
                    "DESC LIMIT 5 ";*/
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                Map map = new HashMap();
                map.put("content",rs.getString(1));
                map.put("websiteName",rs.getString(2));
//                map.put("media",rs.getString(3));
                list.add(map);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;
    }


    //新增事件的观点
    public int addEventOpinion(String objectId, List<Map> opinionList){
        int result = 0;
        try {

            Connection conn = new DBUtil().GetConnection();
            String sql = "INSERT INTO event_opinion (opinionID,objectID,articleID,content,direct_weight,place_weight,similar_title,weight) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement psmt = conn.prepareStatement(sql);
            for (int i = 0;i<opinionList.size();i++){

                double wordweight = Double.parseDouble(opinionList.get(i).get("wordweight").toString());
                double placeweight = Double.parseDouble(opinionList.get(i).get("placeweight").toString());
                double similar_title = Double.parseDouble(opinionList.get(i).get("similar_title").toString());
                double weight = wordweight+placeweight+similar_title;
                if (similar_title==0){
                    weight=0;
                }
                psmt.setString(1, UUIDUtil.getUUID());
                psmt.setString(2,objectId);
                psmt.setString(3,opinionList.get(i).get("articleId").toString());
                psmt.setString(4,opinionList.get(i).get("content").toString());
                psmt.setDouble(5,wordweight);
                psmt.setDouble(6,placeweight);
                psmt.setDouble(7,similar_title);
                psmt.setDouble(8,weight);
                result =  psmt.executeUpdate();
            }

            new DBUtil().closeConn(null,psmt,conn);
        }catch (Exception e){
        }
        return result;
    }


    }


