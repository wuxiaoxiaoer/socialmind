package com.sicdlib.web;

import com.sicdlib.entity.ArticleEntity;
import com.sicdlib.entity.ArticleLabeledEntity;
import com.sicdlib.entity.ObjectEntity;
import com.sicdlib.service.ArticleEntityService;
import com.sicdlib.service.ArticleLabeledEntityService;
import com.sicdlib.service.ObjectEntityService;
import com.sicdlib.util.UUIDUtil.UUIDUtil;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//信息可信度标注模块
@Controller
@RequestMapping("/label/*")
public class LabelCredibilityAction {

	@Autowired
	private ObjectEntityService objectEntityService;
	@Autowired
	private ArticleEntityService articleEntityService;
	@Autowired
	private ArticleLabeledEntityService articleLabeledEntityService;


	//可信度标注主页
	@RequestMapping(value="credibilityIndex")
	public String credibilityIndex(HttpServletRequest req, Model mode){
		List<ObjectEntity> objects = objectEntityService.loadAll();
		mode.addAttribute("objects", objects);
		return "/WEB-INF/admin/credibilityIndex";
	}

	//文章列表
	@RequestMapping("essaysList")
	public String essaysList(HttpServletRequest req, Model mode){
		String objectId = req.getParameter("objectId");
		ObjectEntity object = objectEntityService.load(objectId);
		Set<ArticleEntity> articles = object.getArticles();
		mode.addAttribute("articles", articles);
		mode.addAttribute("object", object);
		//列出文章的可信度与可信类型
		List<ArticleLabeledEntity> articleLabeleds = new ArrayList<>();
		for (ArticleEntity article : articles){
			PropertyFilter filter = new PropertyFilter("articleId", article.getArticleId());
			List<ArticleLabeledEntity> artlabs = articleLabeledEntityService.search(filter);
			if (artlabs.size() != 0){
				articleLabeleds.add(artlabs.get(0));
			}
		}
		mode.addAttribute("articleLabeleds", articleLabeleds);
		return "/WEB-INF/admin/essaysList";
	}

	//对信息可信度及可信类型进行标注
	@RequestMapping("labeled")
	public void labeled(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String articleId = req.getParameter("articleId");
		String name = req.getParameter("name");
		String value = req.getParameter("value");
		PropertyFilter filter = new PropertyFilter("articleId", articleId);
		List<ArticleLabeledEntity> articleLabeleds = articleLabeledEntityService.search(filter);
		ArticleLabeledEntity articleLabeledEntity = null;
		if (articleLabeleds.size() != 0){
			articleLabeledEntity = articleLabeleds.get(0);
		}else {
			articleLabeledEntity = new ArticleLabeledEntity();
			articleLabeledEntity.setId(UUIDUtil.getUUID());
			articleLabeledEntity.setArticleId(articleId);
		}
		if (name.contains("isTrue")){
			articleLabeledEntity.setIsTrue(value);
		}
		if (name.contains("credType")){
			articleLabeledEntity.setCredType(value);
		}
		articleLabeledEntityService.saveOrUpdate(articleLabeledEntity);
		PrintWriter out = resp.getWriter();
		out.print("ok");
	}


}
