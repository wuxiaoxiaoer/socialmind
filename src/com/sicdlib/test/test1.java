package com.sicdlib.test;

import com.sicdlib.entity.UserEntity;
import com.sicdlib.service.UserEntityService;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by DeMH on 2017/11/6.
 */
public class test1 {
    ApplicationContext atx = new ClassPathXmlApplicationContext("beans.xml");
	UserEntityService userEntityService = (UserEntityService) atx.getBean("userEntityService");
    @Test
    public void test(){
        Page<UserEntity> page = new Page<>(10);
        PropertyFilter filter = new PropertyFilter("isAuthenticated", "0");
        Page<UserEntity> users = userEntityService.search(page, filter);
        for (UserEntity user : users.getResult()){
            System.out.println("用户名: " + user.getUserName());
        }
    }

}
