package com.sicdlib.web;

import com.sicdlib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

//信息监测模块
@Controller
@RequestMapping("/*")
public class fullRetrivalAction {

	@Autowired(required=true)
	private UserService userService;

	//搜索
	@RequestMapping("fullRetrival")
	public String fullRetrival(HttpServletRequest req){
		return "WEB-INF/foreground/fullRetrival";
	}
	//搜索
	@RequestMapping("fullRetrivalResults")
	public String fullRetrivalResults(HttpServletRequest req){
		return "WEB-INF/foreground/fullRetrivals";
	}

}
