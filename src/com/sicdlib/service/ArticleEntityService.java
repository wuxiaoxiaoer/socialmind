package com.sicdlib.service;

import com.alibaba.fastjson.JSON;
import com.sicdlib.entity.ArticleEntity;
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
public class ArticleEntityService extends DefaultEntityManager<ArticleEntity> {
    //查询事件的相关文章
    public List<ArticleEntity> findArticleList(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = GetConnection();
            Statement statement = conn.createStatement();
            String sql = "select a.postTime,a.title,w.websiteName from article a,website w " +
                    "where a.websiteID = w.websiteID and a.objectID = '" +objectId+"' order by a.postTime ";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ArticleEntity a = new ArticleEntity();
                a.setPostTime(rs.getString(1));
                a.setTitle(rs.getString(2));
                a.setWebsiteId(rs.getString(3));
                list.add(a);
            }
            return list;
        }catch (Exception e){

        }
        return null;

    }

    //查找事件的关键词
    public List<Map> findKeywords(String objectId){
        String hql = "select keyWords from ArticleEntity a where a.objectEntity = '" +objectId+"'";
        List<ArticleEntity> keywords = getEntityDao().find(hql);
        System.out.println(keywords);
        List<Map> list = new ArrayList();
        for(int i = 0 ; i < keywords.size() ; i++) {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("name",keywords.get(i));
            map.put("value",1);
            list.add(map);
        }

        return list;
    }

    //查事件的事件段
    public List findPeriod(String objectId){
        String hql = "select DISTINCT(substring(a.postTime,1,10)) from ArticleEntity a where a.objectEntity = '" +objectId+"' order by a.postTime";
        List period = getEntityDao().find(hql);
        System.out.println("service中的时间段："+period.toString());
        return period;
    }

    //查找事件的网站统计
    public List findWebsites(String objectId,String time){
        try {
            List list = new ArrayList();
            Connection conn = GetConnection();
            Statement statement = conn.createStatement();
            String sql = "SELECT w.websiteName,t.num  from website w LEFT OUTER JOIN " +
                    "(select a.websiteID,COUNT(a.websiteID) num from article a WHERE a.postTime " +
                    "LIKE '" +time+"%' and a.objectID =" +objectId+" group by a.websiteID) t on w.websiteID= t.websiteID";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                Map map = new HashMap();
                map.put("time",time);
                map.put("websiteName",rs.getString(1));
                map.put("websiteNum",rs.getInt(2));
                list.add(map);
            }

            return list;
        }catch (Exception e){

        }
        return null;
    }

    //事件的热门信息推荐
    public List findHotInformation(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = GetConnection();
            Statement statement = conn.createStatement();
            String sql = "select a.title,w.websiteName,a.postTime,a.recommendNumber from " +
                    "article a,website w where a.objectID = 1 and a.websiteID = w.websiteID " +
                    "ORDER BY a.recommendNumber desc LIMIT 4";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ArticleEntity article = new ArticleEntity();
                article.setTitle(rs.getString(1));
                article.setWebsiteId(rs.getString(2));
                article.setPostTime(rs.getString(3));
                article.setRecommendNumber(rs.getInt(4));
                list.add(article);
            }

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
