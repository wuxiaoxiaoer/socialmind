package com.sicdlib.service;

import com.sicdlib.entity.UserOperaEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserOperaEntityService extends DefaultEntityManager<UserOperaEntity> {

    /** 通过用户id、对象Id、操作 查询用户操作*/
    public UserOperaEntity getUserOpera(String userId, String objId, String opera){
        String hql = "select o from UserOperaEntity o where o.userEntity.userId = '" + userId
                + "' and o.objectEntity.objectId = '" + objId +"' and o.operaType = '" + opera + "'";
        List<UserOperaEntity> userOperaEntities = getEntityDao().find(hql);
        if (userOperaEntities.size() != 0){
            return userOperaEntities.get(0);
        }else {
            return null;
        }
    }
    /**
     * 通过对象Id、操作 查询用户操作
     */
    public List<UserOperaEntity> getUserOperasByObjIdAndOpera(String objId, String opera){
        String hql = "select o from UserOperaEntity o where o.objectEntity.objectId = '" + objId +"' and o.operaType = '" + opera + "'";
        return getEntityDao().find(hql);
    }
}
