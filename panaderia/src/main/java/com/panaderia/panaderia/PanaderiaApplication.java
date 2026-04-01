package com.panaderia.panaderia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories()
public class PanaderiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PanaderiaApplication.class, args);
	}

}
