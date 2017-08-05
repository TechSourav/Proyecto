package com.team.solventa.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.team.solventa.web.propertyeditor.VersionesBindingInitializer;

@Configuration
@ComponentScan(basePackages = "com.arcadia.versions.web.controller,com.arcadia.versions.util")

/**
 * @author Arcadia
 *
 */
public class WebContext extends AbstractWebContext {

	@Autowired
	private VersionesBindingInitializer versionesBindingInitializer;

	@Override
	@Bean
	public VersionesBindingInitializer bindingInitializer() {
		return this.versionesBindingInitializer;
	}

	// To insert files to the server
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}

}
