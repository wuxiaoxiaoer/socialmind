package com.sicdlib.web;

import com.alibaba.fastjson.JSON;
import com.sicdlib.entity.EventEntity;
import com.sicdlib.service.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
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
    @Autowired
    private WebsiteEntityService websiteEntityService;

    @RequestMapping("eventAnalysis")
    public String eventAnalysis(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {

        //根据事件类型统计事件数量
        List<Map> objectType = objectEntityService.objectType();
        List<Map> objectList = new ArrayList<>();
        List<String> objects = new ArrayList<>();
        for (Map map:objectType) {
            objects.add(map.get("name").toString());
            Map map1 = new HashMap();
            map1.put("name",map.get("name").toString());
            map1.put("value",objectEntityService.objectNumofType(map.get("objectID").toString()));
            objectList.add(map1);
        }

        //统计所有实体的数量
        List<String> entityList = objectEntityService.findObjectList();

        //事件总数统计-按年统计
        List<Map> eventByYear = eventEntityService.eventByYear();
        List<String> years = new ArrayList<>();
        List<String> yearCount = new ArrayList<>();
        for (Map map: eventByYear) {
            years.add(map.get("time").toString());
            yearCount.add(map.get("count").toString());
        }

        //事件访问量
        List<Map> eventClickNum = eventEntityService.allClickNum();

        //事件的持续时间
        List<Map> periodEvent = eventEntityService.eventPeriod();
        List<String> eventName = new ArrayList<>();
        List<String> eventPeriod = new ArrayList<>();
        for (Map map:periodEvent) {
            eventName.add(map.get("name").toString());
            eventPeriod.add(map.get("num").toString());
        }

        //各大网站的活跃数
        List<Map> websites = websiteEntityService.allWebsites();
        List<String> websiteName = new ArrayList<>();
        for (Map map:websites) {
            websiteName.add(map.get("name").toString());
        }

        mode.addAttribute("years", JSON.toJSON(years));
        mode.addAttribute("yearCount",JSON.toJSON(yearCount));
        mode.addAttribute("eventClickNum",JSON.toJSON(eventClickNum));
        mode.addAttribute("eventName", JSON.toJSON(eventName));
        mode.addAttribute("eventPeriod", JSON.toJSON(eventPeriod));
        mode.addAttribute("objectList", JSON.toJSON(objectList));
        mode.addAttribute("allEvent", JSON.toJSON(objectList.size()));
        mode.addAttribute("objects", JSON.toJSON(objects));
        mode.addAttribute("entity", JSON.toJSON(entityList));
        mode.addAttribute("entityNum", JSON.toJSON(entityList.size()));
        mode.addAttribute("websites", JSON.toJSON(websites));
        mode.addAttribute("websiteName", JSON.toJSON(websiteName));

        return "/WEB-INF/admin/eventAnalysis";
    }

    @RequestMapping("clickBackEvent")
    public String click(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {

        String objecType = req.getParameter("objecType");
        String object = req.getParameter("object");
//        String year = req.getParameter("year");

        if ("year".equals(objecType)){
            List<Map> eventByYear = eventEntityService.eventByYear();
            List<String> monthCount = new ArrayList<>();
            int[] months = {1,2,3,4,5,6,7,8,9,10,11,12};
            //根据年份查询每个月的数据
            for (int i=0;i<months.length;i++){
                monthCount.add(eventEntityService.eventByYearAndMonth(object,months[i]));
            }

            mode.addAttribute("yearCount", JSON.toJSON(eventByYear));
            mode.addAttribute("monthCount", JSON.toJSON(monthCount));


        }

        return "/WEB-INF/admin/newEventInfo";
    }



}
