package com.sicdlib.web.admin;

import com.sicdlib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//后台主页模块
@Controller
@RequestMapping("/admin/*")
public class AdminIndex {

	@Autowired(required=true)
	private UserService userService;

	//后台主页
	@RequestMapping(value="index")
	public String index(){
		return "/WEB-INF/admin/index";
	}

}
