package edu.xjtsoft.base.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import edu.xjtsoft.base.orm.HibernateDao;

/**
 * 默认的领域对象业务管理类基类,提供默认的范型DAO成员变量.
 * 
 * @param <T> 领域对象类型
 */
public class DefaultEntityManager<T> extends EntityManager<T> {

	protected HibernateDao<T> entityDao;
	
	/**
	 * 实现回调函数,为EntityManager基类的CRUD操作提供DAO.
	 */
	@Override
	protected HibernateDao<T> getEntityDao() {
		return entityDao;
	}

	@Autowired
	protected void setSessionFactory(SessionFactory sessionFactory) {
		entityDao = new HibernateDao<T>(sessionFactory, entityClass);
	}
}
