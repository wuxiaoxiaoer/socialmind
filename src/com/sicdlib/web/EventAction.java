package com.sicdlib.web;

import com.alibaba.fastjson.JSON;
import com.sicdlib.entity.ArticleEntity;
import com.sicdlib.entity.DynamicObject;
import com.sicdlib.entity.EventEntity;
import com.sicdlib.entity.ObjectEntity;
import com.sicdlib.service.ArticleEntityService;
import com.sicdlib.service.EventEntityService;
import com.sicdlib.service.ObjectEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/event/")
public class EventAction {

    @Autowired
    private ObjectEntityService objectEntityService;
    @Autowired
    private EventEntityService eventEntityService;
    @Autowired
    private ArticleEntityService articleEntityService;

    //查找事件列表
    @RequestMapping("all")
    public String findAll(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {
        List<ObjectEntity> objectList = new ArrayList<>();
        objectList =    objectEntityService.findObject();
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

    //查找事件信息
    @RequestMapping("eventInfo")
    public String findEventInfo(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {
        String objectId = req.getParameter("objectId");
        List<ObjectEntity> objectInfo = objectEntityService.findObjectInfo(objectId);
        List<EventEntity> eventInfo = eventEntityService.findEventInfo(objectId);
        List<ArticleEntity> keywords = articleEntityService.findKeywords(objectId);
        List<ArticleEntity> artileList = articleEntityService.findArticleList(objectId);
        List mediaList = articleEntityService.findMedias(objectId);
        System.out.println("json==="+JSON.toJSON(mediaList).toString());;
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

        mode.addAttribute("mediaList", JSON.toJSON(mediaList));
        mode.addAttribute("artileList", artileList);
        mode.addAttribute("keywords", keywords);
        mode.addAttribute("event", event);
        return "/WEB-INF/foreground/eventInfo";
    }

    //分析事件趋势
    @RequestMapping("eventTrend")
    public String findEventTrend(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {
        String objectId = req.getParameter("objectId");
        List<ObjectEntity> objectInfo = objectEntityService.findObjectInfo(objectId);
        List<EventEntity> eventInfo = eventEntityService.findEventInfo(objectId);
        List<ArticleEntity> artileList = articleEntityService.findArticleList(objectId);
        String year = " ";
        List event = new ArrayList();
        for(int i = 0 ; i < objectInfo.size() ; i++) {
            for(int j = 0 ; j < eventInfo.size() ; j++) {
                if (objectInfo.get(i).getObjectId().equals(eventInfo.get(j).getObjectId())){
                    year = eventInfo.get(i).getEventBeginTime().substring(0,3);
                    DynamicObject dy = new DynamicObject();
                    dy.setObject(objectInfo.get(i));
                    dy.setEvent(eventInfo.get(j));
                    event.add(dy);
                }
            }
        }

        mode.addAttribute("year", year);
        mode.addAttribute("event", event);
        mode.addAttribute("artileList", artileList);
        return "/WEB-INF/foreground/eventTrend";
    }


}
