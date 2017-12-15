package com.sicdlib.web;

import com.alibaba.fastjson.JSON;
import com.sicdlib.entity.DynamicObject;
import com.sicdlib.entity.EventEntity;
import com.sicdlib.entity.ObjectEntity;
import com.sicdlib.entity.WebsiteEntity;
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

//信息监测模块
@Controller
@RequestMapping("/infodetection/*")
public class InfoDetectionAction {

	@Autowired(required=true)
	private UserService userService;
	@Autowired
	private ArticleEntityService articleEntityService;
	@Autowired
	private WebsiteEntityService websiteEntityService;
	@Autowired
	private ObjectEntityService objectEntityService;
	@Autowired
	private EventEntityService eventEntityService;

	//搜索
	@RequestMapping("search")
	public String reg(HttpServletRequest req){
		return "WEB-INF/foreground/info_detection_text";
	}

	@RequestMapping("detection")
	public String findEvent(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {

		String object = req.getParameter("object");
		//关键字模糊查询事件
		List<ObjectEntity> objectList = objectEntityService.findObject(object);
		List event = new ArrayList();
		int end = 30;
		for (int i = 0; i<objectList.size();i++){
			String objectId = objectList.get(i).getObjectId();
			List<EventEntity> eventList = eventEntityService.findEventById(objectId);
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

		mode.addAttribute("event",event);
		return "/WEB-INF/foreground/info_detection";
	}

	@RequestMapping("graph")
	public String findEventInfo(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {
		String objectId = req.getParameter("objectId");
		//查找事件下媒体来源信息
		List<Map> mediaSource = websiteEntityService.findMediaSource(objectId);
		//查找事件下网站来源信息
		List<Map> websiteSource = websiteEntityService.findWebsiteSource(objectId);
		List websites = new ArrayList();
		for (int i = 0;i<websiteSource.size();i++){
			websites.add(websiteSource.get(i).get("name"));
		}
		mode.addAttribute("mediaSource", JSON.toJSON(mediaSource));
		mode.addAttribute("websiteSource", JSON.toJSON(websiteSource));
		mode.addAttribute("websites", JSON.toJSON(websites));
		mode.addAttribute("webs", JSON.toJSON(getWebsiteName()));
		mode.addAttribute("periodList", JSON.toJSON(findPeriod(objectId)));
		mode.addAttribute("websiteStatistic",JSON.toJSON(websiteStatistic(objectId)));
		return "/WEB-INF/foreground/info_detection_graph";
	}

	//统计所有网站
	public List<WebsiteEntity> webs(){
		return websiteEntityService.findWebsite();
	}

	//获取网站名称
	public List getWebsiteName(){
		List webs_store = new ArrayList();
		for(int i = 0 ; i < webs().size() ; i++) {
			webs_store.add(webs().get(i).getWebsiteName());
		}
		return webs_store;
	}

	//统计事件的网站
	public List websiteStatistic(String objectId){
		List websiteStatistic = new ArrayList();
		for(int i = 0 ; i < webs().size() ; i++) {
			String websiteID = webs().get(i).getWebsiteId();
			String websiteName = webs().get(i).getWebsiteName();
			List<Map> websiteList = articleEntityService.findWebsites(objectId,websiteID,websiteName);
			Map map = new HashMap();
			map.put("name",websiteName);
			map.put("type","line");
			map.put("stack","发布文章的数量");
			map.put("data",websiteList);
			websiteStatistic.add(map);
		}
		return websiteStatistic;
	}
	//查事件的事件段
	public List findPeriod(String objectId){
		return articleEntityService.findPeriod(objectId);
	}

	}
