package com.sicdlib.service;

import com.sicdlib.entity.ArticleEntity;
import com.sicdlib.entity.DataDictionaryEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DataDictionaryEntityService extends DefaultEntityManager<DataDictionaryEntity> {
    //查找媒体
    public List<DataDictionaryEntity> findAllMedias(){
        String hql = "from DataDictionaryEntity d where d.dataDictionaryId>8 and d.dataDictionaryId<14";
        List<DataDictionaryEntity> allMedias = getEntityDao().find(hql);
        return allMedias;
    }

}
