package com.sicdlib.web.admin;

import com.sicdlib.entity.PermissionEntity;
import com.sicdlib.service.PermissionService;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;
import java.util.List;


/**
 * 权限管理(权限的列表，增加，删除，修改，分配)
 */
@Controller
@RequestMapping("/admin/*")
public class AdminPermissionController {

    @Autowired
    private PermissionService permissionService;



    /**
     *权限列表
     * @param model
     * @return
     */
    @RequestMapping(value="permission")
    public String permission(Model model){

        List<PermissionEntity> permissionList= new ArrayList<PermissionEntity>();

        permissionList = permissionService.loadAll();
        PropertyFilter filters = new PropertyFilter();
        permissionService.search(filters);

        model.addAttribute("permissionList",permissionList);

        return "/WEB-INF/admin/permission";
    }






}
