package com.sicdlib.web;

import com.alibaba.fastjson.JSON;
import com.kenai.jaffl.annotations.In;
import com.sicdlib.entity.*;
import com.sicdlib.service.*;
import com.sicdlib.util.NLPUtil.HanLPUtil.HanLPUtil;
import com.sicdlib.util.NLPUtil.Word2VecUtil.Test.Word2Vec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

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

	//获取事件的全部信息
	public List event(String objectId){
		List<ObjectEntity> objectInfo = objectEntityService.findObjectInfo(objectId);
		List<EventEntity> eventInfo = eventEntityService.findEventInfo(objectId);
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
		return event;
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
//		List<ProvinceEntity> provinceEntities = provinceService.getProvinces();
		List<Map> provinceList = provinceList(articleList);

		mode.addAttribute("objectId",objectId);
		mode.addAttribute("count", maxcount(provinceList));
		mode.addAttribute("event", event(objectId));
		mode.addAttribute("articleNum", articleList.size());
		mode.addAttribute("sensitiveCount", JSON.toJSON(sensitiveCount(list.size(),articleList.size()-list.size())));
		mode.addAttribute("mediaSource", JSON.toJSON(mediaSource));
		mode.addAttribute("sensilist", JSON.toJSON(sensitiveType(list)));
		mode.addAttribute("provinceList", JSON.toJSON(provinceList));
		mode.addAttribute("websiteSource", JSON.toJSON(websiteSource));
		mode.addAttribute("websites", JSON.toJSON(websites));
		mode.addAttribute("webs", JSON.toJSON(getWebsiteName()));
		mode.addAttribute("periodList", JSON.toJSON(findPeriod(objectId)));
		mode.addAttribute("websiteStatistic",JSON.toJSON(websiteStatistic(objectId)));
		return "/WEB-INF/foreground/info_detection_graph";
	}

	//地图信息展示
	public List<Map> provinceList(List<ArticleEntity> articleList){
		String[] province = {"北京", "天津", "河北", "山西", "内蒙古", "辽宁", "吉林", "黑龙江",
				"上海", "江苏", "浙江", "安徽", "福建", "江西", "山东", "河南", "湖北", "湖南", "广东", "广西", "海南",
				"重庆", "四川", "贵州", "云南", "西藏", "陕西", "甘肃", "青海", "宁夏", "新疆", "台湾", "香港", "澳门"};
		int[] provincenum = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		List<Map> provinceList = new ArrayList<>();
		for (int i=0;i<province.length;i++){
			Map map = new HashMap();
			map.put("name",province[i]);
			map.put("value",0);
			provinceList.add(map);
		}
		if(articleList!=null||articleList.size()!=0){
			Word2Vec vec = new Word2Vec();
			try {
				vec.loadGoogleModel("E:\\wiki_chinese_word2vec(Google).model");
//			vec.loadJavaModel("data/wikichinese.model");
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (ArticleEntity articles:articleList) {
				HashMap<String, String> places = HanLPUtil.getNERPlacesMap(articles.getContent());
				for (String place : places.keySet()) {
					for (int i=0;i<province.length;i++){
						if (place.toString().equals(province[i])) {
							for (int j=0;j<provinceList.size();j++) {
								if (place.toString().equals(provinceList.get(j).get("name"))){
									provinceList.get(j).put("value",++provincenum[j]);
								}
							}
						}else{
							//查找市所在的省份
							String provinceName = provinceService.findProvinceOfCity(place);
							if (provinceName!=null&&provinceName.contains(province[i])){
								for (int z=0;z<provinceList.size();z++) {
									if (province[i].equals(provinceList.get(z).get("name"))){
										provinceList.get(z).put("value",++provincenum[i]);
									}
								}
							}
						}
					}
					break;
				}

			}
		}
		return provinceList;
	}

	//地图上的最大信息量
	public String maxcount(List<Map> provinceList){
		List coutlist = new ArrayList();
		for (int i = 0;i<provinceList.size();i++){
			coutlist.add(provinceList.get(i).get("value"));
		}
		return Collections.max(coutlist).toString();
	}

	//页面的点击事件
	@RequestMapping("click")
	public String findEventArticle(HttpServletRequest req, HttpServletResponse resp, Model mode) throws IOException {

		String object = req.getParameter("object");
		String objectId = req.getParameter("objectId");

		//点击敏感事件
		List<DynamicSensitiveArticle> sensitiveList = new ArrayList<>();
		String[] sensitiveType= {"色情","暴恐","反动","贪腐","民生","其他"};
		if ("敏感".equals(object)){
			sensitiveList = sensitive(objectId);
			mode.addAttribute("sensitiveList", sensitiveList);
		}else if("非敏感".equals(object)){
			sensitiveList = sensitive(objectId);
			List nosensitive = articleEntityService.findEventNoSensitive(sensitiveList);
			mode.addAttribute("nosensitive", nosensitive);
		}
		//点击敏感的详细类型
		List<DynamicSensitiveArticle> sensitiveInfoList = new ArrayList<>();
		for (int i=0;i<sensitiveType.length;i++){
			if (sensitiveType[i].equals(object)){
				sensitiveList = sensitive(objectId);
				for (int j=0;j<sensitiveList.size();j++) {
					if (object.equals(sensitiveList.get(j).getSensitiveType())){
						DynamicSensitiveArticle dy = new DynamicSensitiveArticle();
						dy.setSensitiveType(object);
						dy.setArticle(sensitiveList.get(j).getArticle());
						dy.setSensitiveWord(sensitiveList.get(j).getSensitiveWord());
						sensitiveInfoList.add(dy);
					}
				}
			}
		}

		//点击媒体类型
		List<ArticleEntity> mediaList = new ArrayList<>();
		String[] mediaType= {"政府","论坛","博客","新闻","社交媒体"};
		for (int i=0;i<mediaType.length;i++){
			if(mediaType[i].equals(object)){
				mediaList = websiteEntityService.findMediaList(objectId,object);
				break;
			}
		}

		//点击网站类型
		List<ArticleEntity> websiteList = new ArrayList<>();
		List<WebsiteEntity> websiteName= websiteEntityService.findWebsite();
		for (int j=0; j<websiteName.size();j++){
			if(websiteName.get(j).getWebsiteName().equals(object)){
				websiteList = websiteEntityService.findWebsiteList(objectId,object);
				break;
			}
		}

		//点击地点
		List<ArticleEntity> articleList = articleEntityService.findArticleList(objectId);
		List<List> arealist = new ArrayList();
		for (ArticleEntity articles : articleList) {
			if (articles.getContent().contains(object)){
				List<ArticleEntity> areaInfo = articleEntityService.findArticleInfo(articles.getArticleId());
				arealist.add(areaInfo);
			}
		}
		mode.addAttribute("arealist", arealist);
		mode.addAttribute("websiteList", websiteList);
		mode.addAttribute("mediaList", mediaList);
		mode.addAttribute("sensitiveInfo", sensitiveInfoList);
		return "/WEB-INF/foreground/infodetail";
	}

	//
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
			List<Map> websiteList = articleEntityService.findWebsites(objectId,websiteID);
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

	}
