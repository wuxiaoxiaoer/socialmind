package edu.xjtsoft.base.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import edu.xjtsoft.base.orm.HibernateDao;
import edu.xjtsoft.base.orm.support.Page;
import edu.xjtsoft.base.orm.support.PropertyFilter;
import edu.xjtsoft.base.util.ReflectionUtil;

/**
 * 领域对象的业务管理类基类.
 * 
 * 提供了领域对象的简单CRUD方法.
 *
 * @param <T> 领域对象类型
 */
@Transactional(readOnly=true)
public abstract class EntityManager<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected Class<T> entityClass;

	/**
	 * 通过子类的范型定义取得领域对象类型Class.
	 */
	@SuppressWarnings("unchecked")
	public EntityManager() {
		this.entityClass = ReflectionUtil.getSuperClassGenricType(this.getClass());
	}

	/**
	 * 在子类实现的回调函数,为EntityManager提供默认CRUD操作所需的DAO.
	 */
	protected abstract HibernateDao<T> getEntityDao();

	// CRUD函数 //
	/**
	 * 根据Id查找
	 * */
	public T load(Serializable id) {
		return getEntityDao().get(id);
	}

	/**
	 * 实现分页查找
	 * */
	public Page<T> loadAll(Page<T> page) {
		return getEntityDao().getAll(page);
	}

	/**
	 * 查找全部
	 * */
	public List<T> loadAll() {
		return getEntityDao().getAll();
	}

	
	/**
	 * 根据条件查找，与分页有关
	 * */
	public Page<T> search(Page<T> page, PropertyFilter... filters) {
		return getEntityDao().findByFilters(page, filters);
	}
	/**
	 * 根据条件查找，多个属性用‘|’分开，表示或者关系
	 * */
	public List<T> search(PropertyFilter... filters) {
		return getEntityDao().findByFilters(filters);
	}

	/**
	 * 更新对象
	 * */
	@Transactional(readOnly=false)
	public void saveOrUpdate(T entity) {
		getEntityDao().saveOrUpdate(entity);
	}

	/**
	 * 根据id删除对象
	 * */
	@Transactional(readOnly=false)
	public void remove(Serializable id) {
		getEntityDao().delete(id);
	}
}
