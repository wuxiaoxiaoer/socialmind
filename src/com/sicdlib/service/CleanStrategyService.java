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
}
