package com.example.world.config;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnJava(value = JavaVersion.NINE)
public class Java11Config {
	@PostConstruct
	public void init() {
		System.err.println("Running Java11Config...");
	}
}
