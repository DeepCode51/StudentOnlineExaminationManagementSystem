package com.sanqing.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import com.sanqing.util.Page;

/**
 * 通用dao接口类
 * 
 * @author shaolei
 * 
 * @param <T>
 * @param <PK>
 */
public interface BaseDao<T, PK extends Serializable> {

	/**
	 * 获取session
	 * 
	 * @return
	 */
	public Session getSession();

	/**
	 * 保存新增对象
	 * 
	 * @param entity
	 */
	public void save(T entity);

	/**
	 * 数据库中不存在保存。存在就更新对象
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(T entity);

	/**
	 * 保存新增对象。返回主键
	 * 
	 * @param entity
	 * @return
	 */
	public Serializable saveReturnId(T entity);

	/**
	 * 更新对象,存在就更新。不存在就报错
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * 更新对象：1.表中存在记录就更新 2.不存在就插入一条新纪录,并且操作的对象不会纳入session的管理 saveOrUpdate 操作的对象纳入session管理
	 * 
	 * @param entity
	 */
	public void merge(T entity);

	/**
	 * 删除对象
	 * 
	 * @param entity
	 */
	public void delete(final T entity);

	/**
	 * 按照id删除对象
	 * 
	 * @param id
	 */
	public void delete(final PK id);

	/**
	 * 根据主键查询
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public T getByKey(PK id);

	/**
	 * 查询全部记录
	 * 
	 * @return
	 */
	public List<T> getAll();

	/**
	 * 查询全部记录,支持排序
	 * 
	 * @param orderBy
	 * @param isAsc
	 * @return
	 */
	public List<T> getAll(String orderBy, boolean isAsc);

	/**
	 * 根据Criterion条件创建Criteria
	 * 
	 * @param criterions
	 * @return
	 */
	public Criteria createCriteria(final Criterion... criterions);

	/**
	 * 按Criteria查询对象列表
	 * 
	 * @param criterions
	 * @return
	 */
	public List<T> find(final Criterion... criterions);

	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List<T> findBy(final String propertyName, final Object value);

	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public T findUniqueBy(final String propertyName, final Object value);

	/**
	 * 
	 * @param ids
	 * @return
	 */
	public List<T> findByIds(List<PK> ids);

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public <X> List<X> find(final String hql, final Object... values);

	/**
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public <X> List<X> find(final String hql, final Map<String, ?> values);

	/**
	 * 按HQL查询唯一对象
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public <X> X findUnique(final String hql, final Object... values);

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public <X> X findUnique(final String hql, final Map<String, ?> values);

	/**
	 * 执行HQL进行批量修改/删除操作
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public int batchExecute(final String hql, final Object... values);

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public int batchExecute(final String hql, final Map<String, ?> values);

	/**
	 * 按Criteria查询唯一对象.
	 * 
	 * @param criterions
	 * @return
	 */
	public T findUnique(final Criterion... criterions);

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public Query createQuery(final String hql, final Object... values);

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * @param queryString
	 * @param values
	 * @return
	 */
	public Query createQuery(final String hql, final Map<String, ?> values);

	public void flush();

	/**
	 * 获取对象的主键名
	 * 
	 * @return
	 */
	public String getIdName();

	public long countHqlResult(final String hql, final Object... values);

	/**
	 * 执行count查询获得本次Hql查询所能获得的对象总数.
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	public long countHqlResult(final String hql, final Map<String, ?> values);

	/**
	 * 执行count查询获得本次Criteria查询所能获得的对象总数
	 * 
	 * @param c
	 * @return
	 */
	public long countCriteriaResult(final Criteria c);

	/**
	 * 初始化对象. 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化. 只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性. 如需初始化关联属性,可实现新的函数,执行: Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合. Hibernate.initialize(user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
	 */
	public void initEntity(T entity);

	public void initEntity(List<T> entityList);

	public List<T> findByPage(Page page, String hql, Map<String, ?> values);

}
