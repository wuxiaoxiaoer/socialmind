package com.sicdlib.service;

import com.sicdlib.entity.ArticleEntity;
import com.sicdlib.entity.WebsiteEntity;
import com.sicdlib.util.DBUtil;
import edu.xjtsoft.base.orm.support.Page;
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
public class ArticleEntityService extends DefaultEntityManager<ArticleEntity> {

    /**
     * @ wlw
     *查询article表结构
     */
    public boolean getArticleTbStruct(String tableName, String columnName){
        String sql = "SELECT * FROM information_schema.COLUMNS WHERE TABLE_NAME = '" + tableName + "'\n" +
                "AND COLUMN_NAME = '" + columnName + "'";
        getEntityDao().getSession().createSQLQuery(sql).list();
        return true;
    }

    /**
     * @ wlw
     * 从hbase获得数据插入mysql ArticleEntity中
     * @param keyStr
     * @param objStr
     */
    public void insertArticleByPropAndValue(String keyStr, String objStr){
        String sql = "INSERT INTO article("+keyStr+") values ("+ objStr +")";
        getEntityDao().getSession().createSQLQuery(sql).executeUpdate();
    }

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
                    "where a.websiteID = w.websiteID and a.objectID = '" +objectId+"' order by a.postTime ";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                ArticleEntity a = new ArticleEntity();
                a.setPostTime(rs.getString(1));
                a.setTitle(rs.getString(2));

                WebsiteEntity web = new WebsiteEntity();
                web.setWebsiteId(rs.getString(3));
                a.setWebsiteEntity(web);
//                a.setWebsiteId(rs.getString(3));
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
        String hql = "select keyWords from ArticleEntity a where a.objectEntity = '" +objectId+"'";
        List<ArticleEntity> keywords = getEntityDao().find(hql);
        System.out.println(keywords);
        List<Map> list = new ArrayList();
        for(int i = 0 ; i < keywords.size() ; i++) {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("name",keywords.get(i));
            map.put("value",2500);
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
    public List<Map> findWebsites(String objectId,String time){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();

            String sql = "SELECT w.websiteName,t.num  from website w LEFT OUTER JOIN " +
                    "(select a.websiteID,COUNT(a.websiteID) num from article a WHERE a.postTime " +
                    "LIKE '" +time+"%' and a.objectID =" +objectId+" group by a.websiteID) t on w.websiteID= t.websiteID";
//            String sql1 = "select w.websiteName,COUNT(a.articleID) from article a,website w where a.websiteID = w.websiteID
// and a.postTime LIKE '" +time+"%' and a.objectID=" +objectId+" GROUP BY a.postTime";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                Map map = new HashMap();
                map.put("time",time);
                map.put("name",rs.getString(1));
                map.put("type","line");
                map.put("stack","发布文章的数量");
                /*ArrayList al = new ArrayList();
                al.add(rs.getString(2));*/
                map.put("websiteNum",rs.getInt(2));
                list.add(map);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
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
                    "article a,website w where a.objectID = 1 and a.websiteID = w.websiteID " +
                    "ORDER BY a.recommendNumber desc LIMIT 4";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                ArticleEntity article = new ArticleEntity();
                article.setTitle(rs.getString(1));
                WebsiteEntity web = new WebsiteEntity();
                web.setWebsiteId(rs.getString(2));
                article.setWebsiteEntity(web);
//                article.setWebsiteId(rs.getString(2));
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

}
