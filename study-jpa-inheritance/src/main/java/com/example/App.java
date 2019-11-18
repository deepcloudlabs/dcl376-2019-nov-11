package com.example;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entity.Developer;
import com.example.entity.Manager;

/**
 *
 *  @author Binnur Kurt <binnur.kurt@gmail.com>
 */
@SpringBootApplication
public class App implements CommandLineRunner {
	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	@Transactional
	public void run(String... arg0) throws Exception {
		Developer jack = new Developer();
		jack.setFullName("Jack Bauer");
		jack.setIdentityNo("12345678910");
		jack.setSalary(100_000);
		jack.setLanguages(Arrays.asList("c++", "java", "kotlin"));
		employeeRepository.save(jack);
		Manager kate = new Manager();
		kate.setFullName("Jack Bauer");
		kate.setIdentityNo("12345678910");
		kate.setSalary(100_000);
		kate.setDepartment("sales");
		employeeRepository.save(kate);
	}
}
