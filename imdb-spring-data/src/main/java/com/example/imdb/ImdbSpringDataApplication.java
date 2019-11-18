package com.example.imdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.imdb.repository.MovieRepository;

@SpringBootApplication
public class ImdbSpringDataApplication implements ApplicationRunner{

	public static void main(String[] args) {
		SpringApplication.run(ImdbSpringDataApplication.class, args);
	}
	
	@Autowired
	private MovieRepository movieRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		Movie movie = new Movie();
//		movie.setTitle("test movie");
//		movie.setYear(2019);
//		movie.setImdb("tt1234567");
//		movieRepo.save(movie);
		movieRepo.filminTuruneGoreAra("War").forEach(System.out::println);
		movieRepo.filminYilinaVeAdinaGoreAra(1970,1979,"the").forEach(System.out::println);
	}

}
