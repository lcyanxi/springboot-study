package com.lcyanxi;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableDubbo
public class SpringbootServiceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringbootServiceApplication.class, args);
		Thread.sleep(Long.MAX_VALUE);
	}

}
