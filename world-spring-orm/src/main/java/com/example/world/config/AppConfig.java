package com.example.world.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 *
 *  @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@Configuration
@ComponentScan(basePackages = { "com.example.world.repository.orm", "com.example.world.service" })
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class AppConfig {
	@Value("${jdbcUrl}")
	private String jdbcUrl;
	@Value("${user}")
	private String username;
	@Value("${password}")
	private String password;
	@Value("${minIdle}")
	private int minIdle;
	@Value("${maxPoolSize}")
	private int maxPoolSize;

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(username);
		config.setPassword(password);
		config.setMinimumIdle(minIdle);
		config.setMaximumPoolSize(maxPoolSize);
		return new HikariDataSource(config);
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource datasource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(datasource);
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(hibernateJpaVendorAdapter);
		entityManagerFactory.setPackagesToScan("com.example.world.entity");
		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.show_sql", "true");
		jpaProperties.put("hibernate.format_sql", "true");
		//jpaProperties.put("hibernate.use_sql_comments", "true");
		//jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		jpaProperties.put("use_sql_comments", "true");
		entityManagerFactory.setJpaProperties(jpaProperties);
		return entityManagerFactory;
	}
}
