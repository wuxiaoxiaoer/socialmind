package com.sicdlib.service;

import com.sicdlib.entity.KeywordEntity;
import com.sicdlib.entity.KeywordRelatedDegreeEntity;
import com.sicdlib.util.DBUtil.DBUtil;
import com.sicdlib.util.UUIDUtil.UUIDUtil;
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
public class KeywordRelatedDegreeService extends DefaultEntityManager<KeywordRelatedDegreeEntity> {
    //查找事件的关键词关联度
    public List<KeywordRelatedDegreeEntity> findKeywordRelated(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select k.keyword,kr.relatedDegree,k.weight from keyword_related_degree kr,keyword k " +
                    "where kr.keywordOneID IN (select k.keywordID from keyword k WHERE k.objectID ='"+objectId+"') " +
                    "and kr.keywordTwoID = k.keywordID";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
               KeywordRelatedDegreeEntity keywordRelatedDegreeEntity = new KeywordRelatedDegreeEntity();
               KeywordEntity keywordEntity = new KeywordEntity();
               keywordEntity.setKeyword(rs.getString(1));
               keywordRelatedDegreeEntity.setRelatedDegree(rs.getDouble(2));
               keywordEntity.setWeight(rs.getDouble(3));
                keywordRelatedDegreeEntity.setKeywordEntityOne(keywordEntity);
               list.add(keywordRelatedDegreeEntity);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){
        }
        return null;
    }


    //在关键词关联度表中添加事件关联度
    public int insertKeywordRelated(KeywordRelatedDegreeEntity object){
        try {
            Connection conn = new DBUtil().GetConnection();
            String sql2 = "INSERT INTO keyword_related_degree (keywordDegreeID,keywordOneID,keywordTwoID,relatedDegree) VALUES (?,?,?,?)";
            PreparedStatement psmt2 = conn.prepareStatement(sql2);
            psmt2.setString(1, object.getKeywordDegreeId());
            psmt2.setString(2, object.getKeywordEntityOne().getKeywordId());
            psmt2.setString(3, object.getKeywordEntityTwo().getKeywordId());
            psmt2.setDouble(4,object.getRelatedDegree());
            int result = psmt2.executeUpdate();
            new DBUtil().closeConn(null,psmt2,conn);
            return result;
        }catch (Exception e){
        }
        return 0;
    }

    /*public KeywordEntity countSimilarCore(String objectId){
        try {
//            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "SELECT MAX(k.weight),k.keyword,k.keywordID from keyword k where k.objectID = '"+objectId+"'";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            KeywordEntity keywordEntity = new KeywordEntity();
            while (rs.next()){
                keywordEntity.setWeight(rs.getDouble(1));
                keywordEntity.setKeyword(rs.getString(2));
                keywordEntity.setKeywordId(rs.getString(3));
//                list.add(keywordEntity);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return keywordEntity;
        }catch (Exception e){
        }
        return null;
    }*/

}
