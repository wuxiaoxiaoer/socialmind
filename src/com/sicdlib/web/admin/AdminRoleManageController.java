package com.sicdlib.web.admin;

import com.sicdlib.entity.RoleEntity;
import com.sicdlib.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 角色管理(角色列表，增加角色).
 */
@Controller
@RequestMapping("/admin/*")
public class AdminRoleManageController {

	@Autowired(required=true)
	private RoleService roleService;

	//后台主页
	@RequestMapping(value="roleManagement")
	public String roleManage(Model model){


		List<RoleEntity> roleList=new ArrayList<RoleEntity>();

		roleList=roleService.loadAll();

		model.addAttribute("roleList",roleList);

		return "/WEB-INF/admin/roleManagement";
	}

	/**
	 * 增加角色
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="addRole")
	public void addRole(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String roleName = req.getParameter("roleName");
		String roleDesc = req.getParameter("roleDesc");


		RoleEntity role = new RoleEntity();
		role.setRoleName(roleName);
		role.setDescription(roleDesc);
		role.setRoleId(UUID.randomUUID().toString());
		PrintWriter out = res.getWriter();

		roleService.saveOrUpdate(role);
		out.print("success");

	}


    /**
     * 修改角色
     * @return
     * @throws IOException
     */
    @RequestMapping(value="updateRole")
    public void updateRole(HttpServletRequest req, HttpServletResponse res) throws IOException{
        String roleNewName = req.getParameter("roleNewName");
        String roleNewDesc = req.getParameter("roleNewDesc");
        String id = req.getParameter("id");

        RoleEntity role = new RoleEntity();
        role.setRoleName(roleNewName);
        role.setDescription(roleNewDesc);
        role.setRoleId(id);

        PrintWriter out = res.getWriter();
        roleService.saveOrUpdate(role);
        out.print("success");
    }







	/**
	 * 删除角色（假删还是？？?）
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("deleteRole")
	public void deleteRole(HttpServletRequest req, HttpServletResponse res) throws IOException {

		String roleId = req.getParameter("roleId");
		PrintWriter out = res.getWriter();

		roleService.remove(roleId);
		out.print("success");

	}



    /**
     * 判断角色是否存在
     * @return
     * @throws IOException
     */
    @RequestMapping(value="roleExit")
    public void roleExit(HttpServletRequest req, HttpServletResponse res) throws IOException{
        String roleName = req.getParameter("roleId");



    }











}
