package com.sicdlib.service;

import com.sicdlib.entity.EventEntity;
import com.sicdlib.entity.ObjectEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class EventEntityService extends DefaultEntityManager<EventEntity> {

    //前台查找事件列表
    public List<EventEntity> findEvent(){
        String hql = "from EventEntity e";
        List<EventEntity> event = getEntityDao().find(hql);
        return event;
    }

    public List<EventEntity> findEventInfo(String objectId){
        String hql = "from EventEntity e where e.objectId = '" +objectId+"'";
        List<EventEntity> eventInfo = getEntityDao().find(hql);
        return eventInfo;
    }
}
