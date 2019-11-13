package com.example.world.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(value = DataSource.class)
public class DataSourceConfig {

	@Bean
	public DataSource dataSource() {
		System.err.println("Configuring data source...");
		return null;
	}
}
