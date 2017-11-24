package com.sicdlib.web;

import com.sicdlib.entity.CommentEntity;
import com.sicdlib.entity.DynamicSensitiveComment;
import com.sicdlib.service.CommentEntityService;
import com.sicdlib.service.DataDictionaryEntityService;
import com.sicdlib.service.ObjectEntityService;
import com.sicdlib.service.UserOperaEntityService;
import edu.xjtsoft.base.orm.support.MatchType;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/***
 * @Author wlw
 */
//评论管理模块
@Controller
@RequestMapping("/*")
public class CommentAction {

	@Autowired(required=true)
	private CommentEntityService commentEntityService;
	@Autowired
	private UserOperaEntityService userOperaEntityService;
	@Autowired
	private DataDictionaryEntityService dictionaryEntityService;
	@Autowired
	private ObjectEntityService objectEntityService;

	String date = "2017-11-11 00:00:00";

	//评论展示/删除/批量删除
	@RequestMapping(value="commentsManager")
	public String commentsManager(@RequestParam(defaultValue="1") int pageNo,
								  @RequestParam String flag, HttpServletRequest req, Model mode){
		//删除评论
		if (flag.equals("delete")){
			String commentId = req.getParameter("commentId");
			commentEntityService.remove(commentId);
		}

		//批量删除评论
		if (flag.equals("multidelete")){
			String[] commentIds = req.getParameterValues("commentId");
			for (String cid : commentIds){
				CommentEntity commentEntity = commentEntityService.load(cid);
				if (commentEntity != null){
					commentEntityService.remove(cid);
				}
			}
		}
		//敏感词

		//获得某个时间段的评论
		Page<CommentEntity> pageDateComment = new Page<CommentEntity>(100);
		pageDateComment.setPageNo(pageNo);
		//时间>=date
		PropertyFilter filter = new PropertyFilter("commentTime", date, MatchType.GT);
		commentEntityService.search(pageDateComment, filter);
		mode.addAttribute("pageDateComment", pageDateComment.getResult());
		Page<CommentEntity> page = new Page<CommentEntity>(10);
		page.setPageNo(pageNo);
		commentEntityService.loadAll(page);
		mode.addAttribute("page", page);
		return "/WEB-INF/admin/commentsManager";
	}

//	@RequestMapping("sensitive")
//	public String sensitive(@RequestParam(defaultValue="1") int pageNo, HttpServletRequest req,
//						  Model mode) throws IOException {
//		File csv = new File("D:\\project\\socialmind\\socialmind\\data\\sensitiveLib\\sensitive.txt");
//		System.out.println("路径：" + csv);
//		BufferedReader breader = null;
//		breader = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "UTF-8"));
//		String line = "";
//		List<String> sensitiveList = new ArrayList<>();
//		while ((line = breader.readLine()) != null){
//			String [] str = line.split("\t");
//			sensitiveList.add(str[3]);
//		}
//		PropertyFilter filter = new PropertyFilter("commentTime", date, MatchType.GT);
//		List<CommentEntity> sensitiveComments = new ArrayList<>();
//		List<CommentEntity> comments = commentEntityService.search(filter);
//		for (CommentEntity comment : comments){
//			for (String word : sensitiveList){
//				if (comment.getCommentContent().contains(word)){
//					sensitiveComments.add(comment);
//					break;
//				}
//			}
//		}
//		mode.addAttribute("pageDateComment", sensitiveComments);
//		Page<CommentEntity> page = new Page<CommentEntity>(10);
//		page.setPageNo(pageNo);
//		commentEntityService.loadAll(page);
//		mode.addAttribute("page", page);
//		return "/WEB-INF/admin/commentsManager";
//	}

	@RequestMapping("sensitiveList")
	public String sensitiveList(@RequestParam(defaultValue="1") int pageNo, HttpServletRequest req,
							Model mode) throws IOException {
		File csv = new File("D:\\project\\socialmind\\socialmind\\data\\sensitiveLib\\sensitive.txt");
		System.out.println("路径：" + csv);
		BufferedReader breader = null;
		breader = new BufferedReader(new InputStreamReader(new FileInputStream(csv), "UTF-8"));
		String line = "";
		List<DynamicSensitiveComment> sensitiveList = new ArrayList<>();
		while ((line = breader.readLine()) != null){
			DynamicSensitiveComment dySenComment = new DynamicSensitiveComment();
			String [] str = line.split("\t");
			//敏感词word
			dySenComment.setSensitiveWord(str[3]);
			//敏感词type
			dySenComment.setSensitiveType(str[1]);
			sensitiveList.add(dySenComment);
		}
		PropertyFilter filter = new PropertyFilter("commentTime", date, MatchType.GT);

		List<DynamicSensitiveComment> sensitiveComments = new ArrayList<>();
		List<CommentEntity> comments = commentEntityService.search(filter);
		for (CommentEntity comment : comments){
			DynamicSensitiveComment sensitiveComment = new DynamicSensitiveComment();
			for (DynamicSensitiveComment dySenComment : sensitiveList){
				if (comment.getCommentContent().contains(dySenComment.getSensitiveWord())){
					System.out.println("获得评论： " + comment.getCommentContent());
					sensitiveComment.setComment(comment);
					sensitiveComment.setSensitiveWord(dySenComment.getSensitiveWord());
					sensitiveComment.setSensitiveType(dySenComment.getSensitiveType());
					sensitiveComments.add(sensitiveComment);
					break;
				}
			}
		}
		mode.addAttribute("sensitiveComments", sensitiveComments);
		Page<CommentEntity> page = new Page<CommentEntity>(10);
		page.setPageNo(pageNo);
		commentEntityService.loadAll(page);
		mode.addAttribute("page", page);
		return "/WEB-INF/admin/sensitiveComments";
	}

}
