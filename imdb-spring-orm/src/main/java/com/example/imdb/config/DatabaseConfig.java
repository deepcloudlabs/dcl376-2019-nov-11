package com.example.imdb.config;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "com.example.imdb" })
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
public class DatabaseConfig {
	@Value("${db.url}")
	private String url;
	@Value("${db.initialSize}")
	private int initialSize;
	@Value("${db.maxConnectionSize}")
	private int maxConnectionSize;
	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;

	@Bean
	public DataSource dataSource() {
		BasicDataSource bds = new BasicDataSource();
		bds.setUrl(url);
		bds.setUsername(username);
		bds.setPassword(password);
		bds.setInitialSize(initialSize);
		bds.setMaxActive(maxConnectionSize);
		return bds;
	}

	@Bean
	public JpaTransactionManager jpaTransactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource ds) {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setDataSource(ds);
		HibernateJpaVendorAdapter hjva = new HibernateJpaVendorAdapter();
		hjva.setShowSql(true);
		lcemfb.setJpaVendorAdapter(hjva);
		lcemfb.setPackagesToScan("com.example.imdb.entity");
		return lcemfb;
	}
}
