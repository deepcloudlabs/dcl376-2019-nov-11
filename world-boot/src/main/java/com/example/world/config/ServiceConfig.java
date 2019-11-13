package com.example.world.config;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@ConditionalOnExpression(
		"'${os.name}' == 'Windows 10' and ${app.active}"
)
public class ServiceConfig {
	@PostConstruct
	public void init() {
			System.err.println("ServiceConfig is running...");
	}
}
