package com.spring.tutorial.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "")
public class PersistenceConfig {

	@Autowired
	private Environment env;

	@Value("${init-db:false}")
	private String initDatabase;

	@Bean
	public PlatformTransactionManager transactionManager() {
		EntityManagerFactory factory = entityManagerFactory().getObject();
		return new JpaTransactionManager(factory);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(Boolean.TRUE);
		vendorAdapter.setShowSql(Boolean.TRUE);

		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.spring.tutorial.model", "com.spring.tutorial.dao");

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		factory.setJpaProperties(jpaProperties);

		factory.afterPropertiesSet();
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		return factory;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	@Bean
	public DataSource dataSource() {
		// Refer:
		// https://mytechrepo.wordpress.com/2015/02/17/drivermanagerdatasource-vs-basicdatasource/
		// BasicDataSource performs better when handling multiple incoming DB
		// requests compared to DriverManagerDataSource
		BasicDataSource dataSource = new BasicDataSource();
		// DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
		
	}

	/*
	 * When we develop a modern web application, we have lot of REST services in
	 * a single page. Usually we face slow page loading in Local Development
	 * Environment.
	 * 
	 * But, The same page loads little faster in PRO because of its high
	 * configurations (For example its RAM size and such other things). In order
	 * to improve the
	 * 
	 * performance we have to change certain things in our local. One of the
	 * thing we found is Datasource connection pool configuration. When we hit
	 * multiple REST service at a
	 * 
	 * time,all are handled as different request. If each request is a DB call,
	 * DriverManagerDataSource cannot perform well.
	 * 
	 * Since it can handle your multiple DB requests one by one, to load the
	 * entire page it will take comparitively much time in local env. In
	 * spring’s documentation itself its given that
	 * 
	 * This class is not an actual connection pool; it does not actually pool
	 * Connections. It just serves as simple replacement for a full-blown
	 * connection pool, implementing the same standard interface, but creating
	 * new Connections on every call
	 * 
	 * So we have to go for some other datasource which gives you Connection
	 * pooling mechanism.
	 * 
	 * One of such a datasource which gives you a “real” connection pool outside
	 * of a JEE container is BasicDataSource
	 */

	/*
	 * @Bean public DataSourceInitializer dataSourceInitializer(DataSource
	 * dataSource) { DataSourceInitializer dataSourceInitializer = new
	 * DataSourceInitializer(); dataSourceInitializer.setDataSource(dataSource);
	 * ResourceDatabasePopulator databasePopulator = new
	 * ResourceDatabasePopulator(); databasePopulator.addScript(new
	 * ClassPathResource("db.sql"));
	 * dataSourceInitializer.setDatabasePopulator(databasePopulator);
	 * dataSourceInitializer.setEnabled(Boolean.parseBoolean(initDatabase));
	 * return dataSourceInitializer; }
	 */
}
