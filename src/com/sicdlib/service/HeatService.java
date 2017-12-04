package com.sicdlib.service;

import com.sicdlib.entity.IndicatorValueEntity;

import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Created by zcx on 2017/11/23.
 */
@Service
@Transactional
public class HeatService extends DefaultEntityManager<IndicatorValueEntity> {



    public List<IndicatorValueEntity> getHeatList(String objectId){

        String hql = "select i  from IndicatorValueEntity i where i.objectEntity.objectId ='" +objectId+ "' and i.dimensionName='all'  " +" and i.indexName='热度'  Order By i.startTime";

        List<IndicatorValueEntity> heatList = getEntityDao().find(hql);
        return heatList;

    }
    public List<IndicatorValueEntity> getHeatListInSex(String objectId,String sex){

        String hql = "select i  from IndicatorValueEntity i where i.objectEntity.objectId ='" +objectId+ "' and i.dimensionName='sex'  " +" and i.dimensionValue='"+ sex +"'  and i.indexName='热度'  Order By i.startTime";

        List<IndicatorValueEntity> heatList = getEntityDao().find(hql);
        return heatList;

    }
    public List<IndicatorValueEntity> getHeatListInProfession(String objectId,String profession){

        String hql = "select i  from IndicatorValueEntity i where i.objectEntity.objectId ='" +objectId+ "' and i.dimensionName='profession'  " +"  and i.dimensionValue='"+ profession +"'  and i.indexName='热度'  Order By i.startTime";

        List<IndicatorValueEntity> heatList = getEntityDao().find(hql);
        return heatList;

    }

    public List<IndicatorValueEntity> getHeatListInAge(String objectId,String age){

        String hql = "select i  from IndicatorValueEntity i where i.objectEntity.objectId ='" +objectId+ "' and i.dimensionName='age'  " +" and i.dimensionValue='"+ age +"'  and i.indexName='热度'  Order By i.startTime";

        List<IndicatorValueEntity> heatList = getEntityDao().find(hql);
        return heatList;

    }


    public List<IndicatorValueEntity> getHeatListInDomain(String objectId,String dimensionName){

        String hql = "select i  from IndicatorValueEntity i where i.objectEntity.objectId ='" +objectId+ "' and i.dimensionName='"+ dimensionName +"'  and i.indexName='热度'  Order By i.startTime";

        List<IndicatorValueEntity> heatList = getEntityDao().find(hql);
        return heatList;

    }

}
