package com.team.solventa.manager;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.team.solventa.model.IdEntity;


public interface Manager<T extends IdEntity> {

	public void flush();
	public void evict(T entity) throws DataAccessException;
	public void evictAll(Collection<T> entities) throws DataAccessException;
	public void refresh(T entity) throws DataAccessException;
	public Integer save(T entity);
	public T get(Integer id);
	public void update(T entity);
	public void delete(T entity);
	public void delete(Integer id);
	public void saveAll(Collection<T> entities);
	public void updateAll(Collection<T> entities);
	public void deleteAll(Collection<T> entities);
	public void clear();

}
