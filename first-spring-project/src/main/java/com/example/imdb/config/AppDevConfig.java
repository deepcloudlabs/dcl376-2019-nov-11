package com.example.imdb.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Configuration
@ComponentScan("com.example.imdb.service.business")
@PropertySource("classpath:database-dev.properties")
@Profile("dev")
public class AppDevConfig {
	@Value("${database.hostname}") // SpEL: Spring Expression Language
	private String hostName;
	@Value("${database.port}")
	private int port;

	@PostConstruct
	public void init() {
		System.err.println("AppDevConfig::init(): " + hostName + ":" + port);
	}
}
