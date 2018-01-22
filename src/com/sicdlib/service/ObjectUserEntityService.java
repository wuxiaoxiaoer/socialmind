package com.sicdlib.service;

import com.sicdlib.entity.ObjectUserEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ObjectUserEntityService extends DefaultEntityManager<ObjectUserEntity> {

    /**
     * 通过类型（oType）和用户ID（userId）获得对象用户中间表
     */
    public List<ObjectUserEntity> getObjUsersByOTypeAndUser(String oType, String userId){
        String hql = "from ObjectUserEntity ou where ou.oType = '" + oType + "' and ou.userEntity.userId = '" + userId +"'";
        return getEntityDao().find(hql);
    }
}
