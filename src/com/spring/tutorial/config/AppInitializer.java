package com.spring.tutorial.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// Root config classes will have common beans which the servlet classes can
	// utilize
	/*
	 * Spring's ApplicationContext provides the capability of loading multiple
	 * (hierarchical) contexts, allowing each to be focused on one particular
	 * layer, such as the web layer of an application or middle-tier services.
	 * One of the canonical examples of using hierarchical ApplicationContext is
	 * when we have multiple DispatcherServlets in a web application and we're
	 * gonna share some of the common beans such as datasources between them.
	 * This way, we can define a root ApplicationContext that contain all the
	 * common beans and multiple WebApplicationContexts that inherit the common
	 * beans from the root context. In the Web MVC framework, each
	 * DispatcherServlet has its own WebApplicationContext, which inherits all
	 * the beans already defined in the root WebApplicationContext. These
	 * inherited beans can be overridden in the servlet-specific scope, and you
	 * can define new scope-specific beans local to a given Servlet instance
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { AppConfig.class, PersistenceConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { WebMvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

}
