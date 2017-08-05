package com.team.solventa.dao;

import java.util.List;

import com.team.solventa.model.User;

public interface UserDao extends Dao<User>  {
	public List<User> findAll();

}
