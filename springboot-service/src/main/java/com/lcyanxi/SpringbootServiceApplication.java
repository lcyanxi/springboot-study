package com.lcyanxi;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class SpringbootServiceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringbootServiceApplication.class, args);
		Thread.sleep(Long.MAX_VALUE);
	}

}
