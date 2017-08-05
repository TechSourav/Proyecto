package com.team.solventa.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.team.solventa.service.impl.MethodSecurityServiceImpl;

//import com.arcadia.versions.model.Role.Roles;
//import com.arcadia.versions.service.impl.MethodSecurityServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		//@formatter:off
		http.csrf().disable().authorizeRequests()
		.antMatchers("/index.html","/list.html").permitAll()
//		.antMatchers("/configuration.html").access("hasRole('"+ Roles.ROLE_ADMIN.name() +"')")
//		.antMatchers("/employeeCreateModify.html").access("hasAnyRole('"+ Roles.ROLE_ADMIN.name() +"','"+ Roles.ROLE_NORMAL.name() +"')")
		.and().formLogin().loginPage("/index.html").defaultSuccessUrl("/index.html", false).failureForwardUrl("/index.html?error=true")
		.and().logout().logoutUrl("/logout.html").permitAll().logoutSuccessUrl("/index.html")
		.and().exceptionHandling().accessDeniedPage("/accessDenied.html");
		//@formatter:on
	}

	@Bean
	public MethodSecurityServiceImpl methodSecurityService() {
		return new MethodSecurityServiceImpl();
	}
}
