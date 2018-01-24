package com.sicdlib.web;

import com.alibaba.fastjson.JSON;
import com.sicdlib.service.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/")
public class EventAnalysisBackAction {

    @Autowired
    private ObjectEntityService objectEntityService;
    @Autowired
    private EventEntityService eventEntityService;
    @Autowired
    private ArticleEntityService articleEntityService;

    @RequestMapping("eventAnalysis")
    public String eventAnalysis(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {

        //获取当前日期
        /*Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH )+1;
        int date = cal.get(Calendar.DATE);*/

        //事件总数统计-按年统计
        List<Map> eventByYear = eventEntityService.eventByYear();
        List<String> years = new ArrayList<>();
        List<String> yearCount = new ArrayList<>();
        for (Map map: eventByYear) {
            years.add(map.get("time").toString());
            yearCount.add(map.get("count").toString());
        }

        mode.addAttribute("years", JSON.toJSON(years));
        mode.addAttribute("yearCount",JSON.toJSON(yearCount));

        return "/WEB-INF/admin/eventAnalysis";
    }

    @RequestMapping("clickBackEvent")
    public String click(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {

        String objecType = req.getParameter("objecType");
        String object = req.getParameter("object");
        String year = req.getParameter("year");


        if ("\"年\"".equals(objecType)){
            if ("null".equals(year)){
                //当year为null，object的内容为年份，查询12个月数据
                List<String> monthCount = new ArrayList<>();
                int[] months = {1,2,3,4,5,6,7,8,9,10,11,12};
                //根据年份查询每个月的数据
                for (int i=0;i<months.length;i++){
                    monthCount.add(eventEntityService.eventByYearAndMonth(object,months[i]));
                }
                mode.addAttribute("object", JSON.toJSON(object));
                mode.addAttribute("month", JSON.toJSON(months));
                mode.addAttribute("monthCount", JSON.toJSON(monthCount));
            }
            if (!"null".equals(year)){
                // //当year不为null，object的内容为月份，查询每个月的数据
                List<String> dayCount = new ArrayList<>();
                String[] days = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
                for (int i=0;i<days.length;i++){
                    dayCount.add(eventEntityService.eventByYearAndMonthAndDay(year,object,days[i]));
                }
                mode.addAttribute("year", JSON.toJSON(year));
                mode.addAttribute("object", JSON.toJSON(object));
                mode.addAttribute("days", JSON.toJSON(days));
                mode.addAttribute("dayCount", JSON.toJSON(dayCount));
            }
        }

        return "/WEB-INF/admin/newEventInfo";
    }

}
