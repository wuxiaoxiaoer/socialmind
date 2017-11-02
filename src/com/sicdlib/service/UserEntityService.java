package com.sicdlib.service;

import com.sicdlib.entity.UserEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserEntityService extends DefaultEntityManager<UserEntity> {

	//后台用户搜索
//	public List<User> getSearchUsers(String search_key){
//		String hql = "select u from User u where u.u_name = ? or u.u_telphone = ? or u.u_mail = ?";
//		Object[] values = new Object[3];
//		values[0] = search_key;
//		values[1] = search_key;
//		values[2] = search_key;
//		List<User> users = getEntityDao().find(hql, values);
//		return users;
//	}
	
	//后台用户搜索
	public List<UserEntity> findUsers(String username){
		String hql = "select u from UserEntity u where u.userName = '" +username+"'";
		List<UserEntity> users = getEntityDao().find(hql);
		System.out.println(users.size());
		return null;
	}
}
