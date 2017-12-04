package com.sicdlib.service;

import com.sicdlib.entity.ProvinceEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProvinceService extends DefaultEntityManager<ProvinceEntity> {
    public List<ProvinceEntity> getProvinces() {
        List<ProvinceEntity> provinceList = new ArrayList<ProvinceEntity>();
        String hql = "from ProvinceEntity province";
        provinceList = getEntityDao().find(hql);
        return provinceList;
    }
}
