package com.sicdlib.service;

import com.sicdlib.entity.ArticleEntity;
import com.sicdlib.entity.AuthorEntity;
import com.sicdlib.entity.WebsiteEntity;
import com.sicdlib.util.DBUtil.DBUtil;
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
    public List<WebsiteEntity> findWebsite(){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select w.websiteID,w.websiteName from website w";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
               WebsiteEntity websiteEntity = new WebsiteEntity();
               websiteEntity.setWebsiteId(rs.getString(1));
               websiteEntity.setWebsiteName(rs.getString(2));
               list.add(websiteEntity);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;
        /*String hql = "select w.websiteId,w.websiteName from WebsiteEntity w";
        List<WebsiteEntity>  webs = getEntityDao().find(hql);
        return webs*/
    }

    //查找事件的网站来源比
    public List<Map> findWebsiteSource(String objectId){

        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "SELECT DISTINCT(w.websiteName),COUNT(a.websiteID) from article a,website w,author au " +
                    "where a.objectID = '"+objectId+"' and a.websiteID = w.websiteID  " +
                    "and a.authorID = au.authorID "+
                    "and a.similarDegree > (SELECT AVG(similarDegree) aver from article where objectID = '" +objectId+"') " +
                    "GROUP BY a.websiteID";
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

    //查找事件的媒体来源比
    public List<Map> findMediaSource(String objectId){

        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select d.attributeValue,COUNT(a.websiteID) from website w,data_dictionary d,article a,author au " +
                    "WHERE w.websiteID in (select a.websiteID from article a where a.objectID='" +objectId+"' GROUP BY a.websiteID) " +
                    "and a.similarDegree > (SELECT AVG(similarDegree) aver from article where objectID = '" +objectId+"') " +
                    "and a.authorID = au.authorID "+
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

    //查找事件的媒体来源信息
    public List<ArticleEntity> findMediaList(String objectId,String object){

        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select a.postTime,a.title,w.websiteName,d.attributeValue,a.articleID,a.content, " +
                    "a.scanNumber,a.participationNumber,a.likeNumber,a.recommendNumber, " +
                    "a.collectNumber,a.similarDegree,au.authorID,au.name "+
                    "from article a,website w,data_dictionary d,author au  " +
                    "where a.objectID = '"+objectId+"' "+
                    "and a.authorID = au.authorID "+
                    "and a.websiteID = w.websiteID and w.websiteTypeID = d.dataDictionaryID " +
                    "and a.similarDegree > (SELECT AVG(similarDegree) aver from article where objectID = '" +objectId+"') " +
                    "and d.attributeValue ='"+object+"' and au.authorID = a.authorID";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                ArticleEntity a = new ArticleEntity();
                a.setPostTime(rs.getString(1));
                a.setTitle(rs.getString(2));
                WebsiteEntity web = new WebsiteEntity();
                web.setWebsiteName(rs.getString(3));
                web.setWebsiteUrl(rs.getString(4));
                a.setWebsiteEntity(web);
                a.setArticleId(rs.getString(5));
                a.setContent(rs.getString(6));
                a.setScanNumber(rs.getInt(7));
                a.setParticipationNumber(rs.getInt(8));
                a.setLikeNumber(rs.getInt(9));
                a.setRecommendNumber(rs.getInt(10));
                a.setCollectNumber(rs.getInt(11));
                a.setSimilarDegree(rs.getDouble(12));
                AuthorEntity authorEntity = new AuthorEntity();
                authorEntity.setAuthorId(rs.getString(13));
                authorEntity.setName(rs.getString(14));
                a.setAuthorEntity(authorEntity);
                list.add(a);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;
    }

    //查找事件的网站信息
    public List<ArticleEntity> findWebsiteList(String objectId,String object){

        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select a.postTime,a.title,w.websiteName,a.articleID,a.content, " +
                    "a.scanNumber,a.participationNumber,a.likeNumber,a.recommendNumber, " +
                    "a.collectNumber,a.similarDegree,au.authorID,au.name "+
                    "from article a,website w,author au  " +
                    "where a.objectID = '"+objectId+"' "+
                    "and a.websiteID = w.websiteID " +
                    "and w.websiteName ='"+object+"' " +
                    "and a.similarDegree > (SELECT AVG(similarDegree) aver from article where objectID = '" +objectId+"') " +
                    "and au.authorID = a.authorID";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                ArticleEntity a = new ArticleEntity();
                a.setPostTime(rs.getString(1));
                a.setTitle(rs.getString(2));
                WebsiteEntity web = new WebsiteEntity();
                web.setWebsiteName(rs.getString(3));
                a.setWebsiteEntity(web);
                a.setArticleId(rs.getString(4));
                a.setContent(rs.getString(5));
                a.setScanNumber(rs.getInt(6));
                a.setParticipationNumber(rs.getInt(7));
                a.setLikeNumber(rs.getInt(8));
                a.setRecommendNumber(rs.getInt(9));
                a.setCollectNumber(rs.getInt(10));
                a.setSimilarDegree(rs.getDouble(11));
                AuthorEntity authorEntity = new AuthorEntity();
                authorEntity.setAuthorId(rs.getString(12));
                authorEntity.setName(rs.getString(13));
                a.setAuthorEntity(authorEntity);
                list.add(a);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;
    }

    //查找事件下的所有网站名称
    public List<String> findWebsitesByEvent(String objectId){

        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "SELECT DISTINCT(w.websiteName) from article a,website w " +
                    "where a.objectID = '" +objectId+"' " +
                    "and a.similarDegree > (SELECT AVG(similarDegree) aver from article where objectID = '" +objectId+"') " +
                    "and a.websiteID = w.websiteID ORDER BY a.postTime";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                list.add(rs.getString(1));
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;
    }

    //查找事件下某文章的来源网站
    public String findWebsitesByArticle(String objectId,String articleId){

        try {
            String result = null;
            Connection conn = new DBUtil().GetConnection();
            String sql = "SELECT DISTINCT(w.websiteName) from article a,website w " +
                    "where a.objectID = '" +objectId+"' and a.websiteID = w.websiteID and a.articleID = '" +articleId+"'";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                result = rs.getString(1);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return result;
        }catch (Exception e){

        }
        return null;
    }

    //根据文章时间找文章的来源
}
