package com.sicdlib.service;

import com.sicdlib.entity.CityEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;

import java.util.ArrayList;
import java.util.List;

public class CityService extends DefaultEntityManager<CityEntity> {
    public List<CityEntity> getStrategies() {
        List<CityEntity> cityList = new ArrayList<CityEntity>();
        String hql = "select id, name from CityEntity";
        cityList = getEntityDao().find(hql);
        return cityList;
    }
}
