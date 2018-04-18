package com.spring.tutorial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.spring.tutorial.interceptor.RequestProcessingTimeInterceptor;

/*http://www.baeldung.com/spring-dispatcherservlet*/

@Configuration
@EnableWebMvc
@ComponentScan({ "com.spring.tutorial" })
public class WebMvcConfig implements WebMvcConfigurer {
	/*
	 * A ViewResolver determines both what kind of views are served by the
	 * dispatcher and from where they are served.
	 */
	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/*
	 * setting the prefix, which sets the default URL path to find the set views
	 * within the default view type which is set via the suffix setting a view
	 * class on the resolver which allows technologies like JSTL or Tiles to be
	 * associated with the rendered views
	 */

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestProcessingTimeInterceptor());
	}
}
