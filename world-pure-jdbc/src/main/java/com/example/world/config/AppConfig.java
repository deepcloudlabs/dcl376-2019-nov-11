package com.example.world.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = { "com.example.world.dao.jdbc" })
@PropertySource("classpath:database.properties")
public class AppConfig {
	@Value("${url}")
	private String url;
	@Value("${user}")
	private String user;
	@Value("${password}")
	private String password;
	@Value("${initialSize}")
	private int initialSize;
	@Value("${maxTotal}")
	private int maxTotal;

	@Bean // XML: <bean id="dataSource"></bean>
	public DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		ds.setInitialSize(initialSize);
		ds.setMaxTotal(maxTotal);
		return ds;
	}
}
