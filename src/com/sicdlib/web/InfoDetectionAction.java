package com.sicdlib.web;

import com.sicdlib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

//信息监测模块
@Controller
@RequestMapping("/infodetection/*")
public class InfoDetectionAction {

	@Autowired(required=true)
	private UserService userService;

	//搜索
	@RequestMapping("search")
	public String reg(HttpServletRequest req){
		return "WEB-INF/foreground/info_dectection_info_text";
	}

}
