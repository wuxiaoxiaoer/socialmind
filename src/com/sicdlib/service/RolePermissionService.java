package com.sicdlib.service;

import com.sicdlib.entity.RolePermissionEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;

/**
 * Created by zcx on 2017/11/7.
 */
@Service
public class RolePermissionService extends DefaultEntityManager<RolePermissionEntity> {





    /**
     * 删除该角色的所有权限
     */
    public void deleteAllPermission(String roleID){

    }



}
