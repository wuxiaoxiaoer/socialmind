package com.sicdlib.web;



import com.alibaba.fastjson.JSON;
import com.sicdlib.service.LogService;
import com.sicdlib.util.esUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
     *
     *///
    @RequestMapping("spiderMonitor")
    public String spiderMonitor(Model model){

        Map<String,Object> map = new HashMap<String,Object>();
        esUtil es=new esUtil();
        map=es.esClient();

        System.out.println("controller      "+map.get("peopleSpeedmap"));

        model.addAttribute("map", JSON.toJSONString(map));
        model.addAttribute("peopleSpeedmap", JSON.toJSONString(map.get("peopleSpeedmap")));
        //model.addAttribute("peopleSpeedList", JSON.toJSONString(map.get("peopleSpeedList")));

        return "/WEB-INF/admin/spiderMonitor";
    }










}
