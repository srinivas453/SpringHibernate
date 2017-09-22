package org.mainlogic.spring.hibernate.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScans({
	@ComponentScan("org.mainlogic.spring.hibernate.dao"),
	@ComponentScan("org.mainlogic.spring.hibernate.entity"),
	@ComponentScan("org.mainlogic.spring.hibernate.service")
	})
@EnableTransactionManagement
@PropertySource("classpath:hibernate.properties")
public class JpaConfig {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource getDataSource() {

		PGSimpleDataSource dataSource = new PGSimpleDataSource();
		dataSource.setUrl(environment.getProperty("hibernate.connection.url"));
		dataSource.setUser(environment.getProperty("hibernate.connection.username"));
		dataSource.setPassword(environment.getProperty("hibernate.connection.password"));

		return dataSource;
	}

	@Bean
	public LocalEntityManagerFactoryBean getEntityManagerFactory() {

		LocalEntityManagerFactoryBean emFactory = new LocalEntityManagerFactoryBean();
		emFactory.setJpaVendorAdapter(getJpaVendorAdapter());
		emFactory.setJpaProperties(getProperties());
		//emFactory.setPersistenceUnitName("JPAExampleUnit");

		return emFactory;
	}
	
	@Bean
	public HibernateJpaVendorAdapter getJpaVendorAdapter() {
		
		return new HibernateJpaVendorAdapter();
	}

	private Properties getProperties() {

		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
		properties.setProperty("hibernate.cache.use_second_level_cache", environment.getProperty("hibernate.cache.use_second_level_cache"));
		properties.setProperty("hibernate.current_session_context_class", environment.getProperty("hibernate.current_session_context_class"));

		return properties;
	}

	@Bean
	public JpaTransactionManager getTransactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(getEntityManagerFactory().getObject());

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {

		return new PersistenceExceptionTranslationPostProcessor();
	}
}
