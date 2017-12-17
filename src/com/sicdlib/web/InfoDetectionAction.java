package com.sicdlib.web;

import com.alibaba.fastjson.JSON;
import com.sicdlib.entity.*;
import com.sicdlib.service.*;
import edu.xjtsoft.base.orm.support.MatchType;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
	@Autowired
	private ProvinceService provinceService;
	

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
		//查找事件下信息总数
		List<ArticleEntity> articleList = articleEntityService.findArticleList(objectId);
		//查找事件下媒体来源信息
		List<Map> mediaSource = websiteEntityService.findMediaSource(objectId);
		//查找事件下网站来源信息
		List<Map> websiteSource = websiteEntityService.findWebsiteSource(objectId);
		List websites = new ArrayList();
		for (int i = 0;i<websiteSource.size();i++){
			websites.add(websiteSource.get(i).get("name"));
		}
		//查找事件中的敏感文章
		List<DynamicSensitiveArticle> list = sensitive(objectId);

		//查找事件中文章和作者的地域分布
//		area();
		List<ProvinceEntity> provinceEntities = provinceService.getProvinces();
		for (ProvinceEntity province:provinceEntities) {
			System.out.println(province.getName());
		}
		mode.addAttribute("articleNum", articleList.size());
		mode.addAttribute("sensitiveCount", JSON.toJSON(sensitiveCount(list.size(),articleList.size()-list.size())));
		mode.addAttribute("mediaSource", JSON.toJSON(mediaSource));
		mode.addAttribute("sensilist", JSON.toJSON(sensitiveType(list)));
		mode.addAttribute("websiteSource", JSON.toJSON(websiteSource));
		mode.addAttribute("websites", JSON.toJSON(websites));
		mode.addAttribute("webs", JSON.toJSON(getWebsiteName()));
		mode.addAttribute("periodList", JSON.toJSON(findPeriod(objectId)));
		mode.addAttribute("websiteStatistic",JSON.toJSON(websiteStatistic(objectId)));
		return "/WEB-INF/foreground/info_detection_graph";
	}

	//统计敏感与不敏感数量
	public List<Map> sensitiveCount(int sensitive,int nonsensitive){
		List<Map> sensitiveCount = new ArrayList<>();
		Map map0 = new HashMap();
		map0.put("name","敏感");
		map0.put("value",sensitive);
		Map map1 = new HashMap();
		map1.put("name","非敏感");
		map1.put("value",nonsensitive);
		sensitiveCount.add(map0);
		sensitiveCount.add(map1);
		return sensitiveCount;
	}
	//统计敏感类型
	public List<Map> sensitiveType(List<DynamicSensitiveArticle> list){
		int countSeqing = 0;
		int countBaokong = 0 ;
		int countFandong = 0;
		int countTanfu = 0;
		int countMinsheng = 0;
		int countQita = 0;
		List<Map> sensilist = new ArrayList<>();
		for (DynamicSensitiveArticle dy:list){
			if ("色情".equals(dy.getSensitiveType())){
				++countSeqing;
			}
			if ("暴恐".equals(dy.getSensitiveType())){
				++countBaokong;
			}
			if ("反动".equals(dy.getSensitiveType())){
				++countFandong;
			}
			if ("贪腐".equals(dy.getSensitiveType())){
				++countTanfu;
			}
			if ("民生".equals(dy.getSensitiveType())){
				++countMinsheng;
			}
			if ("其他".equals(dy.getSensitiveType())){
				++countQita;
			}
		}
		String[] sensitiveType= {"色情","暴恐","反动","贪腐","民生","其他"};
		for(int i = 0;i<sensitiveType.length; i++){
			Map map = new HashMap();
			map.put("name",sensitiveType[i]);
			if ("色情".equals(sensitiveType[i])){
				map.put("value",countSeqing);
			}
			if ("暴恐".equals(sensitiveType[i])){
				map.put("value",countBaokong);
			}
			if ("反动".equals(sensitiveType[i])){
				map.put("value",countFandong);
			}
			if ("贪腐".equals(sensitiveType[i])){
				map.put("value",countTanfu);
			}
			if ("民生".equals(sensitiveType[i])){
				map.put("value",countMinsheng);
			}
			if ("其他".equals(sensitiveType[i])){
				map.put("value",countQita);
			}
			sensilist.add(map);
		}
		return sensilist;
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

	//鉴别敏感词与敏感类型
	public List<DynamicSensitiveArticle> sensitive(String objectId){
		File csv = new File("E:\\learn\\ideaproject\\socialmind\\data\\sensitiveLib\\sensitive.txt");
		BufferedReader breader = null;
		try {
			breader = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = "";
		List<DynamicSensitiveArticle> sensitiveList = new ArrayList<>();
		try {
			while ((line = breader.readLine()) != null){
                DynamicSensitiveArticle dySenArticle = new DynamicSensitiveArticle();
                String [] str = line.split("\t");
                //敏感词word
                dySenArticle.setSensitiveWord(str[3]);
                //敏感词type
                dySenArticle.setSensitiveType(str[1]);
                sensitiveList.add(dySenArticle);
            }
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<DynamicSensitiveArticle> list = new ArrayList();
		List<ArticleEntity> articleList = articleEntityService.findArticleList(objectId);
		for (ArticleEntity article: articleList) {
			DynamicSensitiveArticle sensitiveArticle = new DynamicSensitiveArticle();
			for (DynamicSensitiveArticle dySenArticle : sensitiveList){
				if (article.getContent().contains(dySenArticle.getSensitiveWord())){
					sensitiveArticle.setArticle(article);
					sensitiveArticle.setSensitiveWord(dySenArticle.getSensitiveWord());
					sensitiveArticle.setSensitiveType(dySenArticle.getSensitiveType());
					if(!dySenArticle.getSensitiveWord().equals(" ")){
						list.add(sensitiveArticle);
					}
					break;
				}
			}
		}

		return list;

	}

	
	public void area(){
		//查询省名
		List list = new ArrayList();
		
	}

	}
