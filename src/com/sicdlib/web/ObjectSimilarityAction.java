package com.sicdlib.web;

import com.sicdlib.service.UserService;
import com.sicdlib.util.NLPUtil.HanLPUtil.HanLPUtil;
import com.sicdlib.util.NLPUtil.HanLPUtil.NERNameValue;
import com.sicdlib.util.NLPUtil.Word2VecUtil.Test.Word2Vec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//用户管理模块
@Controller
@RequestMapping("/*")
public class ObjectSimilarityAction {

	@Autowired(required=true)
	private UserService userService;

	//用户登陆
	@RequestMapping(value="login")
	public String login(HttpServletRequest req){
		String eventName = req.getParameter("eventName");
		Float simi = Float.parseFloat(req.getParameter("simi"));
		//1. 将事件分词进行NER获得带有权值的人名、地名、组织名列表
		List<NERNameValue> event_nerNameValues = HanLPUtil.getNERNameValuesMap(eventName);
		//1.2 还需要word2vec扩充事件动词V的同义词
		Word2Vec vec = new Word2Vec();
		List<String> keyWords = CommmonUtil.getKeywordsPOS(eventName, vec, 5);
		for (String kw : keyWords){
			NERNameValue ner = new NERNameValue(kw, "normal");
			event_nerNameValues.add(ner);
		}
		return "/WEB-INF/foreground/login";
	}

}
