package com.sicdlib.web;

import com.alibaba.fastjson.JSON;
import com.sicdlib.entity.*;
import com.sicdlib.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
    private ArticleCommentEntityService articleCommentEntityService;
    @Autowired
    private CommentEntityService commentEntityService;
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

        List<ArticleCommentEntity> articleCommentList = articleCommentEntityService.findArticleComment(hotAuthor.get(0).getAuthorId());
        List<CommentEntity> comment = commentEntityService.findPeopleComment(objectId);


        List periodList = articleEntityService.findPeriod(objectId);
        List hotInformation = articleEntityService.findHotInformation(objectId);
        List<WebsiteEntity> webs = websiteEntityService.findWebsite();
       /* List websiteStatistic = new ArrayList();
        for(int i = 0 ; i < webs.size() ; i++) {
            List list = new ArrayList();
            for(int j = 0 ; j < periodList.size() ; j++) {
                time = periodList.get(j).toString();
                List<Map> websiteList = articleEntityService.findWebsites(objectId,time);
                List list1 = new ArrayList();
                for(int z = 0 ; z < websiteList.size() ; z++) {
                    list1.add(websiteList.get(z).get("websiteNum").toString());
                }
                System.out.println(list1.toString());
                list.add(list1);
            }
            websiteStatistic.add(list);
        }*/


        //网站统计。。。。。开始
        List websiteStatistic = new ArrayList();
        for(int j = 0 ; j < periodList.size() ; j++) {
            time = periodList.get(j).toString();
            List<Map> websiteList = articleEntityService.findWebsites(objectId,time);
            List list1 = new ArrayList();
            for(int z = 0 ; z < websiteList.size() ; z++) {
                list1.add(websiteList.get(z).get("websiteNum").toString());
            }
            websiteStatistic.add(list1);
        }
        String[] array = new String[websiteStatistic.size()];
        // List转换成数组
        String[] newArray = {};
        for (int i = 0; i < websiteStatistic.size(); i++) {
            array[i] = websiteStatistic.get(i).toString();
            System.out.println("3:"+array[i]);
        }

        //网站统计。。。。。结束

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
        List<IndicatorValueEntity> reliablity = indicatorValueEntityService.getObjectReliablity(objectId);

        mode.addAttribute("mediaSource", JSON.toJSON(mediaSource));
        mode.addAttribute("areaSource", JSON.toJSON(areaSource));
        mode.addAttribute("periodList", JSON.toJSON(periodList));
        mode.addAttribute("webs",JSON.toJSON(webs));
        mode.addAttribute("websiteStatistic",websiteStatistic);
        mode.addAttribute("artileList", artileList);
        mode.addAttribute("hotInformation", hotInformation);
        mode.addAttribute("hotAuthor", hotAuthor);
        mode.addAttribute("articleCommentList", articleCommentList);
        mode.addAttribute("comment", comment);
        mode.addAttribute("keywords", JSON.toJSON(keywords).toString());
        mode.addAttribute("event", event);
        mode.addAttribute("reliablity", reliablity);
        return "/WEB-INF/foreground/eventInfo";
    }
}
