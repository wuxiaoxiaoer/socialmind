package com.sicdlib.service;

import com.sicdlib.entity.ArticleEntity;
import com.sicdlib.entity.WebsiteEntity;
import com.sicdlib.util.DBUtil;
import com.sicdlib.util.UUIDUtil.UUIDUtil;
import edu.xjtsoft.base.orm.support.Page;

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
public class ArticleEntityService extends DefaultEntityManager<ArticleEntity> {

    public Page<ArticleEntity> getConditionArticles(Map<String, Object> map){
        String hql = "from ArticleEntity a where a.objectEntity.objectId = '"+map.get("objectId")+"'";
        String pinjie = "";
        if (map.get("websiteIds") != null){
            String [] websiteIds = (String[]) map.get("websiteIds");
            String temp = "";
            for (int i = 0; websiteIds != null && i < websiteIds.length; i++){
                if (i != websiteIds.length-1){
                    temp += websiteIds[i] +", ";
                }else {
                    temp += websiteIds[i] +" ";
                }
            }

            pinjie += " and a.websiteEntity.websiteId in (" + temp + ")";
        }
        if (map.get("postTime") != null){
            pinjie += " and a.postTime >= '" + map.get("postTime") + "'";
        }
        if (map.get("similarDegree") != null){
            pinjie += " and a.similarDegree >= " + map.get("similarDegree");
        }
        if (map.get("scanNumber") != null){
            pinjie += " and a.scanNumber >= " + map.get("scanNumber");
        }
        if (map.get("likeNumber") != null){
            pinjie += " and a.likeNumber >= " + map.get("likeNumber");
        }
        if (map.get("commentNumber") != null){
            pinjie += " and a.commentNumber >= " + map.get("commentNumber");
        }
        if (map.get("collectNumber") != null){
            pinjie += " and a.collectNumber >= " + map.get("collectNumber");
        }
        hql = hql + pinjie;
        System.out.println(hql);
        Page page = new Page(20);
        page.setPageNo(1);
        Page<ArticleEntity> articlesPage = getEntityDao().find(page, hql);
        return articlesPage;
    }


    //查询事件的相关文章
    public List<ArticleEntity> findArticleList(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select a.postTime,a.title,w.websiteName from article a,website w " +
                    "where a.websiteID = w.websiteID and a.objectID = '" +objectId+"' order by a.postTime";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                ArticleEntity a = new ArticleEntity();
                a.setPostTime(rs.getString(1));
                a.setTitle(rs.getString(2));
                WebsiteEntity web = new WebsiteEntity();
                web.setWebsiteId(rs.getString(3));
                a.setWebsiteEntity(web);
                list.add(a);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;

    }

    //查找事件的关键词
    public List<Map> findKeywords(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select a.keyWords from article a where a.objectId = '" +objectId+"'";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                String origin = rs.getString(1);
                String[] a = origin.split(",");
                for (int i=0; i<a.length;i++){
                    String sql1 = "SELECT k.weight from keyword k where k.objectID = '" +objectId+"' and k.keyword = '" +a[i]+"'";
                    PreparedStatement psmt1 = conn.prepareStatement(sql1);
                    ResultSet rs1 = psmt1.executeQuery(sql1);
                    boolean isExist = false;
                    while (rs1.next()){
                        System.out.println(rs1.getString(1));
                        isExist = true;
                        //keyword表中修改对象的关键词的权重信息
                        String sql3 = "UPDATE keyword set weight = ? where objectID = ? and keyword = ?";
                        PreparedStatement psmt3 = conn.prepareStatement(sql3);
                        psmt3.setDouble(1,rs1.getDouble(1)+1);
                        psmt3.setString(2,objectId);
                        psmt3.setString(3,a[i]);
                        psmt3.executeUpdate();
                    }
                    if (isExist == false){
                        //keyword表中新增对象，关键词，权重信息
                        String sql2 = "INSERT INTO keyword (keywordID,objectID,keyword,weight) VALUES (?,?,?,?);";
                        PreparedStatement psmt2 = conn.prepareStatement(sql2);
                        psmt2.setString(1, UUIDUtil.getUUID());
                        psmt2.setString(2,objectId);
                        psmt2.setString(3,a[i]);
                        psmt2.setInt(4,1);
                        int result = psmt2.executeUpdate();
                        System.out.println(result);
                    }
                }
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;

    }

    //查事件的事件段
    public List findPeriod(String objectId){
        String hql = "select DISTINCT(substring(a.postTime,1,10)) from ArticleEntity a where a.objectEntity = '" +objectId+"' order by a.postTime";
        List period = getEntityDao().find(hql);
        return period;
    }

    //查找事件的网站统计
    public List<Map> findWebsites(String objectId,String websiteId,String websiteName){
        try {
            List coutList = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "SELECT c.postTime,IFNULL(t.m,0) FROM article c LEFT OUTER JOIN (SELECT postTime,COUNT(a.articleID) m " +
                    "FROM article a,website w WHERE a.websiteID = w.websiteID AND a.objectID = '" +objectId+" '" +
                    "AND a.websiteID = "+websiteId+" GROUP BY a.postTime) t on c.postTime = t.postTime";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                coutList.add(rs.getString(2));
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return coutList;
        }catch (Exception e){
        }
        return null;
    }

    //事件的热门信息推荐
    public List findHotInformation(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();

            String sql = "select a.title,w.websiteName,a.postTime,a.recommendNumber from " +
                    "article a,website w where a.objectID = '" +objectId+" ' and a.websiteID = w.websiteID " +
                    "ORDER BY a.recommendNumber desc LIMIT 4";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                ArticleEntity article = new ArticleEntity();
                article.setTitle(rs.getString(1));
                WebsiteEntity web = new WebsiteEntity();
                web.setWebsiteId(rs.getString(2));
                article.setWebsiteEntity(web);
                article.setPostTime(rs.getString(3));
                article.setRecommendNumber(rs.getInt(4));
                list.add(article);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){
        }
        return null;
    }

    //事件的媒体转发数
    //查事件的事件段
    public List findTransferNum(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select COUNT(a.articleID) from article a where a.objectID ="+objectId+"";
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

}
