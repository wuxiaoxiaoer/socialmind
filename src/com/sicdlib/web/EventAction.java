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
import java.util.HashMap;
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
    private KeywordRelatedDegreeService keywordRelatedDegreeService;
    @Autowired
    private DataDictionaryEntityService dataDictionaryEntityService;
    @Autowired
    private KeywordEntityService keywordEntityService;
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

        List<ObjectEntity> objectInfo = objectEntityService.findObjectInfo(objectId);
        List<EventEntity> eventInfo = eventEntityService.findEventInfo(objectId);
        List<Map> keywords = keywordEntityService.findKeywords(objectId);
        List listkey = new ArrayList();
        List<Map> keywordRelated = keywordRelatedDegreeService.findKeywordRelated(objectId);

        String target = "";
        for(int a = 0 ; a < keywordRelated.size() ; a++) {
            for (int b = 0; b < keywords.size(); b++) {
                if(keywordRelated.get(a).get("target").equals(keywords.get(b).get("keywordId"))){
                    target = keywords.get(b).get("name").toString();
                }
            }
        }
        for(int a = 0 ; a < keywordRelated.size() ; a++) {
//            List keylist = new ArrayList();
            for (int b = 0; b < keywords.size(); b++) {
                if(keywords.get(b).get("keywordId").equals(keywordRelated.get(a).get("source"))){
                    Map maps = new HashMap();
                    maps.put("target",target);
                    maps.put("source",keywords.get(b).get("name"));
                    maps.put("weight",keywordRelated.get(a).get("weight"));
                    listkey.add(maps);
//                    keylist.add(maps);
                }
            }

        }


        /*for(int a = 0 ; a < keywordRelated.size() ; a++) {
            for(int b = 0 ; b < keywords.size() ; b++) {
                Map map = new HashMap();
                String target = "";
                if(keywordRelated.get(a).get("target").equals(keywords.get(b).get("keywordId"))){
                    target = keywords.get(b).get("name").toString();
                }
                if(keywordRelated.get(a).get("source").equals(keywords.get(b).get("keywordId"))){
                    keywordRelated.get(a).put("target",keywords.get(b).get("name"));
                    map.put("source",keywords.get(b).get("name"));
                }
                    map.put("target",keywords.get(b).get("name"));

                if(keywordRelated.get(a).get("source").equals(keywords.get(b).get("keywordId"))){
                    map.put("source",keywords.get(b).get("name"));
                }
                map.put("weight",keywordRelated.get(a).get("weight"));
                listkey.add(map);
            }

        }*/
        List keywordList = new ArrayList();
        for(int m = 0 ; m < keywords.size() ; m++) {
            Map map = new HashMap();
            if (m==0){
                map.put("category",0);
            }else {
                map.put("category",1);
            }
            map.put("name",keywords.get(m).get("name"));
            map.put("value",keywords.get(m).get("value"));
            keywordList.add(map);

        }
        List<ArticleEntity> artileList = articleEntityService.findArticleList(objectId);
        List<AuthorEntity> hotAuthor = authorEntityService.findHotAuthor(objectId);
        List<Map> mediaSource = websiteEntityService.findMediaSource(objectId);
        List<DataDictionaryEntity> allMedias = dataDictionaryEntityService.findAllMedias();

        List areaSource = indicatorValueEntityService.getObjectArea(objectId);
        List<IndicatorValueEntity> reliablity = indicatorValueEntityService.getObjectReliablity(objectId);
        List<ArticleCommentEntity> articleCommentList = articleCommentEntityService.findArticleComment(hotAuthor.get(0).getAuthorId());
        List periodList = articleEntityService.findPeriod(objectId);
        List hotInformation = articleEntityService.findHotInformation(objectId);
        List<WebsiteEntity> webs = websiteEntityService.findWebsite();
        List webs_store = new ArrayList();
        List websiteStatistic = new ArrayList();
        List event = new ArrayList();

        List firstWebsite = websiteEntityService.findFirstWeb(objectId);
        for(int i = 0 ; i < webs.size() ; i++) {
            webs_store.add(webs.get(i).getWebsiteName());
        }
        for(int i = 0 ; i < webs.size() ; i++) {
            String websiteID = webs.get(i).getWebsiteId();
            String websiteName = webs.get(i).getWebsiteName();
            List<Map> websiteList = articleEntityService.findWebsites(objectId,websiteID,websiteName);
            Map map = new HashMap();
            map.put("name",websiteName);
            map.put("type","line");
            map.put("stack","发布文章的数量");
            map.put("data",websiteList);
            websiteStatistic.add(map);
        }
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

        List mediaList = new ArrayList();
        for(int i = 0 ; i < allMedias.size() ; i++) {
            mediaList.add(allMedias.get(i).getAttributeValue());
        }


        mode.addAttribute("firstWebsite", JSON.toJSON(firstWebsite));
        mode.addAttribute("mediaList", JSON.toJSON(mediaList));
        mode.addAttribute("mediaSource", JSON.toJSON(mediaSource));
        mode.addAttribute("areaSource", JSON.toJSON(areaSource));
        mode.addAttribute("periodList", JSON.toJSON(periodList));
        mode.addAttribute("webs",JSON.toJSON(webs_store));
        mode.addAttribute("websiteStatistic",JSON.toJSON(websiteStatistic));
        mode.addAttribute("artileList", artileList);
        mode.addAttribute("hotInformation", hotInformation);
        mode.addAttribute("hotAuthor", hotAuthor);
        mode.addAttribute("articleCommentList", articleCommentList);
        mode.addAttribute("keywords", JSON.toJSON(keywords).toString());
        mode.addAttribute("keywordRelated", JSON.toJSON(keywordRelated).toString());
        mode.addAttribute("keywordList", JSON.toJSON(keywordList).toString());
        mode.addAttribute("listkey", JSON.toJSON(listkey).toString());
        mode.addAttribute("event", event);
        mode.addAttribute("reliablity", reliablity);
        return "/WEB-INF/foreground/eventInfo";
    }
}
