package com.sicdlib.web;

import com.alibaba.fastjson.JSON;
import com.sicdlib.entity.ObjectEntity;
import com.sicdlib.service.ArticleEntityService;
import com.sicdlib.service.AuthorEntityService;
import com.sicdlib.service.CommentEntityService;
import com.sicdlib.service.ObjectEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//用户管理模块
@Controller
@RequestMapping("/*")
public class EventStatisticsAction {

	@Autowired(required=true)
	private ObjectEntityService objectEntityService;
	@Autowired(required=true)
	private ArticleEntityService articleEntityService;
	@Autowired(required=true)
	private AuthorEntityService authorEntityService;
	@Autowired(required=true)
	private CommentEntityService commentEntityService;

	/**
	 * 全部事件统计分析
	 * 统计事件数量，事件在各网站的分布
	 */
	@RequestMapping(value="eventStatisticsAnalysis")
	public String eventStatisticsAnalysis(Model mode){
		List<ObjectEntity> objects = objectEntityService.loadAll();
		//事件数量
		Integer eventsSize = objects.size();
		mode.addAttribute("eventSize", eventsSize);
		//非谣言事件数量
		Integer trueEventSize = 0;
		//谣言事件数量
		Integer falseEventSize = 0;
		for (ObjectEntity object : objects){
			if (object.getCredibility().equals("true")){
				trueEventSize ++;
			}
			if (object.getCredibility().equals("false")){
				falseEventSize ++;
			}

		}
		mode.addAttribute("trueEventSize", trueEventSize);
		mode.addAttribute("falseEventSize", falseEventSize);
		//文章数量
		Long articlesSize = articleEntityService.getArticlesCount();
		mode.addAttribute("articlesSize", articlesSize);
		//作者数量
		Long authorsSize = authorEntityService.getAuthorsCount();
		mode.addAttribute("authorsSize", authorsSize);
		//评论数量
		Long commentsSize = commentEntityService.getCommentsCount();
		mode.addAttribute("commentsSize", commentsSize);
		//事件分布（网站名、对象名、可信度、数量）obj[0,1,2,3]
		List<Object[]> webObjectCredNum = articleEntityService.getWebsiteObjCount();
		//网站中事件数量
		Map<String, Integer> webEvents = new HashMap<>();
		//网站中可信度数量
		Map<String, Integer> webCredibility = new HashMap<>();
		//网站中总文章数量
		Map<String, Integer> webArticleNum = new HashMap<>();
		//网站名列表
		List<String> websiteList = new ArrayList<>();
		for (Object[] obj : webObjectCredNum){
			String websiteName = (String) obj[0];
			String object = (String) obj[1];
			String credibility = (String) obj[2];
			BigInteger articleNum = (BigInteger) obj[3];
			websiteList.add(websiteName);
			//网站事件数量
			if (webEvents.get(websiteName) == null){
				webEvents.put(websiteName, 1);
			}else {
				webEvents.put(websiteName, webEvents.get(websiteName) + 1);
			}

			//网站非谣言数量
			if (credibility.equals("true")){
				if (webCredibility.get(websiteName) == null){
					webCredibility.put(websiteName, 1);
				} else {
					webCredibility.put(websiteName, webCredibility.get(websiteName) + 1);
				}
			}

			//网站文章数量
			if (webArticleNum.get(websiteName) == null){
				webArticleNum.put(websiteName, Integer.valueOf(articleNum.toString()));
			} else {
				webArticleNum.put(websiteName, webArticleNum.get(websiteName) + Integer.valueOf(articleNum.toString()));
			}
		}
		Map<String, Object[]> webstatistics = new HashMap<>();
		for (String websiteName : websiteList){
			Object[] objs = new Object[3];
			//网站事件数量
			objs[0] = webEvents.get(websiteName);
			//可信度事件数量
			objs[1] = webCredibility.get(websiteName);
			//文章数量
			objs[2] = webArticleNum.get(websiteName);
			webstatistics.put(websiteName, objs);
		}
		mode.addAttribute("webstatistics", webstatistics);
		return "/WEB-INF/admin/eventStatisticsAnalysis";
	}

	/**
	 * 具体事件统计
	 */
	@RequestMapping("eventStaticsticInfo")
	public void eventStaticsticInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String flag = req.getParameter("flag");
		PrintWriter out = resp.getWriter();
		if (flag.equals("all")){
			List<ObjectEntity> objects = objectEntityService.loadAll();
			String objsJson = JSON.toJSONString(objects);
			out.print(objsJson);
		}
		if (flag.equals("")){

		}
	}
}
