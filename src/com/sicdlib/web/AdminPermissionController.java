package com.sicdlib.web;

import com.google.gson.Gson;
import com.sicdlib.entity.PermissionEntity;
import com.sicdlib.entity.RoleEntity;
import com.sicdlib.entity.RolePermissionEntity;
import com.sicdlib.service.PermissionService;
import com.sicdlib.service.RolePermissionService;
import com.sicdlib.service.RoleService;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 *权限管理
 */
@Controller
@RequestMapping("/*")
public class AdminPermissionController {

	@Autowired(required=true)
	private PermissionService permissionService;
	@Autowired(required=true)
	private RoleService roleService;
	@Autowired(required=true)
	RolePermissionService rolePermissionService;



	/**
	 * 权限列表
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("permission")
	public String  permission(Model model) throws IOException{
		List<PermissionEntity> permissionList=new ArrayList<PermissionEntity>();
		permissionList=permissionService.loadAll();


		model.addAttribute("permissionList",permissionList);
		return "/WEB-INF/admin/permission";

	}


	/**
	 * 增加权限
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="addPermission")
	public void addPermission(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String permissionName = req.getParameter("permissionName");
		String action = req.getParameter("action");


		PermissionEntity permission = new PermissionEntity();
		permission.setPermissionName(permissionName);
		permission.setAction(action);
		permission.setPermissionId(UUID.randomUUID().toString());

		permissionService.saveOrUpdate(permission);

		PrintWriter out = res.getWriter();
		out.print("success");

	}


    /**
     * 修改权限
     * @return
     * @throws IOException
     */
    @RequestMapping(value="updatePermission")
    public void editPermission(HttpServletRequest req, HttpServletResponse res) throws IOException{

		String permissionName = req.getParameter("permissionName");
		String action = req.getParameter("action");
		String id = req.getParameter("id");

		PermissionEntity permission = new PermissionEntity();
		permission.setPermissionName(permissionName);
		permission.setAction(action);
		permission.setPermissionId(id);

		PrintWriter out = res.getWriter();
		permissionService.saveOrUpdate(permission);
		out.print("success");



    }



	/**
	 * 删除权限
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("deletePermission")
	public void deletePermission(HttpServletRequest req, HttpServletResponse res) throws IOException {

		String permissionId = req.getParameter("permissionId");
		PrintWriter out = res.getWriter();

		permissionService.remove(permissionId);
		out.print("success");



	}


    /**
     * 判断权限是否存在
     * @return
     * @throws IOException
     */
    @RequestMapping(value="permissionExist")
    public void permissionExist(HttpServletRequest req, HttpServletResponse res) throws IOException{

    }


	/**
	 * 跳转权限分配页面
	 * @return
	 */
	@RequestMapping(value="toPermissionAllot")
	public String toPermissionAllot(Model model){
		List<RoleEntity> roleList=new ArrayList<RoleEntity>();
		List<PermissionEntity> permissionList=new ArrayList<PermissionEntity>();
		roleList=roleService.loadAll();
		permissionList=permissionService.loadAll();

		//获得该角色已经分配了的权限	默认显示角色列表中的第一个
		//获得学生的ID
		RoleEntity role=roleList.get(0);

		PropertyFilter filter = new PropertyFilter("roleId", role.getRoleId());
		List<RolePermissionEntity> rolepermissionList = rolePermissionService.search(filter);

		//perminssionId 和checked
		Map<String, String> permissionMap = new HashMap<String, String>();
		//先将所有的权限都设置成未选中
		for(int i = 0; i < permissionList.size(); i++){
			permissionMap.put(permissionList.get(i).getPermissionId(), "f");

		}
		//再将已经分配的权限都设置成选中，此时不能用TRUE，因为是关键字，前台会出错
		for(int i = 0; i < rolepermissionList.size(); i++){
			permissionMap.put(rolepermissionList.get(i).getPermissionId(), "t");
		}

		model.addAttribute("permissionMap",permissionMap);//记录此权限有没有选中(权限号，是否选中)
		model.addAttribute("permissionList",permissionList);
		model.addAttribute("roleList",roleList);
		return "/WEB-INF/admin/permissionAllot";
	}


	/**
	 * ajax按角色展示已经分配了的权限
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("permissionByrole")
	public  void permissionByrole( HttpServletRequest req, HttpServletResponse res) throws IOException{

		List<PermissionEntity> permissionList=permissionService.loadAll();

		//获得角色
		String roleId = req.getParameter("roleId");
		//获得该角色已经分配了的权限
		PropertyFilter filter = new PropertyFilter("roleId",roleId);
		List<RolePermissionEntity> rolepermissionList = rolePermissionService.search(filter);
		//perminssionId 和checked
		Map<String, String> permissionMap = new HashMap<String, String>();
		//先将所有的权限都设置成未选中
		for(int i = 0; i < permissionList.size(); i++){
			permissionMap.put(permissionList.get(i).getPermissionId(), "f");

		}
		//再将已经分配的权限都设置成选中，此时不能用TRUE，因为是关键字，前台会出错
		for(int i = 0; i < rolepermissionList.size(); i++){
			permissionMap.put(rolepermissionList.get(i).getPermissionId(), "t");
		}

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("permissionList", permissionList );
		map.put("permissionMap",permissionMap);

		Gson gson = new Gson();
		try {
            /*设置编码格式，返回结果
            * ***/
			res.setContentType( "text/html;charset=UTF-8");
			res.getWriter().write(gson.toJson(map));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}



	/**
	 * 分配权限
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("permissionAllot")
	public String permissionAllot( HttpServletRequest req, HttpServletResponse res) throws IOException{

		System.out.println("permissionAllot###############################  ");

		//获得角色
		String roleId=req.getParameter("roleId");

		System.out.println("roleID "+roleId);


		//获得分配页面所有的分配的权限，包括之前已经分配了的权限，所以要找出来数据库中该角色的权限，先进行删除所有
		String[] permissionIDList=req.getParameterValues("permissionId");
		//getParameterValues后面有空格

		System.out.println("permissionIDList  "+permissionIDList.length);
		System.out.println("permissionAllot###################permissionIDList[0]  "+permissionIDList[0]+"#");
		System.out.println("permissionAllot###################permissionIDList[0]  "+permissionIDList[0].trim()+"#");



		//删除该角色下的所有权限

		PropertyFilter filter = new PropertyFilter("roleId",roleId);
		List<RolePermissionEntity> rolepermissionList = rolePermissionService.search(filter);
		System.out.println("@@@@@rolepermissionList  "+rolepermissionList.size());
		for(int i=0;i<rolepermissionList.size();i++) {

			System.out.println("@@@@@@rolepermissionList.get(i).getRolePermissionId()  "+rolepermissionList.get(i).getRolePermissionId());
			rolePermissionService.remove(rolepermissionList.get(i).getRolePermissionId());
		}


		//为该角色重新添加权限

		for(int i=0;i<permissionIDList.length;i++) {
			RolePermissionEntity rolePermission =new RolePermissionEntity();
			rolePermission.setRolePermissionId(UUID.randomUUID().toString());
			rolePermission.setPermissionId(permissionIDList[i]);
			rolePermission.setRoleId(roleId);
			rolePermissionService.saveOrUpdate(rolePermission);
		}

		PrintWriter out = res.getWriter();

		out.print("success");


		return "redirect:/toPermissionAllot";
	}





















}
