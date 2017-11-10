package com.sicdlib.web;

import com.sicdlib.entity.*;
import com.sicdlib.service.*;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wlw
 */
//用户管理模块
@Controller
@RequestMapping("/*")
public class UsersManagerAction {

	@Autowired(required=true)
	private UserService userService;
	@Autowired
	private UserEntityService userEntityService;
	@Autowired
	private PersonUserEntityService personUserEntityService;
    @Autowired
    private CompanyUserEntityService companyUserEntityService;
    @Autowired
    private InstitutionUserEntityService institutionUserEntityService;
    @Autowired
    private GovUserEntityService govUserEntityService;
    @Autowired
    private RoleEntityService roleEntityService;
    @Autowired
    private AdminEntityService adminEntityService;

    //系统后台 - 用户管理
    @RequestMapping("usersManager")
    public String usersManager(@RequestParam(defaultValue="1") int pageNo, @RequestParam(defaultValue="") String op,
                               @RequestParam(defaultValue="1") String userId, @RequestParam(defaultValue="100") String isisAuthenticated,
                               HttpServletRequest req, Model mode){

        //审核与删除用户：isisAuthenticated为0，未审核；1；审核通过；-1审核不通过；no，删除用户
        if (op.equals("userVerify") && !userId.equals("1") && !isisAuthenticated.equals("100")){
            UserEntity user = userEntityService.load(userId);
            user.setIsAuthenticated(isisAuthenticated);
            userEntityService.saveOrUpdate(user);
        }

        //批量审核用户
        if (op.equals("userMultiVerify")){
            String [] userIds = req.getParameterValues("userId");
            for (String uid : userIds){
                System.out.println("用户ID:" + uid);
                UserEntity user = userEntityService.load(uid);
                user.setIsAuthenticated(isisAuthenticated);
                userEntityService.saveOrUpdate(user);
            }
        }

        //待审核用户
        Page<UserEntity> page = new Page<UserEntity>(100);
        page.setPageNo(pageNo);
        PropertyFilter filter = new PropertyFilter("isAuthenticated", "0");
        userEntityService.search(page, filter);
        List<PersonUserEntity> persons = new ArrayList<PersonUserEntity>();
        for (UserEntity user : page.getResult()){
            PersonUserEntity person = personUserEntityService.load(user.getUserId());
            if (person != null){
                persons.add(person);
            }
        }
        mode.addAttribute("page", page);
        mode.addAttribute("persons", persons);

        List<GovUserEntity> unVerifyGovUsers = new ArrayList<GovUserEntity>();
        for (UserEntity user : page.getResult()){
            GovUserEntity unVerifyGovUser = govUserEntityService.load(user.getUserId());
            if (unVerifyGovUser != null){
                unVerifyGovUsers.add(unVerifyGovUser);
            }
        }
        mode.addAttribute("unVerifyGovUsers", unVerifyGovUsers);

        List<InstitutionUserEntity> unVerifyInstitUsers = new ArrayList<InstitutionUserEntity>();
        for (UserEntity user : page.getResult()){
            InstitutionUserEntity unVerifyInstituUser = institutionUserEntityService.load(user.getUserId());
            if (unVerifyInstituUser != null){
                unVerifyInstitUsers.add(unVerifyInstituUser);
            }
        }
        mode.addAttribute("unVerifyInstitUsers", unVerifyInstitUsers);

        List<CompanyUserEntity> unVerifyCompanyUsers = new ArrayList<CompanyUserEntity>();
        for (UserEntity user : page.getResult()){
            CompanyUserEntity unVerifyCompanyUser = companyUserEntityService.load(user.getUserId());
            if (unVerifyCompanyUser != null){
                unVerifyCompanyUsers.add(unVerifyCompanyUser);
            }
        }
        mode.addAttribute("unVerifyCompanyUsers", unVerifyCompanyUsers);

        //显示全部用户前100条
        Page<UserEntity> pageAllUsers = new Page<>(100);
        pageAllUsers.setPageNo(1);
        userEntityService.loadAll(pageAllUsers);
        List<PersonUserEntity> pageAllPersons = new ArrayList<PersonUserEntity>();
        for (UserEntity user : pageAllUsers.getResult()){
            PersonUserEntity person = personUserEntityService.load(user.getUserId());
            if (person != null){
                pageAllPersons.add(person);
            }
        }
        mode.addAttribute("pageAllUsers", pageAllUsers);
        mode.addAttribute("pageAllPersons", pageAllPersons);
        List<GovUserEntity> pageAllGovUsers = new ArrayList<GovUserEntity>();
        for (UserEntity user : pageAllUsers.getResult()){
            GovUserEntity tempUser = govUserEntityService.load(user.getUserId());
            if (tempUser != null){
                pageAllGovUsers.add(tempUser);
            }
        }
        mode.addAttribute("pageAllGovUsers", pageAllGovUsers);
        List<InstitutionUserEntity> pageAllInstitUsers = new ArrayList<InstitutionUserEntity>();
        for (UserEntity user : pageAllUsers.getResult()){
            InstitutionUserEntity tempUser = institutionUserEntityService.load(user.getUserId());
            if (tempUser != null){
                pageAllInstitUsers.add(tempUser);
            }
        }
        mode.addAttribute("pageAllInstitUsers", pageAllInstitUsers);
        List<CompanyUserEntity> pageAllCompanyUsers = new ArrayList<CompanyUserEntity>();
        for (UserEntity user : pageAllUsers.getResult()){
            CompanyUserEntity tempUser = companyUserEntityService.load(user.getUserId());
            if (tempUser != null){
                pageAllCompanyUsers.add(tempUser);
            }
        }
        mode.addAttribute("pageAllCompanyUsers", pageAllCompanyUsers);
        return "/WEB-INF/admin/usersManager";
    }


}
