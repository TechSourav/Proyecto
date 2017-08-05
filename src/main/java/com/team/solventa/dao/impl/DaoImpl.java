package com.team.solventa.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.team.solventa.dao.Dao;
import com.team.solventa.model.IdEntity;

/**
 * DAO class that implements the interface , to perform operations on the
 * database with data
 *
 * @author Jhonny (Pirate Roberts)
 * 
 * @param <T>
 */
@Transactional
public abstract class DaoImpl<T extends IdEntity> extends HibernateDaoSupport implements Dao<T> {

	private final Class<T> entityClass;

	public DaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public Session getSession() {
		return super.getSessionFactory().getCurrentSession();
	}

	@Override
	protected HibernateTemplate createHibernateTemplate(SessionFactory sessionFactory) {
		return super.createHibernateTemplate(sessionFactory);
	}

	@Override
	public Integer save(final T entity) {
		
		getHibernateTemplate().saveOrUpdate(entity);
		return entity.getId();
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public void delete(final T entity) {
		getHibernateTemplate().delete(entity);
		getHibernateTemplate().flush();
	}

	@Override
	public void delete(final Integer id) {
		final T entity = this.get(id);
		getSession().delete(entity);
	}

	@Override
	public T get(Integer id) {
		final String hql = "select e from " + entityClass.getName() + " e where e.id = :id";

		final Query query = getSession().createQuery(hql);

		query.setParameter("id", id);

		// join
		// select dos/tres campos

		return (T) query.uniqueResult();
	}
	
	@Override
	public void refresh(T entity) {
		getSession().refresh(entity);
	}

	@Override
	public void flush() {
		getSession().flush();
	}
	
	@Override
	public Integer saveAndFlush(T entity) {
		
		Integer id = save(entity);
		flush();

		return id;
	}

	@Override
	public void clear() {
		getSession().clear();
	}
	
	@Autowired
	public void init(SessionFactory factory) {
		setSessionFactory(factory);
	}

	@Override
	public void evict(T entity) throws DataAccessException {
		getSession().evict(entity);
	}
}
