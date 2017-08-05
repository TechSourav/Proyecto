package com.team.solventa.manager.impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.team.solventa.dao.Dao;
import com.team.solventa.manager.Manager;
import com.team.solventa.model.IdEntity;

@Transactional
public class ManagerImpl<T extends IdEntity> implements Manager<T> {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected Dao<T> dao;

	public ManagerImpl() {
		super();
	}

	public Dao<T> getDao() {
		return this.dao;
	}

	public void setDao(Dao<T> dao) {
		this.dao = dao;
	}

	public ManagerImpl(Dao<T> dao) {
		super();
		this.dao = dao;
	}

	@Override
	public void flush() {
		getDao().flush();
	}

	@Override
	public void evict(T entity) throws DataAccessException {
		getDao().evict(entity);

	}

	@Override
	public void evictAll(Collection<T> entities) throws DataAccessException {

		for (T entity : entities) {
			getDao().evict(entity);
		}
	}

	@Override
	public void refresh(T entity) throws DataAccessException {

		getDao().refresh(entity);

	}

	@Override
	public Integer save(T entity) {
		return getDao().save(entity);
	}

	@Override
	public T get(Integer id) {
		return getDao().get(id);
	}

	@Override
	public void update(T entity) {
		getDao().update(entity);

	}

	@Override
	public void delete(T entity) {
		getDao().delete(entity);

	}

	@Override
	public void delete(Integer id) {
		getDao().delete(id);

	}

	@Override
	public void saveAll(Collection<T> entities) {

		for (T entity : entities) {
			getDao().save(entity);
		}
	}

	@Override
	public void updateAll(Collection<T> entities) {
		for (T entity : entities) {
			getDao().update(entity);
		}
	}

	@Override
	public void deleteAll(Collection<T> entities) {

		for (T entity : entities) {
			getDao().delete(entity);
		}
	}

	@Override
	public void clear() {
		getDao().clear();
	}
}
