package com.sicdlib.web;

import com.sicdlib.entity.CleanStrategyEntity;
import com.sicdlib.service.CleanStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/*")
public class DataCleanConfAction {
    @Autowired
    private CleanStrategyService cleanStrategyService;
    //这里映射的是主页面的超链接
    @RequestMapping("dataCleanConf")
    public String dataCleanConf(HttpServletRequest req, HttpServletResponse response, Model model){
        List<List<CleanStrategyEntity>> strategyList =cleanStrategyService.getStrategies();
        model.addAttribute("loseDataList",strategyList.get(0));
        model.addAttribute("errorString",strategyList.get(1));
        model.addAttribute("recordOperating",strategyList.get(2));
        model.addAttribute("dateOperating",strategyList.get(3));
        model.addAttribute("addressOperating",strategyList.get(4));
        return "/WEB-INF/admin/dataClean_conf";
    }

    @RequestMapping(value = "/admin/strategyConfAction")
    public String strategyConf(HttpServletRequest request, HttpServletResponse response, Model model){

        String strategyID =request.getParameter("strategyID");
        List<CleanStrategyEntity> strategyList1 =cleanStrategyService.getUsingStrategies(strategyID);
        List<CleanStrategyEntity> strategyList2 =cleanStrategyService.getNotUsingStrategies(strategyID);
        model.addAttribute("leftList",strategyList1);
        model.addAttribute("rightList",strategyList2);
        return "/WEB-INF/admin/strategyConf";
    }
}
