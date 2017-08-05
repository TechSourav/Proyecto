package com.team.solventa.web.config;

import java.util.EnumSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import ch.qos.logback.classic.ViewStatusMessagesServlet;
import ch.qos.logback.classic.selector.servlet.LoggerContextFilter;

public abstract class AbstractAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(final ServletContext container) {
		// Create the 'root' Spring application context
		final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();

		rootContext.register(this.getRootContextClasses());

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));

		// Create the dispatcher servlet's Spring application context
		final AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(this.getWebContextClass());

		final ServletRegistration.Dynamic viewStatusServlet = container.addServlet("ViewStatusMessages", ViewStatusMessagesServlet.class);

		viewStatusServlet.addMapping("/lbClassicStatus");

		final FilterRegistration.Dynamic loggerContextFilter = container.addFilter("loggerContextFilter", LoggerContextFilter.class);

		loggerContextFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "*.html");

		try {
			final InitialContext initContext = new InitialContext();
			initContext.addToEnvironment("logback/context-name", "elliott");
		} catch (final NamingException e) {
			e.printStackTrace();
		}

		// Register and map the dispatcher servlet
		final ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("*.html");

	}

	protected abstract Class<?> getWebContextClass();

	protected abstract Class<?>[] getRootContextClasses();

}
