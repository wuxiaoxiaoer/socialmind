//package com.sicdlib.web;
//
//import com.sicdlib.entity.*;
//import com.sicdlib.service.*;
//import edu.xjtsoft.base.orm.support.Page;
//import edu.xjtsoft.base.orm.support.PropertyFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
////信息监测模块
//@Controller
//@RequestMapping("/*")
//public class FullRetrivalAction {
//
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private WebsiteEntityService websiteEntityService;
//	@Autowired
//	private DataDictionaryEntityService dataDictionaryEntityService;
//	@Autowired
//	private ObjectEntityService objectEntityService;
//	@Autowired
//	private ArticleEntityService articleEntityService;
//	@Autowired
//	private AuthorEntityService authorEntityService;
//
//	//全文检索首页
//	@RequestMapping("fullRetrival")
//	public String fullRetrival(HttpServletRequest req, Model mode){
//		//列出推荐信息列表
//
//		//2.列出信息导航信息
//		List<WebsiteEntity> websites = websiteEntityService.loadAll();
//		String value = "websiteType";
//		PropertyFilter filter = new PropertyFilter("attributeName", value);
//		List<DataDictionaryEntity> dataDictionarys = dataDictionaryEntityService.search(filter);
//		mode.addAttribute("dataDictionarys", dataDictionarys);
//		mode.addAttribute("websites", websites);
//		return "WEB-INF/foreground/fullRetrival";
//	}
//
//	//全文检索结果/条件搜索
//	@RequestMapping("retrivalResults")
//	public String retrivalResults(HttpServletRequest req, Model mode){
//		//1.1 关键词全文搜索
//		String name = req.getParameter("name");
//		String flag = req.getParameter("flag");
//		PropertyFilter objFilter = new PropertyFilter("name", name);
//		ObjectEntity object = objectEntityService.search(objFilter).get(0);
//		mode.addAttribute("object", object);
//		mode.addAttribute("articles", object.getArticles());
//
//		//1.2 条件检索
//		if (flag.equals("condition")){
//			String objectId = req.getParameter("objectId");
//			String [] websiteIds = req.getParameterValues("websiteId");
//			String time = req.getParameter("time");
//			String similarity = req.getParameter("similarDegree");
//			String [] partQltyCbx = req.getParameterValues("partQltyCbx");
//			String [] topicCheckbox = req.getParameterValues("topicCheckbox");
//			Map<String, Object> map = new HashMap<>();
//			map.put("objectId", objectId);
//			map.put("websiteIds", websiteIds);
//
//			//时间格式化
//			Date date = new Date();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(date);
//			if (time != null){
//				//最近1天
//				if (time.equals("day1")){
//					cal.add(Calendar.DAY_OF_MONTH, -1);
//				}
//				//最近1周
//				if (time.equals("week1")){
//					cal.add(Calendar.DAY_OF_MONTH, -7);
//				}
//				//最近1月
//				if (time.equals("month1")){
//					cal.add(Calendar.DAY_OF_MONTH, -30);
//				}
//				//最近1年
//				if (time.equals("year1")){
//					cal.add(Calendar.DAY_OF_MONTH, -365);
//				}
//				Date dateTemp = cal.getTime();
//				time = formatter.format(dateTemp);
//			}
//			map.put("postTime", time);
//			map.put("similarDegree", similarity);
//			for (int i = 0 ; partQltyCbx != null && i < partQltyCbx.length; i++){
//				map.put(partQltyCbx[i] +"", "50");
//			}
//			for (int i = 0 ; topicCheckbox != null && i < topicCheckbox.length; i++){
//				map.put( "topic", topicCheckbox[i] +",");
//			}
//
//			Page<ArticleEntity> articlesPage = articleEntityService.getConditionArticles(map);
//			mode.addAttribute("articles", articlesPage.getResult());
//			mode.addAttribute("objectId", objectId);
//		}
//
//		//2.列出信息导航信息
//		List<WebsiteEntity> websites = websiteEntityService.loadAll();
//		mode.addAttribute("websites", websites);
//		String value = "websiteType";
//		PropertyFilter filter = new PropertyFilter("attributeName", value);
//		List<DataDictionaryEntity> dataDictionarys = dataDictionaryEntityService.search(filter);
//		mode.addAttribute("dataDictionarys", dataDictionarys);
//
//		return "WEB-INF/foreground/fullRetrivalResults";
//	}
//
//	/** 作者信息 */
//	@RequestMapping("authorInfo")
//	public String authorInfo(HttpServletRequest req, Model mode){
//		String authorId = req.getParameter("authorId");
//		AuthorEntity authorEntity = authorEntityService.load(authorId);
//		mode.addAttribute("authorEntity", authorEntity);
//		return "WEB-INF/foreground/authorInfo";
//	}
//
//}
