package com.sicdlib.service;

import com.sicdlib.entity.ProvinceEntity;
import com.sicdlib.util.DBUtil.DBUtil;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProvinceService extends DefaultEntityManager<ProvinceEntity> {
    public List<ProvinceEntity> getProvinces() {
        List<ProvinceEntity> provinceList = new ArrayList<ProvinceEntity>();
        String hql = "from ProvinceEntity province";
        provinceList = getEntityDao().find(hql);
        return provinceList;
    }

    //查询市所在的省份
    public String findProvinceOfCity(String cityName){
        try {
            String result = null;
            Connection conn = new DBUtil().GetConnection();
            String sql = "select p.name from city c,province p where c.name like '%"+cityName+"%' and c.provincecode=p.code";
            PreparedStatement psmt = conn.prepareStatement(sql);
            ResultSet rs = psmt.executeQuery(sql);
            while (rs.next()){
                result = rs.getString(1);
            }
            new DBUtil().closeConn(rs,psmt,conn);
            return result;
        }catch (Exception e){
        }
        return null;
    }
}
