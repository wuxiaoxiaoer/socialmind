package com.sicdlib.service;

import com.sicdlib.entity.ProvinceEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;

import java.util.ArrayList;
import java.util.List;

public class ProvinceService extends DefaultEntityManager<ProvinceEntity> {
    public List<ProvinceEntity> getStrategies() {
        List<ProvinceEntity> provinceList = new ArrayList<ProvinceEntity>();
        String hql = "select id, name from ProvinceEntity";
        provinceList = getEntityDao().find(hql);
        return provinceList;
    }
}
