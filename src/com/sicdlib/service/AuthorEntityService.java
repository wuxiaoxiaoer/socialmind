package com.sicdlib.service;

import com.sicdlib.entity.AuthorEntity;
import com.sicdlib.util.DBUtil.DBUtil;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AuthorEntityService extends DefaultEntityManager<AuthorEntity> {
    //事件的热点网民
    public List<AuthorEntity> findHotAuthor(String objectId){
        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            Statement statement = conn.createStatement();
            String sql = "select au.authorId,au.name,au.elitePostNumber from author au " +
                    "where au.authorID in " +
                    "(select DISTINCT(a.authorID) from article a where a.objectID = '" +objectId+"') " +
                    "ORDER BY au.elitePostNumber desc limit 4";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                AuthorEntity authorEntity = new AuthorEntity();
                authorEntity.setAuthorId(rs.getString(1));
                authorEntity.setName(rs.getString(2));
                authorEntity.setElitePostNumber(rs.getInt(3));
                list.add(authorEntity);
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @ wlw
     * 根据author拼接的属性和值 插入数据
     * @param keyStr 属性
     * @param objStr 值
     */
    public void insertAuthorByPropAndValue(String keyStr, String objStr){
        String sql = "INSERT INTO author("+keyStr+") values ("+ objStr +")";
        getEntityDao().getSession().createSQLQuery(sql).executeUpdate();
    }
}
