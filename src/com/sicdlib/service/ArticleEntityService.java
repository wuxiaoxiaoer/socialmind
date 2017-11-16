package com.sicdlib.service;

import com.sicdlib.entity.ArticleEntity;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class ArticleEntityService extends DefaultEntityManager<ArticleEntity> {

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

}
