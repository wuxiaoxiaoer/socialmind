package com.sicdlib.web;

import com.sicdlib.entity.*;
import com.sicdlib.service.*;
import com.sicdlib.util.UUIDUtil.UUIDUtil;
import edu.xjtsoft.base.orm.support.MatchType;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

//用户管理模块
@Controller
@RequestMapping("/*")
public class SystemOperation {

	@Autowired(required=true)
	private UserEntityService userEntityService;
	@Autowired
	private UserOperaEntityService userOperaEntityService;
	@Autowired
	private ObjectEntityService objectEntityService;
	@Autowired
	private DataDictionaryEntityService dataDictionaryEntityService;
	@Autowired
	private CommentEntityService commentEntityService;

	//临时设定上次登陆时间
	String date = "2017-11-11 00:00:00";
    //获得当前时间
    public String getTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(date);
        return strDate;
    }

	//前台：系统操作
	@RequestMapping(value="SystemOperation")
	public void SystemOperation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserEntity userEntity = (UserEntity) req.getSession().getAttribute("commonUser");
		String objId = req.getParameter("objId");
		String opera = req.getParameter("opera");
		ObjectEntity objectEntity = objectEntityService.load(objId);
		PropertyFilter filter = new PropertyFilter("attributeValue", opera);
		UserOperaEntity userOperaEntity = null;
		PrintWriter out = resp.getWriter();
		if (!opera.equals("isTrue")) {
			DataDictionaryEntity dataDictionaryEntity = dataDictionaryEntityService.search(filter).get(0);
			userOperaEntity = userOperaEntityService.getUserOpera(userEntity.getUserId(), objectEntity.getObjectId(),
					dataDictionaryEntity.getDataDictionaryId());
			//新增
			if (userOperaEntity == null) {
				userOperaEntity = new UserOperaEntity();
				userOperaEntity.setOperaId(UUIDUtil.getUUID());
				userOperaEntity.setObjectEntity(objectEntity);
				userOperaEntity.setUserEntity(userEntity);
				userOperaEntity.setOperaType(dataDictionaryEntity.getDataDictionaryId());
                userOperaEntity.setOperaTime(getTime());
				userOperaEntityService.saveOrUpdate(userOperaEntity);
				out.print(dataDictionaryEntity.getAttributeValue());
			}
			//删除
			else {
				userOperaEntityService.remove(userOperaEntity.getOperaId());
				out.print("remove" + dataDictionaryEntity.getAttributeValue());
			}
		}
		//判别真假
		if (opera.equals("isTrue")){
			filter = new PropertyFilter("attributeValue", "isTrue");
			DataDictionaryEntity dataDictTrue = dataDictionaryEntityService.search(filter).get(0);
			UserOperaEntity userOperaTrue = userOperaEntityService.getUserOpera(userEntity.getUserId(), objectEntity.getObjectId(),
					dataDictTrue.getDataDictionaryId());
			filter = new PropertyFilter("attributeValue", "isFalse");
			DataDictionaryEntity dataDictFalse = dataDictionaryEntityService.search(filter).get(0);
			UserOperaEntity userOperaFalse = userOperaEntityService.getUserOpera(userEntity.getUserId(), objectEntity.getObjectId(),
					dataDictFalse.getDataDictionaryId());
			//没真没假： 判真
			if (userOperaTrue == null && userOperaFalse == null){
				userOperaEntity = new UserOperaEntity();
				userOperaEntity.setOperaId(UUIDUtil.getUUID());
				userOperaEntity.setObjectEntity(objectEntity);
				userOperaEntity.setUserEntity(userEntity);
				//从数据字典中获得操作类型
				userOperaEntity.setOperaType(dataDictTrue.getDataDictionaryId());
                userOperaEntity.setOperaTime(getTime());
				userOperaEntityService.saveOrUpdate(userOperaEntity);
				out.print(dataDictTrue.getAttributeValue());
			}
			//没真有假情况下：判真
			if (userOperaTrue == null && userOperaFalse != null){
				//增真
				userOperaEntity = new UserOperaEntity();
				userOperaEntity.setOperaId(UUIDUtil.getUUID());
				userOperaEntity.setObjectEntity(objectEntity);
				userOperaEntity.setUserEntity(userEntity);
				userOperaEntity.setOperaType(dataDictTrue.getDataDictionaryId());
                userOperaEntity.setOperaTime(getTime());
				userOperaEntityService.saveOrUpdate(userOperaEntity);
				out.print(dataDictTrue.getAttributeValue());
				//除假
				userOperaEntityService.remove(userOperaFalse.getOperaId());
			}
			//有真没假：判假
			if (userOperaTrue != null && userOperaFalse == null){
				//增假
				userOperaEntity = new UserOperaEntity();
				userOperaEntity.setOperaId(UUIDUtil.getUUID());
				userOperaEntity.setObjectEntity(objectEntity);
				userOperaEntity.setUserEntity(userEntity);
				userOperaEntity.setOperaType(dataDictFalse.getDataDictionaryId());
                userOperaEntity.setOperaTime(getTime());
				userOperaEntityService.saveOrUpdate(userOperaEntity);
				out.print(dataDictFalse.getAttributeValue());
				//除真
				userOperaEntityService.remove(userOperaTrue.getOperaId());
			}
		}
	}

	@RequestMapping("systemComment")
	public void systemComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String comment = req.getParameter("comment");
		String objId = req.getParameter("objId");
		String commentId = req.getParameter("commentId");
		UserEntity userEntity = (UserEntity) req.getSession().getAttribute("commonUser");
		ObjectEntity objectEntity = objectEntityService.load(objId);
		CommentEntity commentEntity = new CommentEntity();
		commentEntity.setCommentId(UUIDUtil.getUUID());
		commentEntity.setCommentContent(comment);
		commentEntity.setUserEntity(userEntity);
		commentEntity.setObjectEntity(objectEntity);
		if (commentId != null){
			commentEntity.setFathercommentId(commentId);
		}
		commentEntity.setCommentTime(getTime());
		commentEntityService.saveOrUpdate(commentEntity);
		PrintWriter out = resp.getWriter();
		out.print("success");
	}


	//前台：系统评论展示
	@RequestMapping("systemCommentsShow")
	public String systemCommentsShow(HttpServletRequest req, Model mode){
		String objId = req.getParameter("objId");
		ObjectEntity objectEntity = objectEntityService.load(objId);
		mode.addAttribute("objectEntity", objectEntity);
		Page page = new Page(50);
		page.setPageNo(1);
		PropertyFilter filter = new PropertyFilter("objectEntity.objectId", objId);
		Page<CommentEntity> commentpage = commentEntityService.search(page, filter);
		Collections.sort(commentpage.getResult(), new Comparator<CommentEntity>() {
			@Override
			public int compare(CommentEntity o1, CommentEntity o2) {
				if (o1.getCommentTime().compareTo(o2.getCommentTime()) > 0){
					return -1;
				}
				return 0;
			}
		});
		mode.addAttribute("comments", commentpage.getResult());
		return "/WEB-INF/foreground/systemCommentsShow";
	}


	/**
	 * 后台：系统操作显示：显示系统操作，舆情对象的用户操作统计
	 */
	@RequestMapping("operationShow")
	public String operationShow(@RequestParam(defaultValue = "1") int pageNo, HttpServletRequest req, Model mode){
		Page<UserOperaEntity> page = new Page<>(20);
		page.setPageNo(pageNo);
		PropertyFilter filter = new PropertyFilter("operaTime", date, MatchType.GT);
		userOperaEntityService.search(page, filter);
		mode.addAttribute("userOperasPage", page);
		//从数据字典中获得操作名称
		PropertyFilter filter_dic = new PropertyFilter("attributeName", "operateName");
		List<DataDictionaryEntity> operas = dataDictionaryEntityService.search(filter_dic);
		mode.addAttribute("operas", operas);
		Page<ObjectEntity> objectPage = new Page<>(10);
		page.setPageNo(pageNo);
		objectEntityService.loadAll(objectPage);
		mode.addAttribute("objectPage", objectPage);
		return "/WEB-INF/admin/operationShow";
	}
	/**
	 * 后台：显示某舆情对象的用户具体操作：如某事件的点赞详细信息
	 */
	@RequestMapping("userOperaDetail")
	public String userOperaDetail(HttpServletRequest req, Model mode){
		String objId = req.getParameter("objId");
		String op = req.getParameter("op");
		List<UserOperaEntity> userOperas = userOperaEntityService.getUserOperasByObjIdAndOpera(objId, op);
		mode.addAttribute("userOperas", userOperas);
		PropertyFilter filter_dic = new PropertyFilter("attributeName", "operateName");
		List<DataDictionaryEntity> operas = dataDictionaryEntityService.search(filter_dic);
		mode.addAttribute("operas", operas);
		return "/WEB-INF/admin/userOperaDetail";
	}

}