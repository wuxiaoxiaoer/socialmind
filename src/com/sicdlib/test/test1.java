package com.sicdlib.test;

import com.sicdlib.entity.*;
import com.sicdlib.service.*;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DeMH on 2017/11/6.
 */
public class test1 {
    ApplicationContext atx = new ClassPathXmlApplicationContext("beans.xml");
	UserEntityService userEntityService = (UserEntityService) atx.getBean("userEntityService");
    IndicatorValueEntityService indicatorService = (IndicatorValueEntityService)atx.getBean("indicatorValueEntityService");
    ObjectEntityService objectEntityService = (ObjectEntityService) atx.getBean("objectEntityService");
    EventEntityService eventEntityService = (EventEntityService) atx.getBean("eventEntityService");
    EntityEntityService entityEntityService = (EntityEntityService) atx.getBean("entityEntityService");
    UserOperaEntityService userOperaEntityService = (UserOperaEntityService) atx.getBean("userOperaEntityService");
    @Test
    public void test(){
        Page<UserEntity> page = new Page<>(10);
        PropertyFilter filter = new PropertyFilter("isAuthenticated", "0");
        Page<UserEntity> users = userEntityService.search(page, filter);
        for (UserEntity user : users.getResult()){
            System.out.println("用户名: " + user.getUserName());
        }
    }

    @Test
    public void test01(){
        //String objectId, String indexName, String dimensionName,
        //String dimensionValue, String startTime, String endTime
        indicatorService.getObjectIndicators("1", "热度", "sex", "", "2016-10-10 12:00:00", "2017-10-25 12:00:00");
    }

    @Test
    public void test02(){
        List<ObjectEntity> objects = objectEntityService.getObjectIndicators("事件", "北京", "2016-10-10 12:00:00");
        System.out.println(objects.size());
    }

    @Test
    public void test03(){
        Page<ObjectEntity> page = new Page<ObjectEntity>(10);
        page.setPageNo(1);
        objectEntityService.loadAll(page);
        List<EventEntity> events = new ArrayList<>();
        List<EntityEntity> entities = new ArrayList<>();
        System.out.println(page.getResult().size());
        for (ObjectEntity obj : page.getResult()){
            System.out.println(obj.getObjectType());
            if (obj.getObjectType().equals("事件")){
                EventEntity event = eventEntityService.load(obj.getObjectId());
                events.add(event);
                System.out.println(event == null);
            }else {
                EntityEntity entity = entityEntityService.load(obj.getObjectId());
                entities.add(entity);
            }

        }
    }

    @Test
    public void test04(){
        objectEntityService.getObjectIndicators("事件","北京","2016-10-10 12:00:00");
    }

    @Test
    public void test05(){
        PropertyFilter objFilter = new PropertyFilter("name", "十九大召开");
        Page<ObjectEntity> page = new Page<>(10);
        page.setPageNo(1);
        Page<ObjectEntity> objectsPage = objectEntityService.search(page, objFilter);
        System.out.println(objectsPage.getResult().size());
    }

    @Test
    public void test06(){
        UserOperaEntity userOperaEntity = userOperaEntityService.getUserOpera("7699aee0-c48d-4a92-931c-b44e83d60bc1", "1", "5");
        System.out.println(userOperaEntity == null);
    }
}
