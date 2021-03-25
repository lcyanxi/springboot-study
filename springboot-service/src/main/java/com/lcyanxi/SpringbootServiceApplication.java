package com.lcyanxi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootServiceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringbootServiceApplication.class, args);
		Thread.sleep(Long.MAX_VALUE);
	}

}
