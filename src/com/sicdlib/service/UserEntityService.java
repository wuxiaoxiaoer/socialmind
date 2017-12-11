package com.sicdlib.service;

import com.sicdlib.entity.UserEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

	/**
	 * @ wlw
	 * 获得某个注册时间之后的用户
	 */
	public List<UserEntity> getUsersByTime(String time){
		String hql = "SELECT u FROM UserEntity u WHERE u.regTime > '" + time + "'";
		List<UserEntity> users = getEntityDao().find(hql);
		return users;
	}
	/**
	 * 获得某个时间段的用户
	 */
	public List<UserEntity> getUsersBetweenTimeAndRoleName(String startTime, String endTime, String roleName){
		String hql = "SELECT u FROM UserEntity u WHERE u.role.roleName = '"+ roleName +"' and u.regTime >= '" + startTime + "' and u.regTime < '" + endTime +"'";
		if (roleName.equals("ALL")){
			hql = "SELECT u FROM UserEntity u WHERE u.regTime >= '" + startTime + "' and u.regTime < '" + endTime +"'";
		}
		List<UserEntity> users = getEntityDao().find(hql);
		return users;
	}
	/**
	 * 获得最大或最小用户
	 */
	public UserEntity getMaxOrMinUser(String flag) {
		String hql = "";
		if (flag.equals("MAX")){
			hql = "SELECT u FROM UserEntity u ORDER BY u.regTime DESC LIMIT 0,1";
		}
		if (flag.equals("MIN")){
			hql = "SELECT u FROM UserEntity u ORDER BY u.regTime ASC LIMIT 0,1";
		}
		List<UserEntity> users = getEntityDao().find(hql);
		return users.get(0);
	}

	/**
	 * 获得某个时间段的用户
	 */
	public Map<String, List<Integer>> getUsersBetweenTime(String [] periods) throws ParseException {
		Map<String, List<Integer>> allRoleCount = new HashMap<>();
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = sdf.format(currentDate);
		List<Integer> counts = new ArrayList<>();
		List<Integer> adminCounts = new ArrayList<>();
		List<Integer> govCounts = new ArrayList<>();
		List<Integer> insCounts = new ArrayList<>();
		List<Integer> comCounts = new ArrayList<>();
		List<Integer> perCounts = new ArrayList<>();
		Long allInterval = Long.valueOf(0);
		if (!periods[2].equals("")){
			allInterval = Long.valueOf(periods[2]);
		}
//		if (periods[0].equals("ALL")){
//			UserEntity minRegTimeUser = getMaxOrMinUser("MIN");
//			UserEntity maxRegTimeUser = getMaxOrMinUser("MAX");
//			String minRegTime = minRegTimeUser.getRegTime();
//			String maxRegTime = maxRegTimeUser.getRegTime();
//			SimpleDateFormat sdfTemp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			Date minTime = sdfTemp.parse(minRegTime);
//			Date maxTime = sdfTemp.parse(maxRegTime);
//			Long days = (maxTime.getTime() - minTime.getTime()) / 24*3600;
//			allInterval = days / Integer.valueOf(periods[1]);
//		}
		for (int i = 1; i <= Integer.parseInt(periods[1]); i++){
			String startTime = "";
			if (periods[0].equals("week")){
				//week是7次*1天
				startTime = sdf.format(new Date(currentDate.getTime() - i* 24*3600*1000));
			}
			if (periods[0].equals("month")){
				//month是15次*2天
				startTime = sdf.format(new Date(currentDate.getTime() - i*2* 24*3600*1000));
			}
			if (periods[0].equals("year")){
				//year是12次*30天
				Calendar cal = Calendar.getInstance();
				cal.setTime(currentDate);
				cal.add(Calendar.MONTH, -1*i);
				startTime = sdf.format(cal.getTime());
			}
			if (periods[0].equals("ALL")){
				//ALL是整个周期，10次
				startTime = sdf.format(new Date(currentDate.getTime() - i*allInterval* 24*3600*1000));

			}

			//总用户
			List<UserEntity> users = getUsersBetweenTimeAndRoleName(startTime, nowTime, "ALL");
			int count = users.size();
			counts.add(count);
			//管理员
			List<UserEntity> adminUsers = getUsersBetweenTimeAndRoleName(startTime, nowTime, "管理员");
			int adminCount = adminUsers.size();
			adminCounts.add(adminCount);
			//政府
			List<UserEntity> govUsers = getUsersBetweenTimeAndRoleName(startTime, nowTime, "政府");
			int govCount = govUsers.size();
			govCounts.add(govCount);
			//事业单位
			List<UserEntity> insUsers = getUsersBetweenTimeAndRoleName(startTime, nowTime, "事业单位");
			int insCount = insUsers.size();
			insCounts.add(insCount);
			//企业
			List<UserEntity> comUsers = getUsersBetweenTimeAndRoleName(startTime, nowTime, "企业");
			int comCount = comUsers.size();
			comCounts.add(comCount);
			//个人
			List<UserEntity> perUsers = getUsersBetweenTimeAndRoleName(startTime, nowTime, "个人");
			int perCount = perUsers.size();
			perCounts.add(perCount);

			nowTime = startTime;
		}
		allRoleCount.put("ALL", counts);
		allRoleCount.put("管理员", adminCounts);
		allRoleCount.put("政府", govCounts);
		allRoleCount.put("事业单位", insCounts);
		allRoleCount.put("企业", comCounts);
		allRoleCount.put("个人", perCounts);
		return allRoleCount;
	}

	/**
	 * @wlw
	 * 获得不同用户角色下的用户数
	 */
	public Long getUserCountByRoleName(String roleName){
		String hql = "SELECT COUNT(u.userId) FROM UserEntity u WHERE u.role.roleName = '" + roleName +"'";
		if (roleName.equals("ALL")){
			hql = "SELECT COUNT(u.userId) FROM UserEntity u";
		}
		Query query = getEntityDao().getSession().createQuery(hql);
		if (query.list().size() != 0){
			return (Long) query.list().get(0);
		}else {
			return Long.valueOf(0);
		}
	}
	/**
	 * 获得数据库中某个字段信息
	 */
	public List<String> getUserColumns(String column){
		String hql = "SELECT u."+ column +" FROM UserEntity u";
		List<String> users = getEntityDao().getSession().createQuery(hql).list();
		return users;
	}

}
