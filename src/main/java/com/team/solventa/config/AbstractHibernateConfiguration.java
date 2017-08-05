package com.team.solventa.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.dialect.PostgreSQL82Dialect;
//import org.postgresql.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@EnableTransactionManagement
public abstract class AbstractHibernateConfiguration {
	
	@Bean
	public DataSource dataSource() throws IOException {

		final Properties props = getCustomTestHibernateProperties();
		final StringBuilder string = new StringBuilder();

		string.append("jdbc:postgresql://").append(props.getProperty("database.host"))
		.append(":").append(props.getProperty("database.port")).append("/")
		.append(props.getProperty("database.name"));

		final DriverManagerDataSource toReturn = new DriverManagerDataSource(string.toString(), props.getProperty("database.username"),
				props.getProperty("database.password"));

		//toReturn.setDriverClassName(Driver.class.getName());

		return toReturn;
	}

	public Properties getCustomTestHibernateProperties() throws IOException {
		final ClassPathResource cpr = new ClassPathResource("hibernate.properties");
		final Properties properties = new Properties();

		properties.load(cpr.getInputStream());

		return properties;
	}

	@Bean
	public Properties dataBaseProperties() throws FileNotFoundException, IOException {
		return getCustomTestHibernateProperties();
	}

	@Bean(name = "sessionFactory", destroyMethod = "destroy")
	public LocalSessionFactoryBean sessionFactoryBean() throws FileNotFoundException, IOException, SQLException {
		final Properties prop = dataBaseProperties();

		final Properties props = new Properties();
		props.put("hibernate.dialect", PostgreSQL82Dialect.class.getName());
		props.put("hibernate.format_sql", StringUtils.isNotEmpty(prop.getProperty("format_sql")) ? prop.getProperty("format_sql") : "false");
		props.put("hibernate.connection.charSet", "UTF-8");
		props.put("hibernate.connection.useUnicode", "true");
		props.put("hibernate.show_sql", StringUtils.isNotEmpty(prop.getProperty("show_sql")) ? prop.getProperty("show_sql") : "false");
		props.put("hibernate.connection.characterEncoding", "UTF-8");
		props.put("hibernate.validator.autoregister_listeners", "false");

		final LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setPackagesToScan(getPackagesToScan());
		bean.setHibernateProperties(props);
		bean.setDataSource(dataSource());

		return bean;
	}
	
	public abstract String getPackagesToScan();
	

	@Bean(name = "transactionManager")
	public HibernateTransactionManager transactionManager() throws Exception {
		final HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactoryBean().getObject());

		return txManager;
	}
	
}
