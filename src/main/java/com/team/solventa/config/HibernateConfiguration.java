package com.team.solventa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration extends AbstractHibernateConfiguration{

	@Override
	public String getPackagesToScan() {
		return "com.team.solventa.model";
	}

}
