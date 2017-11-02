package com.sicdlib.web;

import com.sicdlib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//用户管理模块
@Controller
@RequestMapping("/*")
public class Index {

	@Autowired(required=true)
	private UserService userService;

	//用户登陆
	@RequestMapping(value="login")
	public String login(){
		return "/WEB-INF/foreground/login";
	}


	//信息监测
	@RequestMapping(value="infodetection", method = RequestMethod.GET)
	public String infodetection(){
		return "/WEB-INF/foreground/info_dectection";
	}

}
