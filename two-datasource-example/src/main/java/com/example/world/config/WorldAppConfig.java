package com.example.world.config;

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
@PropertySource("classpath:database-world.properties")
@ComponentScan(basePackages = { "com.example.world.repository", "com.example.world.service" })
@EnableTransactionManagement
public class WorldAppConfig {
	@Value("${world.jdbc.url}")
	private String url;
	@Value("${world.user}")
	private String username;
	@Value("${world.password}")
	private String password;
	@Value("${world.initialSize}")
	private int initialSize;
	@Value("${world.maxTotal}")
	private int maxTotal;

	@Bean("worldDataSource")
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setInitialSize(initialSize);
		ds.setMaxTotal(maxTotal);
		return ds;
	}

	@Bean("worldTransactionManager")
	public JpaTransactionManager jpaTransactionManager(
			@Qualifier("worldEntityManagerFactory") EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	@Bean("worldEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactor(@Qualifier("worldDataSource") DataSource ds) {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setPersistenceUnitName("worldPU");
		lcemfb.setDataSource(ds);
		HibernateJpaVendorAdapter hjva = new HibernateJpaVendorAdapter();
		hjva.setShowSql(true);
		lcemfb.setJpaVendorAdapter(hjva);
		lcemfb.setPackagesToScan("com.example.world.entity");
		return lcemfb;
	}
}
