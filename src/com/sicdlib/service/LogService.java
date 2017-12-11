package com.sicdlib.service;

import com.sicdlib.entity.LogEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zcx on 2017/11/9.
 */
@Service
@Transactional
public class LogService extends DefaultEntityManager<LogEntity> {

    /**
     * 获得最近一次的用户注销日志
     * 根据用户角色以及method方法(login)查找用户日志，并根据时间倒序形式显示，获得倒序的第一条即为最新的日志
     * @param
     * @return
     */
    public List<LogEntity> getLastLogByuserId(String userId){
        String hql = "SELECT l FROM LogEntity l WHERE l.userEntity.userId = '"+ userId + "' AND l.method='login' ORDER BY l.logTime DESC";
        List<LogEntity> logs = getEntityDao().find(hql);
        return logs;
    }
    /**
     * 获得大于某时间节点以上的日志信息
     */
    public List<LogEntity> getLogsByTime(String time){
        String hql = "SELECT l FROM LogEntity l WHERE l.logTime > '" + time +"' ORDER BY l.logTime DESC";
        List<LogEntity> logs = getEntityDao().find(hql);
        return logs;
    }
    /**
     * 获得大于某时间节点以上的日志信息
     */
    public Long getLogsCount(String time){
        String hql = "SELECT COUNT(l.logId) FROM LogEntity l WHERE l.logTime > '" + time +"'";
        if (time != null){
            if (time.equals("ALL")){
                hql = "SELECT COUNT(l.logId) FROM LogEntity l";
            }
            Query query = getEntityDao().getSession().createQuery(hql);
            if (query.list().size() != 0){
                return (Long)query.list().get(0);
            }else {
                return Long.valueOf(0);
            }
        } else {
            return Long.valueOf(0);
        }
    }

}
