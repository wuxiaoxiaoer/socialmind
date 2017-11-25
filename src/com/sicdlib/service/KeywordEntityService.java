package com.sicdlib.service;

import com.sicdlib.entity.KeywordEntity;
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
public class KeywordEntityService extends DefaultEntityManager<KeywordEntity> {
    //在关键词表中查询事件的关键词与权重
    public List<Map> findKeywords(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select keyword,weight,keywordId from keyword k where k.objectID = "+objectId+" ORDER BY weight DESC";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                Map map = new HashMap();
                map.put("name",rs.getString(1));
                map.put("value",rs.getString(2));
                map.put("keywordId",rs.getString(3));
                list.add(map);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){
        }
        return null;
    }
}
