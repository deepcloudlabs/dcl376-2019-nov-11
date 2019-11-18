package com.example.imdb.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@Configuration
@PropertySource("classpath:database-imdb.properties")
@ComponentScan(basePackages = { "com.example.imdb.repository" })
@EnableTransactionManagement
public class ImdbAppConfig {
	@Value("${imdb.jdbc.url}")
	private String url;
	@Value("${imdb.user}")
	private String username;
	@Value("${imdb.password}")
	private String password;
	@Value("${imdb.initialSize}")
	private int initialSize;
	@Value("${imdb.maxTotal}")
	private int maxTotal;

	@Bean("imdbDataSource")
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setInitialSize(initialSize);
		ds.setMaxTotal(maxTotal);
		return ds;
	}

	@Bean("imdbTransactionManager")
	public JpaTransactionManager jpaTransactionManager(
			@Qualifier("imdbEntityManagerFactory") EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Bean("imdbEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactor(@Qualifier("imdbDataSource") DataSource ds) {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setDataSource(ds);
		lcemfb.setPersistenceUnitName("imdbPU");
		HibernateJpaVendorAdapter hjva = new HibernateJpaVendorAdapter();
		hjva.setShowSql(true);
		lcemfb.setJpaVendorAdapter(hjva);
		lcemfb.setPackagesToScan("com.example.imdb.entity");
		return lcemfb;
	}
}
