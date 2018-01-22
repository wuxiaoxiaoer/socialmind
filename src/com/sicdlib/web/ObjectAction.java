package com.sicdlib.web;

import com.sicdlib.entity.*;
import com.sicdlib.service.EntityEntityService;
import com.sicdlib.service.EventEntityService;
import com.sicdlib.service.ObjectEntityService;
import com.sicdlib.service.ObjectUserEntityService;
import com.sicdlib.util.UUIDUtil.UUIDUtil;
import edu.xjtsoft.base.orm.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author DeMH
 * 舆情对象管理模块
 */
@Controller
@RequestMapping("/object/*")
public class ObjectAction {

	@Autowired
	private ObjectEntityService objectEntityService;
	@Autowired
	private EventEntityService eventEntityService;
	@Autowired
	private EntityEntityService entityEntityService;
	@Autowired
	private ObjectUserEntityService objectUserEntityService;

	//舆情对象展示
	@RequestMapping("objectsManager")
	public String objectsManager(@RequestParam(defaultValue = "1") int pageNo, Model mode){
		Page<ObjectEntity> page = new Page<ObjectEntity>(10);
		page.setPageNo(pageNo);
		objectEntityService.loadAll(page);
		List<DynamicObject> dynamicObjects = new ArrayList<>();
		for (ObjectEntity obj : page.getResult()){
			DynamicObject dynamicObject = new DynamicObject();
			dynamicObject.setObject(obj);
			if (obj.getObjectType().equals("事件")){
				EventEntity event = eventEntityService.load(obj.getObjectId());
				dynamicObject.setEvent(event);
			}else {
				EntityEntity entity = entityEntityService.load(obj.getObjectId());
				dynamicObject.setEntity(entity);
			}
			dynamicObjects.add(dynamicObject);
		}
		mode.addAttribute("dynamicObjects", dynamicObjects);
		mode.addAttribute("page", page);
		return "WEB-INF/admin/objectsManager";
	}

	@RequestMapping("managePersons")
	public String managePersons(HttpServletRequest req, Model mode){
		UserEntity user = (UserEntity) req.getSession().getAttribute("commonUser");

		Set<ObjectUserEntity> objectUsersSet = user.getObjectUsers();
		String flag = req.getParameter("flag");
		List<ObjectUserEntity> objectUsers = new ArrayList<>();
		for (ObjectUserEntity objUser : objectUsersSet){
			if (objUser.getoType().equals(flag)){
				objectUsers.add(objUser);
			}
		}
		mode.addAttribute("objectUsers", objectUsers);
		//选定舆情对象, 先like，如果存在，则选定；如果不存在，则填写具体信息（name，introduction，keywords）填入待爬取队列。
		/**
		 * 新增领导舆情对象
		 */
		if (flag.equals("addLeader")){
			String name = req.getParameter("name");
			String introduction = req.getParameter("introduction");
			List<ObjectEntity> searchObjects = objectEntityService.getObjectsLikeName(name);

			mode.addAttribute("searchObjects", searchObjects);
		}
		/**
		 * 选定舆情对象
		 */
		if (flag.equals("chooseObject")){
			String chooseId = req.getParameter("chooseId");
			String oType = req.getParameter("oType");
			ObjectUserEntity objectUserEntity = new ObjectUserEntity();
			objectUserEntity.setId(UUIDUtil.getUUID());
			ObjectEntity object = objectEntityService.load(chooseId);
			objectUserEntity.setObjectEntity(object);
			objectUserEntity.setoType(oType);
			objectUserEntity.setUserEntity(user);
			objectUserEntityService.saveOrUpdate(objectUserEntity);
		}
		/**
		 * 删除对象用户中间表
		 */
		if (flag.equals("deleteObjUser")){
			String ouId = req.getParameter("ouId");
			objectUserEntityService.remove(ouId);
		}
		return "WEB-INF/foreground/managePersons";
	}

}
