package com.sicdlib.web;


import com.sicdlib.entity.CleanLogManagerEntity;
import com.sicdlib.util.aop.AopLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/*")

public class DataCleanDiaryAction {
    @Autowired
    AopLogService aopLogService;
    @RequestMapping("dataCleanDiary")
    public String dataCleanDiary(HttpServletRequest req, HttpServletResponse response, Model model){
        List<CleanLogManagerEntity> LogList = aopLogService.getLog();
        for(int i=0;i<LogList.size();i++){
            System.out.println(LogList.get(i).getUserDefineOldValue());
        }
        model.addAttribute("cleanLogList",LogList);
        return "/WEB-INF/admin/dataClean_diary";
    }
}
