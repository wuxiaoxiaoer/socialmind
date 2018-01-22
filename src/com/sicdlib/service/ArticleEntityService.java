package com.sicdlib.service;

import com.sicdlib.entity.*;
import com.sicdlib.util.DBUtil.DBUtil;
import com.sicdlib.util.SNAUtil.SNAUtil;
import com.sicdlib.util.UUIDUtil.UUIDUtil;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
public class ArticleEntityService extends DefaultEntityManager<ArticleEntity> {


    /**
     * @ wlw
     * 查询网站名，对象名，数量
     */
    public List<Object[]> getWebsiteObjCount(){
        String sql = "SELECT w.websiteName, o.name, o.credibility, count(a.articleID) FROM article a, object o, website w\n" +
                "WHERE a.objectID = o.objectID AND w.websiteID = a.websiteID\n" +
                "GROUP BY a.websiteID, a.objectID";
        List<Object[]> objects = getEntityDao().getSession().createSQLQuery(sql).list();
        return objects;
    }

    /**
     * @ wlw
     * 获得文章的数量
     */
    public Long getArticlesCount(){
        String hql = "SELECT COUNT(articleId) FROM ArticleEntity a";
        Long count = (Long) getEntityDao().createQuery(hql).list().get(0);
        return count;
    }

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


    //查询事件的相关文章相似度大于均值的
    public List<ArticleEntity> findArticleList(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select a.postTime,a.title,w.websiteName,a.articleID,a.content, " +
                    "a.scanNumber,a.participationNumber,a.likeNumber,a.recommendNumber, " +
                    "a.collectNumber,a.similarDegree " +
                    "from article a,website w,author au " +
                    "where a.websiteID = w.websiteID " +
//                    "and a.authorID = au.authorID " +
                    "and a.objectID = '" +objectId+"' and " +
                    "a.similarDegree > (SELECT AVG(similarDegree) aver from article where objectID = '" +objectId+"') " +
                    "order by a.postTime";
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
               /* AuthorEntity authorEntity = new AuthorEntity();
                authorEntity.setAuthorId(rs.getString(12));
                authorEntity.setName(rs.getString(13));
                a.setAuthorEntity(authorEntity);*/
               //,au.authorID,au.name
                list.add(a);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;

    }

    //根据文章ID判断作者ID是否为空---true代表不空，false代表空
    public boolean  authorIsNull(String articleId){
        try {
            boolean result = false;
            Connection conn = new DBUtil().GetConnection();
            String sql = "select ISNULL(a.authorID) from article a where a.articleID = '" +articleId+"'";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                if ("0".equals(rs.getString(1))){
                    result = true;
                    break;
                }
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return result;
        }catch (Exception e){

        }
        return false;
    }

    //根据网站名称找最早时间
    public String findFirstTimeByWeb(String webName,String objectId){
        try {
            String result = null;
            Connection conn = new DBUtil().GetConnection();
            String sql = "select a.postTime from article a,website w " +
                    "where a.objectID = '"+objectId+"' " +
                    "and a.websiteID = w.websiteID and w.websiteName = '"+webName+"' " +
                    "and a.similarDegree > (SELECT AVG(similarDegree) aver from article where objectID = '"+objectId+"') " +
                    "ORDER BY a.postTime";
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

    //根据网站名称找结束时间
    public String findEndTimeByWeb(String webName,String objectId){
        try {
            String result = null;
            Connection conn = new DBUtil().GetConnection();
            String sql = "select a.postTime from article a,website w " +
                    "where a.objectID = '"+objectId+"' " +
                    "and a.websiteID = w.websiteID and w.websiteName = '"+webName+"' " +
                    "and a.similarDegree > (SELECT AVG(similarDegree) aver from article where objectID = '"+objectId+"') " +
                    "ORDER BY a.postTime desc";
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

    //根据文章ID查询文章详情
    public List<ArticleEntity> findArticleInfo(String articleId){
        List list = new ArrayList();
        try {
            boolean isAuthorNull = authorIsNull(articleId);

            Connection conn = new DBUtil().GetConnection();
            String sql = "";
            if(isAuthorNull){
                sql = "select a.postTime,a.title,w.websiteName,a.articleID,a.content, " +
                        "a.scanNumber,a.participationNumber,a.likeNumber,a.recommendNumber, " +
                        "a.collectNumber,a.similarDegree,au.authorID,au.name " +
                        "from article a,website w,author au " +
                        "where a.websiteID = w.websiteID and a.authorID = au.authorID and a.articleID = '" +articleId+"'";
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
                    new DBUtil().closeConn(rs,psmt,conn);
                }
            }else {
                sql = "select a.postTime,a.title,w.websiteName,a.articleID,a.content, " +
                        "a.scanNumber,a.participationNumber,a.likeNumber,a.recommendNumber, " +
                        "a.collectNumber,a.similarDegree " +
                        "from article a,website w where a.websiteID = w.websiteID and a.articleID = '" +articleId+"'";
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
                    authorEntity.setAuthorId(null);
                    authorEntity.setName(null);
                    a.setAuthorEntity(authorEntity);
                    list.add(a);
                }
                new DBUtil().closeConn(rs,psmt,conn);
            }


        }catch (Exception e){

        }
        return list;

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
                    if ("".equals(a[i])||a[i]=="null"){
                        break;
                    }
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

                    }
                }
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;

    }

    //查询不敏感事件
    public List<ArticleEntity> findEventNoSensitive(List<DynamicSensitiveArticle> articles){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String pinjie = "";
            String sql = "select a.postTime,a.title,w.websiteName,a.articleID,a.content, " +
                    "a.scanNumber,a.participationNumber,a.likeNumber,a.recommendNumber, " +
                    "a.collectNumber,a.similarDegree,au.authorID,au.name " +
                    "from article a,website w,author au " +
                    "where a.websiteID = w.websiteID " +
                    "and a.authorID = au.authorID and a.articleID not in "+pinjie+"";
            if (articles.iterator().hasNext()){
                for (int i = 0; i<articles.size();i++){
                    if (i==0){
                        pinjie += "('"+articles.get(i).getArticle().getArticleId()+"',";
                    }else if (i<articles.size()-1){
                        pinjie += "'"+articles.get(i).getArticle().getArticleId()+"',";
                    }else{
                        pinjie += "'"+articles.get(i).getArticle().getArticleId()+"')";
                    }

                }
            }
            sql = sql + pinjie;
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

    //查事件的事件段
    public List findPeriod(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select DISTINCT(substring(a.postTime,1,10)) from article a where a.objectID = '"+objectId+"' " +
                    "AND a.similarDegree > (SELECT AVG(similarDegree) aver from article where objectID = '" +objectId+"') "+
                    "order by a.postTime";
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

    //统计事件下各个网站的信息量
    public List<Map> findWebsiteArticle(String objectId,String websiteId){
        try {
            List<Map> coutList = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "SELECT w.websiteName,COUNT(a.articleID) from article a,website w " +
                    "where a.objectID = '"+objectId+"' " +
                    "and a.websiteID = w.websiteID and a.websiteID = '"+websiteId+"'";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                Map map = new HashMap();
                map.put("webname",rs.getString(1));
                map.put("count",rs.getString(2));
                coutList.add(map);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return coutList;
        }catch (Exception e){
        }
        return null;
    }

    //查找事件的网站统计
    public List<Map> findWebsites(String objectId,String websiteId){
        try {
            List coutList = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "SELECT c.postTime,IFNULL(t.m,0) FROM article c LEFT OUTER JOIN (SELECT postTime,COUNT(a.articleID) m " +
                    "FROM article a,website w WHERE a.websiteID = w.websiteID AND a.objectID = '" +objectId+" '" +
                    "AND a.similarDegree > (SELECT AVG(similarDegree) aver from article where objectID = '" +objectId+"') "+
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

    //事件的每天的各网站的信息量
    public int findArticleNum(String objectId,String time){
        try {
            int result = 0;
            Connection conn = new DBUtil().GetConnection();
            String sql = "select count(a.articleID) from article a " +
                    "where a.postTime LIKE '"+time+"%' and a.objectID = '"+objectId+"' " +
                    "and a.similarDegree > (SELECT AVG(similarDegree) aver from article where objectID = '" +objectId+"')";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
               result=rs.getInt(1);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return result;
        }catch (Exception e){
        }
        return 0;
    }

    //事件的网站峰值的文章信息
    public List findMaxArticle(String objectId,String time){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select a.postTime,a.title,w.websiteName,a.articleID,a.content, " +
                    "a.scanNumber,a.participationNumber,a.likeNumber,a.recommendNumber, " +
                    "a.collectNumber,a.similarDegree,au.authorID,au.name, " +
                    "IFNULL(0.3*a.commentNumber,0)+IFNULL(0.2*a.replyNumber,0)+IFNULL(0.15*a.scanNumber,0)+IFNULL(0.05*a.participationNumber,0)+IFNULL(0.03*a.recommendNumber,0)+IFNULL(0.03*a.collectNumber,0)+IFNULL(0.03*a.likeNumber,0) as summary " +
                    "from article a,website w,author au " +
                    "where a.websiteID = w.websiteID and a.objectID = '" +objectId+"' and " +
                    "a.similarDegree > (SELECT AVG(similarDegree) aver from article where objectID = '" +objectId+"') " +
                    "and a.postTime like '"+time+"%'"+
                    "order by summary DESC LIMIT 1";

            /*String sql = "select a.title,w.websiteName,a.postTime,a.recommendNumber,a.scanNumber,a.articleID, " +
                    "IFNULL(0.3*a.commentNumber,0)+IFNULL(0.2*a.replyNumber,0)+IFNULL(0.15*a.scanNumber,0)+IFNULL(0.05*a.participationNumber,0)+IFNULL(0.03*a.recommendNumber,0)+IFNULL(0.03*a.collectNumber,0)+IFNULL(0.03*a.likeNumber,0) as summary " +
                    "from article a,website w where a.objectID = '" +objectId+" ' and a.websiteID = w.websiteID " +
                    "ORDER BY summary DESC LIMIT 1";*/
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

    //事件的热门信息推荐
    public List findHotInformation(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();

            String sql = "select a.title,w.websiteName,a.postTime,a.recommendNumber,a.scanNumber,a.articleID, " +
                    "IFNULL(0.3*a.commentNumber,0)+IFNULL(0.2*a.replyNumber,0)+IFNULL(0.15*a.scanNumber,0)+IFNULL(0.05*a.participationNumber,0)+IFNULL(0.03*a.recommendNumber,0)+IFNULL(0.03*a.collectNumber,0)+IFNULL(0.03*a.likeNumber,0) as summary " +
                    "from article a,website w where a.objectID = '" +objectId+" ' and a.websiteID = w.websiteID " +
                    "ORDER BY summary DESC LIMIT 1";
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
                article.setScanNumber(rs.getInt(5));
                article.setArticleId(rs.getString(6));
                list.add(article);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){
        }
        return null;
    }


    //获取文章标题
    public String findTitle(String articleId,String objectId){
        try {
            String result = null;
            Connection conn = new DBUtil().GetConnection();
            String sql = "select DISTINCT (a.title) from article a where a.objectID = '"+objectId+"' and a.articleID = '"+articleId+"'";
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

    //获取不同媒体下的5条热门观点
    public List finfHotOpinion(String media,String objectId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();

            String sql = "select a.title,w.websiteName,au.`name`, " +
                    "IFNULL(a.commentNumber,0)+IFNULL(a.replyNumber,0) as summary " +
                    "from article a,website w,data_dictionary d,author au " +
                    "where a.objectID ='"+objectId+"' and a.websiteID = w.websiteID " +
                    "and w.websiteTypeID = d.dataDictionaryID and d.attributeValue='"+media+"' " +
                    "and a.authorID = au.authorID ORDER BY summary DESC LIMIT 3";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                ArticleEntity article = new ArticleEntity();
                article.setTitle(rs.getString(1));
                article.setArticleId(rs.getString(2));
                /*WebsiteEntity web = new WebsiteEntity();
                web.setWebsiteId(rs.getString(2));
                article.setWebsiteEntity(web);*/
                article.setContent(rs.getString(3));
                article.setPostTime(media);
                list.add(article);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){
        }
        return null;

    }
    //查找某事件下的文章数量
    public String findArticleSumNum(String objectId){
        try {
            String result = "";
            Connection conn = new DBUtil().GetConnection();
            String sql = "select COUNT(a.articleID) from article a where a.objectID ='"+objectId+"'";
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

    //查找某事件下含有某一关键词的文章数量
    public String findOneKeywordArticleNum(String objectId,String keyword){
        try {
            String result = "";
            Connection conn = new DBUtil().GetConnection();
            String sql = "SELECT COUNT(aa.articleID) from article aa where aa.objectID ='"+objectId+"' and aa.keyWords like '%"+keyword+"%'";
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

    //查找某事件下含有两个关键词的文章数量
    public String findTwoKeywordArticleNum(String objectId,String keywordOne,String keywordTwo){
        try {
            String result = "";
            Connection conn = new DBUtil().GetConnection();
            String sql = "SELECT COUNT(aa.articleID) from article aa where aa.objectID ='"+objectId+"' " +
                    "and aa.keyWords like '%"+keywordOne+"%' and aa.keyWords like '%"+keywordTwo+"%'";
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

}
