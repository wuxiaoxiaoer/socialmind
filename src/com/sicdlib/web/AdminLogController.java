package com.sicdlib.web;


import com.sicdlib.entity.LogEntity;
import com.sicdlib.service.LogService;
import edu.xjtsoft.base.orm.support.Page;
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
public class AdminLogController {



    @Autowired(required=true)
    private LogService logService;


    /**
     * 日志
     *///
    @RequestMapping("log")
    public String roleManage(Model model){

        List<LogEntity> logList=new ArrayList<LogEntity>();

        Page<LogEntity> pageLog=new Page<LogEntity>(100);
        pageLog.setPageNo(1);
        logList=logService.loadAll(pageLog).getResult();


        model.addAttribute("logList",logList);

        return "/WEB-INF/admin/log";
    }










}
