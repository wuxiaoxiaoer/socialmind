package com.sicdlib.service;

import com.sicdlib.entity.IndicatorValueEntity;
import com.sicdlib.entity.UserEntity;
import com.sicdlib.util.DBUtil;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class IndicatorValueEntityService extends DefaultEntityManager<IndicatorValueEntity> {

    /** 用户相关事件 */
    public List<Object> relatedEvents(UserEntity user){

        String hql = "from Object o where ";
        return null;
    }

    /**
     * 获得某个舆情对象的各种属性统计
     * @param objectId          舆情对象ID    ID
     * @param indexName         指标名称      热度/可信度/敏感度/情感度...
     * @param dimensionName     维度名称      性别/年龄/职业...
     * @param dimensionValue    维度值        男/女/18...
     * @param startTime         开始时间      2017-11-07 15:11:00
     * @param endTime           结束时间      2017-11-07 15:12:00
     * @return
     */
    public List<IndicatorValueEntity> getObjectIndicators(String objectId, String indexName, String dimensionName,
                                  String dimensionValue, String startTime, String endTime){
        String hql = "select i from IndicatorValueEntity i "
                +"where objectId ='"+objectId+"' and indexName like "+"'%"+indexName+"'"
                + "and dimensionName like"+"'%"+dimensionName+"' and dimensionValue like '%"+dimensionValue +"'"
                + "and startTime > '"+startTime+"' and endTime < '" + endTime + "'";
        List<IndicatorValueEntity> indicators = getEntityDao().find(hql);
        return indicators;
    }

    public List getObjectArea(String objectId){

        try {
            List list = new ArrayList();
            Connection conn = new DBUtil().GetConnection();
            Statement statement = conn.createStatement();
            String sql = "select i.dimensionValue,SUM(i.indicatorValue) from indicator_value i where i.objectID ='" +objectId+"' AND i.indexName=\"地域\" GROUP BY i.dimensionValue";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                Map map = new HashMap();
                map.put("name",rs.getString(1));
                map.put("value",rs.getInt(2));
                list.add(map);
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
