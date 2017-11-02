package com.sicdlib.service;

import com.sicdlib.entity.UserEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserEntityService extends DefaultEntityManager<UserEntity> {

	/**
	 * 
	 * @param search_key
	 * @return
	 * @author shashajie
	 */
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
	
//	//后台用户搜索
//	public Page<User> getsearchUsers(int pageNo, String search_key){
//		String hql = "select u from User u where u.u_name = ? or u.u_telphone = ? or u.u_mail = ?";
//		Object[] values = new Object[3];
//		values[0] = search_key;
//		values[1] = search_key;
//		values[2] = search_key;
//		Page<User> page = new Page<User>(10);
//		page.setPageNo(pageNo);
//		Page<User> users = getEntityDao().find(page , hql, values);
//
//		return users;
//	}
}
