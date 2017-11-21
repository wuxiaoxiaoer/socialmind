package com.sicdlib.web;

import com.sicdlib.entity.DynamicObject;
import com.sicdlib.entity.EntityEntity;
import com.sicdlib.entity.EventEntity;
import com.sicdlib.entity.ObjectEntity;
import com.sicdlib.service.EntityEntityService;
import com.sicdlib.service.EventEntityService;
import com.sicdlib.service.ObjectEntityService;
import edu.xjtsoft.base.orm.support.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
}
