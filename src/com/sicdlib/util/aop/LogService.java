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


@Service("LogService")
@Transactional
public class LogService extends DefaultEntityManager<CleanLogManagerEntity> {
    public Boolean inputLog(CleanLogManagerEntity cleanLogManagerEntity){
        SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
        Session session=sessionFactory.openSession();
        Transaction trans=session.beginTransaction();
        session.save(cleanLogManagerEntity);
        trans.commit();
        return true;
    }
}
