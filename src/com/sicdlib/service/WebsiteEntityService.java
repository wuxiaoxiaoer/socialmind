package com.sicdlib.service;

import com.sicdlib.entity.WebsiteEntity;
import com.sicdlib.util.DBUtil;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WebsiteEntityService extends DefaultEntityManager<WebsiteEntity> {
    //查找website表中所有的网址
    public List findWebsite(){
        String hql = "select w.websiteId,w.websiteName from WebsiteEntity w";
        List<WebsiteEntity>  webs = getEntityDao().find(hql);
        return webs;
    }

    //查找事件的媒体来源比
    public List findMediaSource(String objectId){

        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select d.attributeValue,COUNT(w.websiteTypeID) from website w,data_dictionary d " +
                    "WHERE w.websiteID in (select a.websiteID from article a where a.objectID='" +objectId+"' GROUP BY a.websiteID) " +
                    "AND w.websiteTypeID=d.dataDictionaryID GROUP BY d.attributeValue";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                Map map = new HashMap();
                map.put("name",rs.getString(1));
                map.put("value",rs.getInt(2));
                list.add(map);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;
    }
}
