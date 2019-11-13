package com.example.world.config;

import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 *
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@Configuration
@ComponentScan(basePackages = { "com.example.world.repository.jdbctemplate", "com.example.world.service" })
@EnableTransactionManagement
@EnableCaching
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
	public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public SimpleCacheManager cacheManager() {
		SimpleCacheManager manager = new SimpleCacheManager();
		Collection<Cache> caches = new ArrayList<>();
		caches.add(new ConcurrentMapCache("countries"));
		manager.setCaches(caches);
		return manager;
	}
}
