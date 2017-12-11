package com.sicdlib.service;

import com.sicdlib.entity.ArticleEntity;
import com.sicdlib.entity.ArticleSimilarEntity;
import com.sicdlib.util.DBUtil.DBUtil;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ArticleSimilarityService  extends DefaultEntityManager<ArticleSimilarEntity> {
    /**
     * 方法：添加某个事件下所有文章的相似度
     */

    public int addEventArticleSimi(ArticleSimilarEntity article) {
        try {

            Connection conn = new DBUtil().GetConnection();
            String sql = "INSERT INTO article_similar (articleSimilarID,articleOneID,articleTwoID,similarDegree) VALUES (?,?,?,?);";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1,article.getArticleSimilarId());
            psmt.setString(2,article.getArticleEntityOne().getArticleId());
            psmt.setString(3,article.getArticleEntityTwo().getArticleId());
            psmt.setDouble(4,article.getSimilarDegree());
            int result = psmt.executeUpdate();
            System.out.println("result:"+result);
            new DBUtil().closeConn(null,psmt,conn);
            return result;
        }catch (Exception e){
        }
        return 0;
    }

    //判断事件是否抽取过文章的相似度
    public List<ArticleSimilarEntity> findEventSimilar(String objectId) {
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select aa.articleSimilarID,aa.articleOneID,aa.articleTwoID,aa.similarDegree from article_similar aa " +
                    "where aa.articleOneID in (SELECT articleID from article where objectID = '" +objectId+" ') limit 1000";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                ArticleSimilarEntity similarEntity = new ArticleSimilarEntity();
                similarEntity.setArticleSimilarId(rs.getString(1));
                ArticleEntity articleEntity1 = new ArticleEntity();
                articleEntity1.setArticleId(rs.getString(2));
                ArticleEntity articleEntity2 = new ArticleEntity();
                articleEntity2.setArticleId(rs.getString(3));
                similarEntity.setArticleEntityOne(articleEntity1);
                similarEntity.setArticleEntityTwo(articleEntity2);
                similarEntity.setSimilarDegree(rs.getDouble(4));
                list.add(similarEntity);

            }

            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){
        }
        return null;
    }

    //查找某事件下的文章相似度
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
