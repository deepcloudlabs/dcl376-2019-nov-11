package com.example.world.config;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.world.dao.CountryDao;

@Configuration
@ComponentScan(basePackages = {
		"com.example.world.dao",
		"com.example.world.service"
}
,excludeFilters = {
	@Filter(type = FilterType.ANNOTATION,classes = {Service.class})
}
)
public class AppConfig {
	@Autowired private CountryDao countryDao;
	// SpEL: Spring Expression Language
	@Value("#{elma.getAllContinents()}")
	public Set<String> kitalar ;
	@Value("#{ 2 + 2}")
	private int x;
	@Value("${os.name}")
	private String osName;
	
	@PostConstruct
	public void init() {
		System.err.println("Kitalar: "+kitalar);
		System.err.println("x="+x);
		System.err.println("osName: "+osName);
	}
	@Bean("continents")
	@Scope("singleton")
	public Set<String> getContinents(){
		return countryDao.getAllContinents();
	}
}
