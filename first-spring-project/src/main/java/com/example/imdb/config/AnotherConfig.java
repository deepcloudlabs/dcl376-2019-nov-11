package com.example.imdb.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.example.imdb.service.Calculator;
import com.example.imdb.service.CalculatorQualifier;
import com.example.imdb.service.CalculatorType;
import com.example.imdb.service.MovieService;
import com.example.imdb.service.SequenceService;
import com.example.imdb.service.business.CalculatorService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Configuration
public class AnotherConfig {
	@Autowired
	private List<Calculator> calculators;

	@Autowired
	private MovieService movieSrv1;

	@PostConstruct
	public void init() {
		calculators.stream().map(Object::getClass).map(Class::getSimpleName)
				.map(name -> "AnotherConfig::init(): ".concat(name)).forEach(System.err::println);
	}

	@Bean("simpleCalc") // <bean ....>
	@Scope("prototype")
	@Lazy
	@CalculatorQualifier(CalculatorType.SIMPLE)
	public Calculator calculator(SequenceService seqSrv, MovieService movieSrv2) {
		System.err.println("AnotherConfig::calculator(...) :" + seqSrv.nextId("test-sequence"));
		System.err.println("AnotherConfig::calculator(...) :" + movieSrv1.findMovieById(1));
		System.err.println("AnotherConfig::calculator(...) :" + movieSrv2.findMovieById(1));
		return new CalculatorService();
	}
}
