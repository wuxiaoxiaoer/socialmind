package com.sicdlib.service;

import com.sicdlib.entity.ArticleCommentEntity;
import com.sicdlib.util.DBUtil;
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
public class ArticleCommentEntityService extends DefaultEntityManager<ArticleCommentEntity>{
    //查找网民观点
    public List findArticleComment(String authorId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select w.websiteName,ac.content from article_comment ac,article a,website w " +
                    "where ac.authorID = "+authorId+" and ac.articleID= a.articleID and w.websiteID = a.websiteID " +
                    "ORDER BY (ac.replayNumber+ac.likeNumber) desc limit 5";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                ArticleCommentEntity articleComment = new ArticleCommentEntity();
                articleComment.setSourceCommentId(rs.getString(1));
                articleComment.setContent(rs.getString(2));
                list.add(articleComment);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;
    }
}
