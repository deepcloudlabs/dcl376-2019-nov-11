package com.example.world;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.world.config.AppConfig;
import com.example.world.dao.CountryDao;

public class App {
	public static void main(String[] args) {
		try (ConfigurableApplicationContext container = new AnnotationConfigApplicationContext(AppConfig.class);) {
			CountryDao countryDao = container.getBean(CountryDao.class);
			countryDao.findOne("TUR").ifPresent(System.out::println);
			countryDao.findAll(1, 10).forEach(System.out::println);
			countryDao.findAll(1, 10).forEach(country -> {
				country.setPopulation(country.getPopulation() + 1);
				countryDao.update(country);
			});
		}
	}
}
