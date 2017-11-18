package com.sicdlib.web;

import com.sicdlib.entity.*;
import com.sicdlib.service.*;
import com.sicdlib.util.MD5Util.MD5Util;
import com.sicdlib.util.TeleValidUtil.TeleValidCode;
import com.sicdlib.util.UUIDUtil.UUIDUtil;
import com.sicdlib.util.mailUtil.SendMails;
import com.sicdlib.util.mailUtil.ValidationCode;
import com.taobao.api.ApiException;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wlw
 */
//用户管理模块
@Controller
@RequestMapping("/user/*")
public class UserAction {

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
    @Autowired
    private ObjectEntityService objectEntityService;
    @Autowired
    private EventEntityService eventEntityService;
    @Autowired
    private IndicatorValueEntityService indicatorValueEntityService;

	
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
						return "/WEB-INF/admin/index";
                    }else {
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

                        return "/WEB-INF/foreground/index";
                    }
				}
			}
		}
		return "";
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

    //注册模块--跳转
	@RequestMapping(value="register", method = RequestMethod.GET)
	public String register(){
		return "register";
	}

	//注册模块--用户注册(政府: 1; 企业: 2; 事业单位: 3; 个人: 4)
	@RequestMapping(value="register", method = RequestMethod.POST)
	public String register(MultipartHttpServletRequest multipartRequest, UserEntity user, PersonUserEntity personUser, CompanyUserEntity companyUser,
                           InstitutionUserEntity institutionUser, GovUserEntity govUser, AdminEntity admin,
                           HttpServletRequest req){
	    String province = req.getParameter("province");
	    String city = req.getParameter("city");
	    String area = req.getParameter("area");
        String userType = req.getParameter("userType");
        //设置地区
        String address = province +" " + city + " " + area;
		//设置用户ID
		String userId = UUIDUtil.getUUID();
		user.setUserId(userId);
        user.setPassword(MD5Util.generatePassword(user.getPassword()));
        user.setAddress(address);
        //设置用户审核状态：0表示未审核
        user.setIsAuthenticated("0");
        PropertyFilter filter = new PropertyFilter("roleName", userType);
        RoleEntity role = roleEntityService.search(filter).get(0);
        //通过角色保存角色
        user.setRole(role);
        //保存抽象公共用户
        userEntityService.saveOrUpdate(user);

		//个人用户
		if (userType.equals("个人")){
			personUser.setUserId(userId);
            //保存个人用户
			personUserEntityService.saveOrUpdate(personUser);
		}

        //管理员
        if (userType.equals("管理员")){
            admin.setUserId(userId);
            //保存管理员
            adminEntityService.saveOrUpdate(admin);
        }

		//创建路径：
        String rootPath = req.getSession().getServletContext().getRealPath("/") + "upload/user/";
		File fileRoot = new File(rootPath);
        fileRoot.mkdirs();
        String filePath = rootPath + "";
        //企业用户
        if (userType.equals("企业")){
            //对于企业类型的文件存放路径根目录为：rootPath + "/compamy/"
            String companyRootPath = rootPath + "company/"+ user.getUserId() + "/";
            File companyFileRoot = new File(companyRootPath);
            companyFileRoot.mkdirs();
            List<MultipartFile> files = multipartRequest.getFiles("files");
            //判断file数组不能为空并且长度大于0
            if(files!=null&&files.size() > 0){
                //循环获取file数组中的文件，并保存
                for(int i = 0;i<files.size();i++){
                    MultipartFile file = files.get(i);
                    String [] splitOrigFile = file.getOriginalFilename().split("\\.");
                    //获得文件后缀名
                    String extensionName = splitOrigFile[splitOrigFile.length - 1];
                    String fileName = "";
                    //身份证正面
                    if (i == 0) {
                        fileName = user.getRegistrantId() + "_front." + extensionName;
                        filePath = companyRootPath + fileName;
                        companyUser.setIdFrontUrl("upload/user/company/"+ user.getUserId() + "/" + fileName);
                    }
                    //身份证反面
                    if (i == 1) {
                        fileName = user.getRegistrantId() + "_back." + extensionName;
                        filePath = companyRootPath + fileName;
                        companyUser.setIdBackUrl("upload/user/company/"+ user.getUserId() + "/" + fileName);
                    }
                    //营业执照
                    if (i == 2) {
                        fileName = companyUser.getBusinessLicenceId() + "_businesslicence." + extensionName;
                        filePath = companyRootPath + fileName;
                        companyUser.setBusinessLicenceUrl("upload/user/company/"+ user.getUserId() + "/" + fileName);
                    }
                    //保存文件:compamy信息，以及文件信息
                    saveFile(filePath, file);
                    companyUser.setUserId(userId);
                    companyUserEntityService.saveOrUpdate(companyUser);
                }
            }
        }

        //事业单位
        if (userType.equals("事业单位")){
            String institutionRootPath = rootPath + "institution/"+ user.getUserId() + "/";
            File institutionFileRoot = new File(institutionRootPath);
            institutionFileRoot.mkdirs();
            List<MultipartFile> files = multipartRequest.getFiles("files");
            //判断file数组不能为空并且长度大于0
            if(files!=null&&files.size() > 0){
                //循环获取file数组中的文件，并保存
                for(int i = 0;i<files.size();i++){
                    MultipartFile file = files.get(i);
                    String [] splitOrigFile = file.getOriginalFilename().split("\\.");
                    //获得文件后缀名
                    String extensionName = splitOrigFile[splitOrigFile.length - 1];
                    String fileName = "";
                    //身份证正面
                    if (i == 0) {
                        fileName = user.getRegistrantId() + "_front." + extensionName;
                        filePath = institutionRootPath + fileName;
                        institutionUser.setIdFrontUrl("upload/user/institution/"+ user.getUserId() + "/" + fileName);
                    }
                    //身份证反面
                    if (i == 1) {
                        fileName = user.getRegistrantId() + "_back." + extensionName;
                        filePath = institutionRootPath + fileName;
                        institutionUser.setIdBackUrl("upload/user/institution/"+ user.getUserId() + "/" + fileName);
                    }
                    //事业单位登记图
                    if (i == 2) {
                        fileName = companyUser.getBusinessLicenceId() + "_institude." + extensionName;
                        filePath = institutionRootPath + fileName;
                        institutionUser.setInstitudeCodeUrl("upload/user/institution/"+ user.getUserId() + "/" + fileName);
                    }
                    //保存文件:institution信息，以及文件信息
                    saveFile(filePath, file);
                    institutionUser.setUserId(userId);
                    institutionUserEntityService.saveOrUpdate(institutionUser);
                }
            }
        }

        //政府
        if (userType.equals("政府")){
            String govRootPath = rootPath + "gov/"+ user.getUserId() + "/";
            File govFileRoot = new File(govRootPath);
            govFileRoot.mkdirs();
            List<MultipartFile> files = multipartRequest.getFiles("files");
            //判断file数组不能为空并且长度大于0
            if(files!=null&&files.size() > 0){
                //循环获取file数组中的文件，并保存
                for(int i = 0;i<files.size();i++){
                    MultipartFile file = files.get(i);
                    String [] splitOrigFile = file.getOriginalFilename().split("\\.");
                    //获得文件后缀名
                    String extensionName = splitOrigFile[splitOrigFile.length - 1];
                    String fileName = "";
                    //身份证正面
                    if (i == 0) {
                        fileName = user.getRegistrantId() + "_front." + extensionName;
                        filePath = govRootPath + fileName;
                        govUser.setIdFrontUrl("upload/user/gov/"+ user.getUserId() + "/" + fileName);
                    }
                    //身份证反面
                    if (i == 1) {
                        fileName = user.getRegistrantId() + "_back." + extensionName;
                        filePath = govRootPath + fileName;
                        govUser.setIdBackUrl("upload/user/gov/"+ user.getUserId() + "/" + fileName);
                    }
                    //政府登记图
                    if (i == 2) {
                        fileName = companyUser.getBusinessLicenceId() + "_institude." + extensionName;
                        filePath = govRootPath + fileName;
                        govUser.setGovCodeUrl("upload/user/gov/"+ user.getUserId() + "/" + fileName);
                    }
                    //保存文件:gov信息，以及文件信息
                    saveFile(filePath, file);
                    govUser.setUserId(userId);
                    govUserEntityService.saveOrUpdate(govUser);
                }
            }
        }

		return "login";
	}

	//保存文件
	private boolean saveFile(String filePath, MultipartFile file){
        System.out.println(filePath + " : ...");
        if (!file.isEmpty()){
            try {
                file.transferTo(new File(filePath));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
	    return true;
    }

    //通过用户名验证用户是否存在
    @RequestMapping("getUserByUsername")
    public void getUserByUsername(@RequestParam String username, HttpServletResponse resp) throws IOException {
        PropertyFilter filters = new PropertyFilter("userName", username);
        List<UserEntity> users = userEntityService.search(filters);
        PrintWriter out = resp.getWriter();
        if (users.size() != 0){
            out.print("isExist");
        }else {
            out.print("isNotExist");
        }
    }

    //通过邮箱验证用户是否存在
    @RequestMapping("getUserByEmail")
    public void getUserByEmail(@RequestParam String email, HttpServletResponse resp) throws IOException {
        System.out.println("邮箱：" + email);
//        //在多个字段查询时，用的到
//        PropertyFilter filters = new PropertyFilter("email|userName", keuWords, MatchType.EQ);
        PropertyFilter filters = new PropertyFilter("email", email);
        List<UserEntity> users = userEntityService.search(filters);
        PrintWriter out = resp.getWriter();
        if (users.size() != 0){
            out.print("isExist");
        }else{
            out.print("isNotExist");
        }
    }

    /** 个人中心 - 跳转
     * 其中，管理员:0, 政府1, 事业单位2, 企业3, 个人4
     * @param id
     * @return
     */
    @RequestMapping("my/{id}")
    public String myInfoCenter(@PathVariable String id, HttpServletRequest req, Model mode){
        String userId = req.getParameter("userId");
        UserEntity user = userEntityService.load(userId);
        if(id.equals("0")){
            AdminEntity admin = adminEntityService.load(userId);
            mode.addAttribute("commonUser", user);
            mode.addAttribute("user", admin);
            return "/WEB-INF/admin/my";
        }else{
            return "/WEB-INF/foreground/";
        }
    }

	//修改个人信息
	@RequestMapping("modMyInfo")
	public String modMyInfo(UserEntity user, AdminEntity admin, HttpServletRequest req,Model mode){
	    String province = req.getParameter("province");
	    String city = req.getParameter("city");
	    String area = req.getParameter("area");
        String address = province + " " + city + " " + area;
        user.setAddress(address);
	    UserEntity commonUser = (UserEntity)req.getSession().getAttribute("commonUser");
        if(user.getPassword().equals("")){
            String pwd = commonUser.getPassword();
            user.setPassword(pwd);
        }
        user.setRole(commonUser.getRole());
        userEntityService.saveOrUpdate(user);
        adminEntityService.saveOrUpdate(admin);
        mode.addAttribute("commonUser", user);
        mode.addAttribute("user", admin);
		return "/WEB-INF/admin/my";
	}
	
	//注销用户
	@RequestMapping
	public String logout(HttpServletRequest req){
		System.out.println("load to logout ......");
		req.getSession().removeAttribute("user");
		
		return "login";
	}
	
	@RequestMapping
	public void isExistUser(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		System.out.println("已经加载...");
		String username = req.getParameter("u_name");
		PropertyFilter filters = new PropertyFilter("u_name",username);
		List<User> searchUsers = userService.search(filters);
		PrintWriter out = resp.getWriter();
		if (searchUsers.size()!=0) {
			if (searchUsers.get(0)!=null) {
				out.print("success");
			}
		}
	}
	
	//发送邮件注册
	@RequestMapping
	public void sendMail(HttpServletRequest req) throws MessagingException{
		System.out.println("已经加载...");
		String usermail = req.getParameter("u_mail");
		StringBuffer stringBuffer = new StringBuffer("验证码<br>");
		String code = ValidationCode.getValidationCode(4);
		stringBuffer.append(code);
		req.getSession().setAttribute("valication_mail_code", code);		
		SendMails.sendMail(usermail, stringBuffer.toString());
	}
	
	//发送手机验证码注册：
	@RequestMapping
	public void sendTelephoneCode(HttpServletRequest req) throws ApiException, JsonGenerationException, JsonMappingException, IOException{
		System.out.println("已经加载到tel..");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString("123");
		TeleValidCode.getTeleValidCode();
		
	}
	
	//注册模块--结束
	@RequestMapping
	public String add(User user) {
		userService.saveOrUpdate(user);
		return "redirect:/user/list/1.do";
	}
		
	@Deprecated
	@RequestMapping
	public String list2(@RequestParam(defaultValue="1") int pageNo, Model model) {
		Page<User> page = new Page<User>(5);
		page.setPageNo(pageNo);
		userService.loadAll(page);
		model.addAttribute("page", page);
		return "user/list";
	}

	
	@InitBinder
	public void init(WebDataBinder binder) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
	}
	
}
