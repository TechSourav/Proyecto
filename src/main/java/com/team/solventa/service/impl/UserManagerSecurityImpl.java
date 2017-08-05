package com.team.solventa.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import com.arcadia.versions.model.CustomUser;
//import com.arcadia.versions.model.Role.Roles;
import com.team.solventa.dao.UserDao;
import com.team.solventa.service.UserManagerSecurity;

@Service
public class UserManagerSecurityImpl implements UserManagerSecurity, UserDetailsService {

	@Autowired private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) {
//		final CustomUser adminUser = (CustomUser) loadSuperAdmin(username);
//
//		final CustomUser user;
//
//		if (adminUser == null) {
//			user = userDao.getForLogin(username);
//
//			if (user == null) {
//				return null;
//			} else {
//				return user;
//			}
//		} else {
//
//			adminUser.setRole(Roles.ROLE_ADMIN.name());
//
//			return adminUser;
//		}
//	}

//	public UserDetails loadSuperAdmin(String username) {
//		String pathSuperadminProperties = System.getProperty("catalina.home");
//
//		pathSuperadminProperties += File.separator + "custom" + File.separator + "versions" + File.separator + "superadmin.properties";
//
//		final Properties properties = new Properties();
//
//		try {
//			properties.load(new FileInputStream(pathSuperadminProperties));
//		} catch (final IOException e) {
//			e.printStackTrace();
//		}
//
//		final String adminUserName = properties.getProperty("superadmin.username");
//		final String adminPassword = properties.getProperty("superadmin.password");
//
//		if (!adminUserName.equals(username)) {
//			return null;
//		}
//
//		return new CustomUser(adminUserName, adminPassword);
//	}
}
