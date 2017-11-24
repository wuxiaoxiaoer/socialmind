package com.sicdlib.web;



import com.alibaba.fastjson.JSON;
import com.sicdlib.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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
    public String roleManage(Model model){






        return "/WEB-INF/admin/spiderMonitor";
    }










}
