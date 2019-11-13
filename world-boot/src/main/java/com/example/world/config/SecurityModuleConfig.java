package com.example.world.config;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnResource(resources = "classpath:logback.xml")
@ConditionalOnProperty(prefix = "app", name = "security", havingValue = "deneme2")
public class SecurityModuleConfig {
	@PostConstruct
	public void init() {
		System.err.println("Configuring SecurityModule...");
	}
}
