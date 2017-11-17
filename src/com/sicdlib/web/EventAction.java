package com.sicdlib.web;

import com.alibaba.fastjson.JSON;
import com.sicdlib.entity.*;
import com.sicdlib.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/event/")
public class EventAction {

    @Autowired
    private ObjectEntityService objectEntityService;
    @Autowired
    private EventEntityService eventEntityService;
    @Autowired
    private ArticleEntityService articleEntityService;
    @Autowired
    private AuthorEntityService authorEntityService;
    @Autowired
    private WebsiteEntityService websiteEntityService;
    @Autowired
    private IndicatorValueEntityService indicatorValueEntityService;

    //查找事件列表
    @RequestMapping("all")
    public String findAll(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {
        List<ObjectEntity> objectList = new ArrayList<>();
        objectList = objectEntityService.findObject();
        List<EventEntity> eventList = eventEntityService.findEvent();
        List event = new ArrayList();
        int end = 30;
        for(int i = 0 ; i < objectList.size() ; i++) {
            for(int j = 0 ; j < eventList.size() ; j++) {
                if (objectList.get(i).getObjectId().equals(eventList.get(j).getObjectId())){
                    if (eventList.get(j).getIntroduction().length()<end){
                        end = eventList.get(j).getIntroduction().length();
                    }
                    eventList.get(j).setIntroduction(eventList.get(j).getIntroduction().substring(0,end));
                    DynamicObject dy = new DynamicObject();
                    dy.setObject(objectList.get(i));
                    dy.setEvent(eventList.get(j));
                    event.add(dy);
                }
            }
        }
        mode.addAttribute("objectList", objectList);
        mode.addAttribute("eventList", eventList);
        mode.addAttribute("event", event);
        return "/WEB-INF/foreground/event";
    }

    /*@RequestMapping(value="test",produces="application/json;charset=UTF-8")
    @ResponseBody
    public String test(HttpServletRequest req, HttpServletResponse resp, Model mode){

        return "";
    }*/

    //查找事件信息
    @RequestMapping("eventInfo")
    public String findEventInfo(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {
        String objectId = req.getParameter("objectId");
        String time = "";
        List<ObjectEntity> objectInfo = objectEntityService.findObjectInfo(objectId);
        List<EventEntity> eventInfo = eventEntityService.findEventInfo(objectId);
        List<Map> keywords = articleEntityService.findKeywords(objectId);
        List<ArticleEntity> artileList = articleEntityService.findArticleList(objectId);
        List<AuthorEntity> hotAuthor = authorEntityService.findHotAuthor(objectId);
        List periodList = articleEntityService.findPeriod(objectId);

        List hotInformation = articleEntityService.findHotInformation(objectId);
        List<WebsiteEntity> webs = websiteEntityService.findWebsite();
        System.out.println("webs:"+webs.toString());
        System.out.println("webs to json"+JSON.toJSON(webs));
        List websiteStatistic = new ArrayList();
        for(int i = 0 ; i < periodList.size() ; i++) {
            time = periodList.get(i).toString();
            List websiteList = articleEntityService.findWebsites(objectId,time);
            System.out.println("websiteList :"+websiteList.toString());
            System.out.println("json.tojson:"+JSON.toJSON(websiteList));
        }


        /*String[] strings = new String[periodList.size()];
        System.out.println("periodList.toArray(strings):"+JSON.toJSON(strings));*/
        List event = new ArrayList();
        for(int i = 0 ; i < objectInfo.size() ; i++) {
            for(int j = 0 ; j < eventInfo.size() ; j++) {
                if (objectInfo.get(i).getObjectId().equals(eventInfo.get(j).getObjectId())){
                    DynamicObject dy = new DynamicObject();
                    dy.setObject(objectInfo.get(i));
                    dy.setEvent(eventInfo.get(j));
                    event.add(dy);
                }
            }
        }

        List mediaSource = websiteEntityService.findMediaSource(objectId);
        List areaSource = indicatorValueEntityService.getObjectArea(objectId);
        System.out.println("json de keywords:"+JSON.toJSON(keywords));
        mode.addAttribute("mediaSource", JSON.toJSON(mediaSource));
        mode.addAttribute("areaSource", JSON.toJSON(areaSource));
        mode.addAttribute("periodList", JSON.toJSON(periodList));
        mode.addAttribute("webs",JSON.toJSON(webs));
        mode.addAttribute("artileList", artileList);
        mode.addAttribute("hotInformation", hotInformation);
        mode.addAttribute("hotAuthor", hotAuthor);
        mode.addAttribute("keywords", JSON.toJSON(keywords).toString());
        mode.addAttribute("event", event);
        return "/WEB-INF/foreground/eventInfo";
    }
}
