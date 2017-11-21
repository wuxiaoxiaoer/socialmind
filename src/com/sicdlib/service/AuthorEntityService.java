package com.sicdlib.service;

import com.sicdlib.entity.ArticleEntity;
import com.sicdlib.entity.AuthorEntity;
import com.sicdlib.util.DBUtil;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AuthorEntityService extends DefaultEntityManager<AuthorEntity> {
    //事件的热点网民
    public List<AuthorEntity> findHotAuthor(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            Statement statement = conn.createStatement();
            String sql = "select au.name,au.elitePostNumber from author au " +
                    "where au.authorID in " +
                    "(select DISTINCT(a.authorID) from article a where a.objectID = '" +objectId+"') " +
                    "ORDER BY au.elitePostNumber desc limit 4";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                AuthorEntity authorEntity = new AuthorEntity();
                authorEntity.setName(rs.getString(1));
                authorEntity.setElitePostNumber(rs.getInt(2));
                list.add(authorEntity);
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
