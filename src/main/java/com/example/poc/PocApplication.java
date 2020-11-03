package com.example.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PocApplication {

	public static void main(String[] args) {
		System.out.println("PocApplication.main()");
		SpringApplication.run(PocApplication.class, args);
	}

}
