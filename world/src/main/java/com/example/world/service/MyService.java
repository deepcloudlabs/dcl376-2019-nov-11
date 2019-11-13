package com.example.world.service;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

	@Autowired
	private Set<String> continents;

	@PostConstruct
	public void init() {
		continents.forEach(System.err::println);
	}
}
