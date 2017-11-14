package com.sicdlib.service;

import com.sicdlib.entity.ArticleEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArticleEntityService extends DefaultEntityManager<ArticleEntity> {
    //查询事件的相关文章
    public List<ArticleEntity> findArticleList(String objectId){
        String hql = "from ArticleEntity a where a.objectEntity = '" +objectId+"' order by postTime";
        List<ArticleEntity> artileList = getEntityDao().find(hql);
        return artileList;
    }

    //查找事件的关键词
    public List<ArticleEntity> findKeywords(String objectId){
        String hql = "select keyWords from ArticleEntity a where a.objectEntity = '" +objectId+"'";
        List<ArticleEntity> keywords = getEntityDao().find(hql);
        return keywords;
    }

    //查找事件的媒体数量
    public List findMedias(String objectId){
        String hql = "select a.postTime,a.newsResource,COUNT(a.newsResource)from ArticleEntity a where a.objectEntity = '" +objectId+"' group by a.newsResource";
        List medias = getEntityDao().find(hql);
        return medias;
    }
}
