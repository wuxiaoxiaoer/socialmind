package com.sicdlib.service;

import com.sicdlib.entity.KeywordRelatedDegreeEntity;
import com.sicdlib.util.DBUtil;
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
    public List<Map> findKeywordRelated(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select kr.keywordOneID,kr.keywordTwoID,kr.relatedDegree from keyword_related_degree kr " +
                    "where kr.keywordOneID IN (select k.keywordID from keyword k WHERE k.objectID ="+objectId+")";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                Map map = new HashMap();
                map.put("target",rs.getString(1));
                map.put("source",rs.getString(2));
                map.put("weight",rs.getString(3));
                list.add(map);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){
        }
        return null;
    }
}
