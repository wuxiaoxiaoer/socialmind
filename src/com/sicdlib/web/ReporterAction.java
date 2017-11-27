package com.sicdlib.web;

import com.sicdlib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

//用户管理模块
@Controller
@RequestMapping("/*")
public class ReporterAction {

	@Autowired(required=true)
	private UserService userService;

	//0. 制作模板首页
	@RequestMapping(value="makeReporter")
	public String makeReporter(){
		return "/WEB-INF/foreground/makeReporter";
	}

	//1. 模板样式列表
	@RequestMapping(value="reporterList")
	public String reporterList(){
		return "/WEB-INF/foreground/reporterList";
	}

    //2. 选择素材：素材1,2,3
    @RequestMapping("ReporterMaterial")
    public String ReporterMaterial(HttpServletRequest req, Model mode){
        String style = req.getParameter("style");
        mode.addAttribute("style", style);
        return "/WEB-INF/foreground/ReporterMaterial";
    }

    //3. 编辑素材
    @RequestMapping(value="reporterEdit")
    public String reporterEdit(HttpServletRequest req, Model mode){
        String style = req.getParameter("style");
        String objectId = req.getParameter("objectId");
        mode.addAttribute("style", style);
        System.out.println(style + " : " + objectId);
        return "/WEB-INF/foreground/reporterEdit";
    }

    //模板,选择不同模板
    @RequestMapping(value="reporter")
    public String reporter1(HttpServletRequest req){
        String style = req.getParameter("style");
        return "/WEB-INF/foreground/reporter_"+style;
    }

    //demo5
    @RequestMapping(value="makeReporter_5")
    public String makeReporter_5(){
        return "/WEB-INF/foreground/makeReporter_5";
    }

}
