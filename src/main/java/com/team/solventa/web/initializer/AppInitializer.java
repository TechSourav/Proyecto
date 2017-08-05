package com.team.solventa.web.initializer;

import com.team.solventa.config.HibernateConfiguration;
import com.team.solventa.config.RootConfiguration;
import com.team.solventa.web.config.AbstractAppInitializer;
import com.team.solventa.web.config.WebContext;

public class AppInitializer extends AbstractAppInitializer {

	/**
	 * Avisar clases de configuracion que se utiliza para cada proyecto
	 */
	@Override
	protected Class<?> getWebContextClass() {

		return WebContext.class;
	}

	@Override
	protected Class<?>[] getRootContextClasses() {
		return new Class[] { HibernateConfiguration.class, RootConfiguration.class };
	}

}
