package com.sicdlib.service;

import com.sicdlib.entity.WebsiteEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WebsiteEntityService extends DefaultEntityManager<WebsiteEntity> {
    //查找website表中所有的网址
    public List findWebsite(){
        String hql = "select w.websiteName from WebsiteEntity w";
        List<WebsiteEntity>  webs = getEntityDao().find(hql);
        System.out.println("service中的website集合"+webs.toString());
        return webs;
    }

    //查找事件的媒体来源比
    public List findMediaSource(String objectId){

        try {
            List list = new ArrayList();
            Connection conn = GetConnection();
            Statement statement = conn.createStatement();
            String sql = "select d.attributeValue,COUNT(w.websiteTypeID) from website w,data_dictionary d " +
                    "WHERE w.websiteID in (select a.websiteID from article a where a.objectID='" +objectId+"' GROUP BY a.websiteID) " +
                    "AND w.websiteTypeID=d.dataDictionaryID GROUP BY d.attributeValue";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                Map map = new HashMap();
                map.put("name",rs.getString(1));
                map.put("value",rs.getInt(2));
                list.add(map);
            }
            System.out.println(list.get(0).toString());
            return list;
        }catch (Exception e){

        }
        return null;
    }

    public Connection GetConnection(){
        Connection conn = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.1.249:3306/socialmind";
        String user = "root";
        String password = "root";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
