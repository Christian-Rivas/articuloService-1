package com.rivas.infraccionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class RivasExamen {

	public static void main(String[] args) {
		SpringApplication.run(RivasExamen.class, args);
	}

}
