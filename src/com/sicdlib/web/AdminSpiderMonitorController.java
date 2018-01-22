package com.sicdlib.web;



import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.sicdlib.service.LogService;
import com.sicdlib.util.esUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zcx on 2017/11/9.
 */
@Controller
@RequestMapping("/*")
public class AdminSpiderMonitorController {



    @Autowired(required=true)
    private LogService logService;


    /**
     *进入爬虫监控
     *///
    @RequestMapping("spiderMonitor")
    public String spiderMonitor(Model model){

        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,List> errorDetailMap = new HashMap<String,List>();
        esUtil es=new esUtil();
        map=es.esClient();

        System.out.println("controller      "+map.get("peopleSpeedmap"));


        errorDetailMap=(Map)map.get("errorDetailMap");


        map.remove("errorDetailMap");//如果不加这一句传到前台会出错，可能和里面的各种转义字符括号有关



        model.addAttribute("map", JSON.toJSONString(map));
        model.addAttribute("errorDetailMap", errorDetailMap);

        return "/WEB-INF/admin/spiderMonitor";
    }




    /**
     *爬虫监控AJAX
     *///
    @RequestMapping("speedByWebsite")
    public void speedByWebsite( HttpServletRequest req, HttpServletResponse res){
        Map<String,List> map = new HashMap<String,List>();
        String website = req.getParameter("website");
        String source="/hbasestorage/spider/spider_logs/"+website+"/scrapy.logs";
        esUtil es=new esUtil();
        map=es.speed(source);
        Gson gson = new Gson();
        try {
            /*设置编码格式，返回结果
            * ***/
            res.setContentType( "text/html;charset=UTF-8");
            res.getWriter().write(gson.toJson(map));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }






}
