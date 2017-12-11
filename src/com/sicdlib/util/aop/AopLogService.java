package com.sicdlib.util.aop;

import com.sicdlib.entity.CleanLogManagerEntity;
import edu.xjtsoft.base.service.DefaultEntityManager;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class AopLogService extends DefaultEntityManager<CleanLogManagerEntity> {
    public Boolean inputLog(CleanLogManagerEntity cleanLogManagerEntity){
        Session session=getEntityDao().getSession();
        session.save(cleanLogManagerEntity);
        return true;
    }
    public List<CleanLogManagerEntity> getLog(){
        String hql = "from CleanLogManagerEntity cleanLog";
        List<CleanLogManagerEntity> cleanLogList = getEntityDao().find(hql);
        return cleanLogList;
    }
}
