package com.sicdlib.service;

import com.sicdlib.entity.EventEntity;
import com.sicdlib.entity.ObjectEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ObjectEntityService extends DefaultEntityManager<ObjectEntity> {

    /**
     * 获得舆情对象列表
     * @param objectType      舆情对象类型  事件/实体
     * @param addTime         开始时间      2017-11-07 15:11:00
     * @return
     */
    public List<ObjectEntity> getObjectIndicators(String objectType, String place, String addTime){
        String hql = "from ObjectEntity o where o.objectType = '"+ objectType +  "' and place like '%"+ place +"' and o.addTime > '"+ addTime +"'";
        List<ObjectEntity> objects = getEntityDao().find(hql);
        return objects;
    }

    //前台查找事件列表
    public List<ObjectEntity> findEvent(){
        String hql = "select * from EventEntity e,ObjectEntity o where e.objectId = o.objectId";
        List<ObjectEntity> events = getEntityDao().find(hql);
        System.out.println(events.size());
        return null;
    }

}
