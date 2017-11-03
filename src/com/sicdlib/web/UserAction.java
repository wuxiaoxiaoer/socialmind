package com.sicdlib.web;

import com.sicdlib.entity.PersonUserEntity;
import com.sicdlib.entity.User;
import com.sicdlib.entity.UserEntity;
import com.sicdlib.service.PersonUserEntityService;
import com.sicdlib.service.UserEntityService;
import com.sicdlib.service.UserService;
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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
import java.util.Date;
import java.util.List;

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

	
	//登录模块
	@RequestMapping("login")
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String username = req.getParameter("username");
		String userpwd = req.getParameter("userpwd");
		boolean isRemPwd = Boolean.parseBoolean(req.getParameter("isRemPwd"));
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		PropertyFilter filters = new PropertyFilter("userName",username);
		List<UserEntity> searchUsers = userEntityService.search(filters);
		System.out.println(userpwd + " : " + MD5Util.generatePassword(userpwd));
		if (searchUsers.size() != 0) {
            UserEntity user = searchUsers.get(0);
			if(user != null){
				if (MD5Util.validatePassword(searchUsers.get(0).getPassword(), userpwd)) {
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
					if (user.getUserType().equals("0")){
						session.setAttribute("user", user);
						out.print("admin");
						return ;
                    }else {
                    	out.print("foreground");
						return ;
					}

				}
			}
		}
		out.print("failure");
	}

	//注册模块--跳转
	@RequestMapping(value="register", method = RequestMethod.GET)
	public String register(){
		return "register";
	}

	//注册模块--用户注册(政府: 1; 企业: 2; 事业单位: 3; 个人: 4)
	@RequestMapping(value="register", method = RequestMethod.POST)
	public String register(MultipartHttpServletRequest multipartRequest, UserEntity user, PersonUserEntity personUser, HttpServletRequest req){
	    String province = req.getParameter("province");
	    String city = req.getParameter("city");
	    String area = req.getParameter("area");
        //设置地区
        String address = province +" " + city + " " + area;
		//设置用户ID
		String userId = UUIDUtil.getUUID();
		user.setUserId(userId);
        user.setAddress(address);
        //保存抽象公共用户
        userEntityService.saveOrUpdate(user);

		//个人用户
		if (user.getUserType().equals("4")){
			personUser.setUserId(userId);
            //保存个人用户
			personUserEntityService.saveOrUpdate(personUser);
		}
		//路径：
		File rootPath = new File(req.getSession().getServletContext().getRealPath("/") + "upload/");
        List<MultipartFile> files = multipartRequest.getFiles("files");
        //不存在则创建
        rootPath.mkdirs();
        System.out.println("路径为： "+ rootPath + " : " + files.size());
        //企业用户
        if (user.getUserType().equals("3")){
            //判断file数组不能为空并且长度大于0
            if(files!=null&&files.size()>0){
                //循环获取file数组中得文件
                for(int i = 0;i<files.size();i++){
                    MultipartFile file = files.get(i);
                    System.out.println("源文件名： " + file.getOriginalFilename());
                    //保存文件
                    saveFile(file);
                }
            }
        }
		return "login";
	}
	//保存文件
	private boolean saveFile(MultipartFile file){
        if (!file.isEmpty()){
            try {
                String filePath;
            }catch (Exception e){}
        }
	    return true;
    }
	
	//注册
	@RequestMapping
	public String reg(HttpServletRequest req , User user){

		return "redirect:../login.jsp";
	}
	
	//修改个人信息
	@RequestMapping
	public String modMyInfo(HttpServletRequest req, User user, Model mode){
		
		User sessionUser = (User) req.getSession().getAttribute("user");
		System.out.println(user.getU_name());
		System.out.println(user.getU_pwd());
		System.out.println(user.getU_mail());
		System.out.println(user.getU_telphone());
//		if(!"***".equals(user.getU_pwd())){
//			session_user.setU_pwd(user.getU_pwd());
//		}
		sessionUser.setU_name(user.getU_name());
		sessionUser.setU_mail(user.getU_mail());
		sessionUser.setU_telphone(user.getU_telphone());
		userService.saveOrUpdate(sessionUser);
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		return "/WEB-INF/index";
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
