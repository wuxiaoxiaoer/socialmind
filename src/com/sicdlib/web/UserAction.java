package com.sicdlib.web;

import com.sicdlib.entity.*;
import com.sicdlib.service.*;
import com.sicdlib.util.MD5Util.MD5Util;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author wlw
 */
//用户管理模块
@Controller
@RequestMapping("/user/*")
public class UserAction {

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
    @Autowired
    private ObjectEntityService objectEntityService;
    @Autowired
    private EventEntityService eventEntityService;
    @Autowired
    private IndicatorValueEntityService indicatorValueEntityService;
    @Autowired
    private LogService logService;
    @Autowired
    private CommentEntityService commentEntityService;
    @Autowired
    private ObjectUserEntityService objectUserEntityService;

	
	//登录模块
	@RequestMapping("login")
	public String login(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException{
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String userpwd = req.getParameter("userpwd");
		boolean isRemPwd = Boolean.parseBoolean(req.getParameter("isRemPwd"));
        String address = "";
        if (req.getParameter("address") != null){
            address = req.getParameter("address");
        }
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		PropertyFilter filters = new PropertyFilter("userName",username);
		List<UserEntity> searchUsers = userEntityService.search(filters);
		System.out.println(userpwd + " : " + MD5Util.generatePassword(userpwd));
		if (searchUsers.size() != 0) {
            UserEntity user = searchUsers.get(0);
			if(user != null){
				if (MD5Util.validatePassword(searchUsers.get(0).getPassword(), userpwd)) {
                    session.setAttribute("commonUser", user);
					if(isRemPwd){
						Cookie userNameCookie = new Cookie("u_name", URLEncoder.encode(username,"UTF-8"));
						Cookie userPwdCookie = new Cookie("u_pwd", userpwd);
						userNameCookie.setPath(req.getContextPath()+"/");
						userPwdCookie.setPath(req.getContextPath()+"/");
						userNameCookie.setMaxAge(7*24*60*60);
						userPwdCookie.setMaxAge(7*24*60*60);
						resp.addCookie(userNameCookie);
						resp.addCookie(userPwdCookie);
					} else if(!isRemPwd){
						Cookie userNameTempCookie = new Cookie("u_name", null);
						userNameTempCookie.setMaxAge(0);
						Cookie userPwdTempCookie = new Cookie("u_pwd", null);
						userPwdTempCookie.setMaxAge(0);
						userNameTempCookie.setPath(req.getContextPath()+"/");
						userPwdTempCookie.setPath(req.getContextPath()+"/");
						resp.addCookie(userNameTempCookie);
						resp.addCookie(userPwdTempCookie);
					}
					if (user.getRole().getRoleName().equals("管理员")){
                        AdminEntity adminUser = adminEntityService.load(user.getUserId());
                        session.setAttribute("user", adminUser);
						out.print("admin");
                        Map<String, List<UserEntity>> returns = showAdminIndex(user.getUserId());
                        //1. 用户上次登陆时间后的新增系统用户
                        List<UserEntity> users = returns.get("latestUsers");
                        mode.addAttribute("users", users);
                        //2. 提供总用户数
                        Long allUsers = userEntityService.getUserCountByRoleName("ALL");
                        Long adminUsers = userEntityService.getUserCountByRoleName("管理员");
                        Long govUsers = userEntityService.getUserCountByRoleName("政府");
                        Long insUsers = userEntityService.getUserCountByRoleName("事业单位");
                        Long comUsers = userEntityService.getUserCountByRoleName("企业");
                        Long perUsers = userEntityService.getUserCountByRoleName("个人");
                        List<Long> counts = new ArrayList<>();
                        counts.add(allUsers);
                        counts.add(adminUsers);
                        counts.add(govUsers);
                        counts.add(insUsers);
                        counts.add(comUsers);
                        counts.add(perUsers);
                        mode.addAttribute("counts", counts);
                        //获得用户上次登陆时间
                        String latestLogTime = getLatestLogTime(user.getUserId()).getLogTime();
                        //3. 显示系统留言信息
                        Long latestCommentsCount = commentEntityService.getCommentsCount(latestLogTime);
                        Long allCommentsCount = commentEntityService.getCommentsCount("ALL");
                        mode.addAttribute("latestCommentsCount", latestCommentsCount);
                        mode.addAttribute("allCommentsCount", allCommentsCount);
                        //4. 日志生成量
                        Long latestLogsCount = logService.getLogsCount(latestLogTime);
                        Long allLogsCount = logService.getLogsCount("ALL");
                        mode.addAttribute("latestLogsCount", latestLogsCount);
                        mode.addAttribute("allLogsCount", allLogsCount);
						return "/WEB-INF/admin/index";
                    }else {

                        //获得不同角色用户与该对象相关的事件(包括热度)
                        session.setAttribute("commonUser", user);
                        Page<ObjectEntity> page = new Page<>(5);
                        page.setPageNo(1);
                        objectEntityService.loadAll(page);
                        mode.addAttribute("page", page);
                        //1.获得热门事件
                        //获得事件类型的舆情对象
                        List<ObjectEntity> objects = objectEntityService.getObjectIndicators("事件", "", "2016-10-10 12:00:00");
                        List<DynamicObjectIndexIndicator> dynamicObjIndexs = getDynamicObjIndexs(objects);
                        //根据指标值（如热度）进行排序
                        objectsSort(dynamicObjIndexs);
                        //获得前5条
                        List<DynamicObjectIndexIndicator> top5DynamicObjIndexs = getTop5DynamicObjIndexs(dynamicObjIndexs);
                        mode.addAttribute("top5DynamicObjIndexs", top5DynamicObjIndexs);
                        //2.获得当地事件
                        //获得事件类型的舆情对象
                        System.out.println("地址：" + address);
                        List<ObjectEntity> addressObjects = objectEntityService.getObjectIndicators("事件", address, "2016-10-10 12:00:00");
                        List<DynamicObjectIndexIndicator> dynamicAddressObjIndexs = getDynamicObjIndexs(addressObjects);
                        //根据指标值（如热度）进行排序
                        objectsSort(dynamicAddressObjIndexs);
                        //获得前5条
                        List<DynamicObjectIndexIndicator> top5DynamicAddressObjIndexs = getTop5DynamicObjIndexs(dynamicAddressObjIndexs);
                        mode.addAttribute("top5DynamicAddressObjIndexs", top5DynamicAddressObjIndexs);
                        //获得领导动态 - leader
                        List<ObjectUserEntity> objectUsersLeader = objectUserEntityService.getObjUsersByOTypeAndUser("leader", user.getUserId());
                        mode.addAttribute("objectUsersLeader", objectUsersLeader);
                        //获得机关部门动态 - department
                        List<ObjectUserEntity> objectUsersDepartment = objectUserEntityService.getObjUsersByOTypeAndUser("department", user.getUserId());
                        mode.addAttribute("objectUsersDepartment", objectUsersDepartment);
                        //获得领域动态 - field
                        List<ObjectUserEntity> objectUsersField = objectUserEntityService.getObjUsersByOTypeAndUser("field", user.getUserId());
                        mode.addAttribute("objectUsersField", objectUsersField);
                        if (user.getRole().getRoleName().equals("政府")) {
                            GovUserEntity govUser = govUserEntityService.load(user.getUserId());
                            session.setAttribute("user", govUser);
                        } else if (user.getRole().getRoleName().equals("事业单位")) {
                            InstitutionUserEntity institutionUser = institutionUserEntityService.load(user.getUserId());
                            session.setAttribute("user", institutionUser);
                        } else if (user.getRole().getRoleName().equals("企业")) {
                            CompanyUserEntity companyUser = companyUserEntityService.load(user.getUserId());
                            session.setAttribute("user", companyUser);
                        } else if (user.getRole().getRoleName().equals("个人")) {
                            PersonUserEntity personUser = personUserEntityService.load(user.getUserId());
                            session.setAttribute("user", personUser);
                        }
                        return "/WEB-INF/foreground/index";
                    }
				}
			}
		}
		return "";
	}

    /**
     * 获得用户上次登陆时间
     */
    public LogEntity getLatestLogTime(String userId){
        //1.获得管理员上次登陆时间以后的系统用户注册人数，提供：上次不同类型用户人数
        String latestLogTime = "";
        List<LogEntity> logs = logService.getLastLogByuserId(userId);
        LogEntity logEntity = new LogEntity();
        if (logs.size() != 0){
            logEntity = logs.get(0);
        }
        return logEntity;
    }

    /**
     * @ wlw
     * 显示后台管理员信息
     * @param
     */
	public Map<String, List<UserEntity>> showAdminIndex(String userId){
        String latestLogTime = getLatestLogTime(userId).getLogTime();
        //查询某个上次登陆时间之后的用户
        List<UserEntity> latestUsers = userEntityService.getUsersByTime(latestLogTime);
        Map<String, List<UserEntity>> returns = new HashMap<>();
        returns.put("latestUsers", latestUsers);

        return returns;
    }

    /**
     * 根据对象类型、地点、新增事件时间获得舆情对象
     * @param objectType
     * @param place
     * @param addTime
     * @return
     */
	public List<ObjectEntity> getObjects(String objectType, String place, String addTime){
        List<ObjectEntity> objects = objectEntityService.getObjectIndicators(objectType, place, addTime);
        return objects;
    }

    /**
     * 根据舆情对象列表查询指标(热度)
     * @param objects
     * @return
     */
    public List<DynamicObjectIndexIndicator> getDynamicObjIndexs(List<ObjectEntity> objects){
        List<DynamicObjectIndexIndicator> dynamicObjIndexs = new ArrayList<>();
        //获得事件的指标值（如热度）的统计
        for (ObjectEntity obj : objects){
            DynamicObjectIndexIndicator dynamicObjIndex = new DynamicObjectIndexIndicator();
            Double hotValue = 0.0;
            List<IndicatorValueEntity> indicators = indicatorValueEntityService
                    .getObjectIndicators(obj.getObjectId(), "热度", "sex", "", "2016-10-10 12:00:00", "2099-11-07 12:00:00");
            for (IndicatorValueEntity ind : indicators){
                Double value = Double.parseDouble(ind.getIndicatorValue());
                hotValue += value;
            }
            dynamicObjIndex.setObject(obj);
            dynamicObjIndex.setHotValue(hotValue);
            dynamicObjIndexs.add(dynamicObjIndex);
        }
        return dynamicObjIndexs;
    }

    /**
     * 获得实体与指标构成的动态对象指标的前5条
     * @param dynamicObjIndexs
     * @return
     */
    public List<DynamicObjectIndexIndicator> getTop5DynamicObjIndexs(List<DynamicObjectIndexIndicator> dynamicObjIndexs){
        //获得前5条
        List<DynamicObjectIndexIndicator> top5DynamicObjIndexs = new ArrayList<>();
        if (dynamicObjIndexs.size() > 5){
            top5DynamicObjIndexs = dynamicObjIndexs.subList(0,4);
        }else {
            top5DynamicObjIndexs = dynamicObjIndexs;
        }
        return top5DynamicObjIndexs;
    }

    /**
     * 根据指标值（如热度）进行排序
     * @param dynamicObjIndexs
     * @return
     */
	public List<DynamicObjectIndexIndicator> objectsSort(List<DynamicObjectIndexIndicator> dynamicObjIndexs){
        Collections.sort(dynamicObjIndexs, new Comparator<DynamicObjectIndexIndicator>() {
            @Override
            public int compare(DynamicObjectIndexIndicator o1, DynamicObjectIndexIndicator o2) {
                //降序排列
                if (o1.getHotValue() > o2.getHotValue()){}
                return 1;
            }
        });
	    return dynamicObjIndexs;
    }

}
