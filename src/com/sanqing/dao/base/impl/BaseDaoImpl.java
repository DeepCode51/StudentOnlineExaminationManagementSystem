package com.sanqing.dao.base.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.sanqing.dao.base.BaseDao;
import com.sanqing.util.Page;
import com.sanqing.util.ReflectionUtils;

@SuppressWarnings("unchecked")
@Repository("baseDao")
public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> entityClass;

	public BaseDaoImpl() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	public BaseDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().save(entity);
		logger.debug("save entity: {}", entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().saveOrUpdate(entity);
		logger.debug("saveOrUpdate entity: {}", entity);
	}

	@Override
	public Serializable saveReturnId(T entity) {
		Assert.notNull(entity, "entity不能为空");
		logger.debug("save entity: {}", entity);
		return getSession().save(entity).toString();
	}

	@Override
	public void update(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().update(entity);
		logger.debug("update entity: {}", entity);
	}

	@Override
	public void merge(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().merge(entity);
		logger.debug("merge entity: {}", entity);
	}

	@Override
	public void delete(T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
		logger.debug("delete entity: {}", entity);
	}

	@Override
	public void delete(PK id) {
		Assert.notNull(id, "id不能为空");
		delete(getByKey(id));
		logger.debug("delete id is {}", id);
	}

	@Override
	public T getByKey(PK id) {
		Assert.notNull(id, "id不能为空");
		return (T) getSession().get(entityClass, id);
	}

	@Override
	public List<T> getAll() {
		return find();
	}

	@Override
	public List<T> getAll(String orderBy, boolean isAsc) {
		Criteria c = createCriteria();
		if (isAsc) {
			c.addOrder(Order.asc(orderBy));
		} else {
			c.addOrder(Order.desc(orderBy));
		}
		return c.list();
	}

	@Override
	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	@Override
	public List<T> find(Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	@Override
	public List<T> findBy(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}

	@Override
	public T findUniqueBy(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}

	@Override
	public T findUnique(Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}

	@Override
	public List<T> findByIds(List<PK> ids) {
		return find(Restrictions.in(getIdName(), ids));
	}

	@Override
	public <X> List<X> find(String hql, Object... values) {
		return createQuery(hql, values).list();
	}

	@Override
	public <X> List<X> find(String hql, Map<String, ?> values) {
		return createQuery(hql, values).list();
	}

	@Override
	public <X> X findUnique(String hql, Object... values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	@Override
	public <X> X findUnique(String hql, Map<String, ?> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	@Override
	public int batchExecute(String hql, Object... values) {
		return createQuery(hql, values).executeUpdate();
	}

	@Override
	public int batchExecute(String hql, Map<String, ?> values) {
		return createQuery(hql, values).executeUpdate();
	}

	@Override
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql, "hql不能为空");
		Query query = getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	@Override
	public Query createQuery(String hql, Map<String, ?> values) {
		Assert.hasText(hql, "hql不能为空");
		Query query = getSession().createQuery(hql);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	@Override
	public void flush() {
		getSession().flush();
	}

	@Override
	public String getIdName() {
		ClassMetadata meta = sessionFactory.getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}

	@Override
	public long countHqlResult(String hql, Object... values) {
		String fromHql = hql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;

		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	@Override
	public long countHqlResult(String hql, Map<String, ?> values) {
		String fromHql = hql;
		// select子句与order by子句会影响count查询,进行简单的排除.
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;

		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	@Override
	public long countCriteriaResult(Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;

		// 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List<CriteriaImpl.OrderEntry>) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		// 执行Count查询
		// int totalCount = (Integer)
		// c.setProjection(Projections.rowCount()).uniqueResult();
		Long count = (Long) c.setProjection(Projections.rowCount()).uniqueResult();
		long totalCount = count == null ? 0 : count;

		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
		c.setProjection(projection);

		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		return totalCount;
	}

	@Override
	public void initEntity(T entity) {
		Hibernate.initialize(entity);
	}

	@Override
	public void initEntity(List<T> entityList) {
		for (T entity : entityList) {
			Hibernate.initialize(entity);
		}
	}

	@Override
	public List<T> findByPage(Page page, String hql, Map<String, ?> values) {
		Query query = createQuery(hql, values);
		query.setMaxResults(page.getEveryPage());// 设置查询记录数
		query.setFirstResult(page.getBeginIndex());// 设置查询记录起始位置
		List<T> list = query.list();
		return list;
	}

}
