package com.sicdlib.service;


import com.sicdlib.entity.CleanStrategyEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CleanStrategyService extends DefaultEntityManager<CleanStrategyEntity> {
    public List<List<CleanStrategyEntity>> getStrategies(){
        List<List<CleanStrategyEntity>> strategyList=new ArrayList<List<CleanStrategyEntity>>();

        String hql1 = "from CleanStrategyEntity strategy where strategy.type = '1'";
        String hql2 = "from CleanStrategyEntity strategy where strategy.type = '2'";
        String hql3 = "from CleanStrategyEntity strategy where strategy.type = '3'";
        String hql4 = "from CleanStrategyEntity strategy where strategy.type = '4'";
        String hql5 = "from CleanStrategyEntity strategy where strategy.type = '5'";
        List<CleanStrategyEntity> strategyList1 = getEntityDao().find(hql1);
        List<CleanStrategyEntity> strategyList2 = getEntityDao().find(hql2);
        List<CleanStrategyEntity> strategyList3 = getEntityDao().find(hql3);
        List<CleanStrategyEntity> strategyList4 = getEntityDao().find(hql4);
        List<CleanStrategyEntity> strategyList5 = getEntityDao().find(hql5);
        strategyList.add(strategyList1);
        strategyList.add(strategyList2);
        strategyList.add(strategyList3);
        strategyList.add(strategyList4);
        strategyList.add(strategyList5);
        return strategyList;
    }

    // get using strategies
    public List<CleanStrategyEntity>  getUsingStrategies(String strategyID){
        List<CleanStrategyEntity> strategyList=new ArrayList<CleanStrategyEntity>();
        String hql = "from CleanStrategyEntity strategy where strategy.type = '"+strategyID+"' AND strategy.isUse = '0'";
        strategyList = getEntityDao().find(hql);
        return strategyList;
    }
    //get not using strategies
    public List<CleanStrategyEntity>  getNotUsingStrategies(String strategyID){
        List<CleanStrategyEntity> strategyList=new ArrayList<CleanStrategyEntity>();
        String hql = "from CleanStrategyEntity strategy where strategy.type = '"+strategyID+"' AND strategy.isUse = '1'";
        strategyList = getEntityDao().find(hql);
        return strategyList;
    }

    //change use status
    public Boolean moveStrategy(String strategyID, String useStatus){
        String newStatus;
        if(useStatus.equals("0")){
            newStatus ="1";
        }else{
            newStatus ="0";
        }
//        String hql = "update CleanStrategyEntity strategy set strategy.isUse='"+newStatus+"' where strategy.type = '"+strategyID+"' AND strategy.isUse = '"+useStatus+"'";
//        getEntityDao().createQuery(hql);
        CleanStrategyEntity cleanStrategyEntity=this.load(strategyID);
        cleanStrategyEntity.setIsUse(newStatus);
        this.saveOrUpdate(cleanStrategyEntity);
        return true;
    }
}
