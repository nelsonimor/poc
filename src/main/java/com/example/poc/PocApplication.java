package com.example.poc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PocApplication {
   static Logger logger = LoggerFactory.getLogger(PocApplication.class);

   public static void main(String[] args) {
	  try {
	      SpringApplication.run(PocApplication.class, args);
	} catch (Exception e) {
		e.printStackTrace();
	}

   }
}
