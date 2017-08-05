package com.team.solventa.dao;

import org.springframework.dao.DataAccessException;

import com.team.solventa.model.IdEntity;

/**
 * Interface that represents the general DAO able to perform CRUD Common on BD
 *
 * @author Arcadia
 *
 * @param <T>
 *            : Domain Object
 */
public interface Dao<T extends IdEntity> {
	
	public void evict(T entity) throws DataAccessException;
	
	public Integer save(T entity);
	 	
	public void update(T entity);

	public void delete(T entity);

	public void delete(Integer entity);
		
	public T get(Integer id);
	
	public void refresh(T Entity);

	public void flush();
	
	public Integer saveAndFlush(T entity);
	
	public void clear();
	

}
