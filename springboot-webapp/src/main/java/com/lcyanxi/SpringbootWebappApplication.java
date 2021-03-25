package com.lcyanxi;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:dubbo_config/springboot-manage-consumer.xml")
public class SpringbootWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebappApplication.class, args);
	}

}
