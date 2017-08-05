package com.team.solventa.web.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.team.solventa.util.LocalDateDeserializer;
import com.team.solventa.util.LocalDateSerializer;

@ComponentScan("com.arcadia.config.utils")
@EnableWebMvc
public abstract class AbstractWebContext extends WebMvcConfigurerAdapter {
	@Autowired private LocalDateDeserializer localDateDeserializer;
	@Autowired private LocalDateSerializer localDateSerializer;

	@Bean(name = "objectMapper")
	public Jackson2ObjectMapperFactoryBean objectMapper() {
		final Jackson2ObjectMapperFactoryBean factory = new Jackson2ObjectMapperFactoryBean();
		factory.setFeaturesToEnable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		factory.setFeaturesToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		factory.setFeaturesToEnable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);

		final Map<Class<?>, JsonDeserializer<?>> deserializers = new HashMap<>();
		deserializers.put(LocalDate.class, localDateDeserializer);
		// TODO deserializers.put(LocalDateTime.class, localDateTimeDeserializer);
		factory.setDeserializersByType(deserializers);

		final Map<Class<?>, JsonSerializer<?>> serializers = new HashMap<>();

		serializers.put(LocalDate.class, localDateSerializer);
		// TODO serializers.put(LocalDateTime.class, localDateTimeSerializer);

		factory.setSerializersByType(serializers);
		return factory;
	}

	@Bean
	public ViewResolver viewResolver() {
		final UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
		urlBasedViewResolver.setViewClass(JstlView.class);
		urlBasedViewResolver.setPrefix("/WEB-INF/jsp/");
		urlBasedViewResolver.setSuffix(".jsp");

		return urlBasedViewResolver;
	}

	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		final RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();

		final List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper().getObject());

		messageConverters.add(mappingJackson2HttpMessageConverter);

		adapter.setMessageConverters(messageConverters);

		return adapter;
	}

	public abstract WebBindingInitializer bindingInitializer();

}
