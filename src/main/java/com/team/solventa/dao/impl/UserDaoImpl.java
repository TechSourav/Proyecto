package com.team.solventa.dao.impl;

import java.util.List;

import org.hibernate.Query;
//import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Repository;

import com.team.solventa.dao.UserDao;
import com.team.solventa.model.User;

@Repository("userDao")
public class UserDaoImpl extends DaoImpl<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}
	/**
	 *  Query to obtain the list of users
	 */
	@Override
	public List<User> findAll(){
		final String hql = "select distinct user from " + User.class.getName() + " user "
				+ "left join fetch user.applications order by user.id";
		final Query query = getSession().createQuery(hql);
		List<User> list = query.list();
		return list;
	}
	/**
	 * Returns a user with a list of applications and document
	 */

	 @Override
	 public User get(Integer id) {

	  final String applications = "select user from " + User.class.getName() + " user "
	    + "left join fetch user.applications apps" + " where user.id = :id  order by apps.name";

	  final Query query = getSession().createQuery(applications);
	  query.setInteger("id", id);
	  User user = (User) query.uniqueResult();
	  return user;
	 }
	 
//	 @Override
//	 public CustomUser getForLogin(Object username) {
//		 final String getUser = "from " + User.class.getName() + " e where e.userName = :username";
//		 final Query queryGetUser = getSession().createQuery(getUser);
//		 queryGetUser.setParameter("username", username);
//		 User user = (User) queryGetUser.uniqueResult();
//		 String hql="";
//		 
//		 if(user.getAdmin()){
//			 hql = "select e.id as id, e.userName as username, e.password as password, '" + Roles.ROLE_ADMIN.name()
//			    + "' as role from " + User.class.getName() + " e where e.userName = :username";
//		 }else{
//			 hql = "select e.id as id, e.userName as username, e.password as password, '" + Roles.ROLE_NORMAL.name()
//			    + "' as role from " + User.class.getName() + " e where e.userName = :username";
//		 }
//		 
//		  final Query query = getSession().createQuery(hql);
//
//		  query.setParameter("username", username);
//	  
//		 query.setResultTransformer(new AliasToBeanResultTransformer(CustomUser.class));
//
//	  return (CustomUser) query.uniqueResult();
//
//	 }
}
