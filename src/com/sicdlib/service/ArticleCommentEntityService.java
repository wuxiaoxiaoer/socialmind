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
    public List<ArticleCommentEntity> findArticleComment(String articleId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "SELECT ac.content from article_comment ac " +
                    "where ac.authorID = "+articleId+" ORDER BY (ac.replayNumber+ac.likeNumber) desc limit 1";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                ArticleCommentEntity articleComment = new ArticleCommentEntity();
                articleComment.setContent(rs.getString(1));
                list.add(articleComment);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;
    }
}
