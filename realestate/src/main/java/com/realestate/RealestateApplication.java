package com.realestate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RealestateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealestateApplication.class, args);

		System.err.println("PORT : localhost8080");
		System.err.println("Swagger documentation : "+"http://localhost:8080/swagger-ui/index.html#/");
	}

}
