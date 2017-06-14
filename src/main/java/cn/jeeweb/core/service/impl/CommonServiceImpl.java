package cn.jeeweb.core.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.jeeweb.core.dao.ICommonDao;
import cn.jeeweb.core.dao.support.OrderHelper;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.PageImpl;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.parse.CriteriaParse;
import cn.jeeweb.core.query.parse.QueryParse;
import cn.jeeweb.core.query.utils.QueryableConvertUtils;
import cn.jeeweb.core.service.ICommonService;
import cn.jeeweb.core.utils.ReflectionUtils;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
//@Service("commonService")
public class CommonServiceImpl<T extends Serializable> implements ICommonService<T> {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	ICommonDao commonDao;
	protected Class<T> entityClass;

	public CommonServiceImpl() {
		this.entityClass = ReflectionUtils.getSuperGenericType(getClass());
	}

	@Override
	public void save(T entity) {
		commonDao.save(entity);
	}

	@Override
	public void batchSave(List<T> entitys) {
		commonDao.batchSave(entitys);
	}

	@Override
	public void saveOrUpdate(T entity) {
		commonDao.saveOrUpdate(entity);
	}

	@Override
	public void update(T entity) {
		commonDao.update(entity);
	}
	

	@Override
	public void merge(T entity) {
		 commonDao.merge(entity);
	}
	
	@Override
	public T load(Serializable id) {
		return commonDao.load(id, entityClass);
	}
	@Override
	public T get(Serializable id) {
		return commonDao.get(id, entityClass);
	}

	@Override
	public T get(String propertyName, Object value) {
		return commonDao.get(propertyName, value, entityClass);
	}

	@Override
	public void delete(T entity) {
		commonDao.delete(entity);
	}

	@Override
	public void deleteById(Serializable id) {
		commonDao.deleteById(id, entityClass);
	}

	@Override
	public void batchDelete(List<T> entitys) {
		commonDao.batchDelete(entitys);
	}

	@Override
	public void batchDeleteById(List<?> ids) {
		for (Object id : ids) {
			commonDao.deleteById((Serializable) id, entityClass);
		}
	}

	@Override
	public void batchDeleteByProperty(String propertyName, Object value) {
		commonDao.batchDeleteByProperty(propertyName, value, entityClass);
	}

	@Override
	public int count() {
		return commonDao.count(entityClass);
	}

	@Override
	public List<T> list() {
		return commonDao.list(entityClass);
	}

	@Override
	public List<T> list(OrderHelper orderHelper) {
		return commonDao.list(entityClass, orderHelper);
	}

	@Override
	public List<T> list(int page, int rows, DetachedCriteria detachedCriteria) {
		return commonDao.list(page, rows, detachedCriteria);
	}

	@Override
	public Page<T> list(Pageable pageable, DetachedCriteria detachedCriteria) {
		Long total = commonDao.count(detachedCriteria);
		List<T> content = list(pageable.getPageNumber(), pageable.getPageSize(), detachedCriteria);
		return new PageImpl<T>(content, pageable, total);
	}

	@Override
	public Page<T> list(Queryable queryable, DetachedCriteria detachedCriteria) {
		QueryParse<DetachedCriteria> queryParse = new CriteriaParse();
		QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
		queryParse.parseCondition(detachedCriteria, queryable);
		queryParse.parseSort(detachedCriteria, queryable);
		Long total = commonDao.count(detachedCriteria);
		Pageable pageable = queryable.getPageable();
		List<T> content = list(pageable.getPageNumber() + 1, pageable.getPageSize(), detachedCriteria);
		return new PageImpl<T>(content, queryable.getPageable(), total);
	}

	@Override
	public List<T> listWithNoPage(Queryable queryable, DetachedCriteria detachedCriteria) {
		QueryParse<DetachedCriteria> queryParse = new CriteriaParse();
		QueryableConvertUtils.convertQueryValueToEntityValue(queryable, entityClass);
		queryParse.parseCondition(detachedCriteria, queryable);
		queryParse.parseSort(detachedCriteria, queryable);
		return commonDao.list(detachedCriteria);
	}

	@Override
	public int updateByHql(String hql, Object... params) {
		return commonDao.updateByHql(hql, params);
	}

	@Override
	public int count(Criterion... criterions) {
		return commonDao.count(entityClass, criterions);
	}

	@Override
	public List<T> listByCriterion(Criterion... criterions) {
		return commonDao.listByCriterion(entityClass, criterions);
	}

	@Override
	public List<T> listByCriterion(OrderHelper orderHelper, Criterion... criterions) {
		return commonDao.listByCriterion(entityClass, orderHelper, criterions);
	}

	@Override
	public List<T> listByCriterion(int page, int rows, Criterion... criterions) {
		return commonDao.listByCriterion(page, rows, entityClass, criterions);
	}

	@Override
	public List<T> listByCriterion(OrderHelper orderHelper, int page, int rows, Criterion... criterions) {
		return commonDao.listByCriterion(page, rows, entityClass, orderHelper, criterions);
	}
	
	@Override
	public Long countByHql(String hql, Object... params) {
		 return commonDao.countByHql(hql, params);
	}

	@Override
	public List<T> listByHql(String hql, Object... params) {
		return commonDao.listByHql(hql, params);
	}
	
	@Override
	public List<T> listByHql(String hql, int page, int rows, Object... params) {
	 	return commonDao.listByHql(hql, page, rows, params);
	}

	@Override
	public List<T> listByIndexAliasHql(String hql, Object... params) {
		return commonDao.listByIndexAliasHql(hql, params);
	}

	@Override
	public List<T> listByAliasHql(String hql, Map<String, Object> alias) {
		return commonDao.listByAliasHql(hql, alias);
	}

	@Override
	public List<Map<String, Object>> listMapByHql(String hql, Object... params) {
		return commonDao.listMapByHql(hql, params);
	}

	@Override
	public List<Map<String, Object>> listMapByIndexAliasHql(String hql, Object... params) {
		return commonDao.listMapByIndexAliasHql(hql, params);
	}

	@Override
	public List<Map<String, Object>> listMapByAliasHql(String hql, Map<String, Object> alias) {
		return commonDao.listMapByAliasHql(hql, alias);
	}

	@Override
	public List<T> list(String propertyName, Object value) {
		return commonDao.list(propertyName, value, entityClass);
	}

	

	

	


}