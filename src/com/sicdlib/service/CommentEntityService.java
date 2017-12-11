package com.sicdlib.service;

import com.sicdlib.entity.CommentEntity;
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
public class CommentEntityService extends DefaultEntityManager<CommentEntity> {
    //查找网民观点
    public List<CommentEntity> findPeopleComment(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            String sql = "select c.commentContent from comment c where c.objectID = "+objectId+" limit 1";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                CommentEntity comment = new CommentEntity();
                comment.setCommentContent(rs.getString(1));
                list.add(comment);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return list;
        }catch (Exception e){

        }
        return null;
    }
}
