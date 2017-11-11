package com.sicdlib.service;

import com.sicdlib.entity.ArticleEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArticleEntityService extends DefaultEntityManager<ArticleEntity> {
    //查询事件的相关文章
    public List<ArticleEntity> findArticleList(String objectId){
        /*String hql = "from ArticleEntity a where a.objectEntity = '" +objectId+"'";
        List<ArticleEntity> artileList = getEntityDao().find(hql);
        return artileList;*/
        return null;
    }
}
