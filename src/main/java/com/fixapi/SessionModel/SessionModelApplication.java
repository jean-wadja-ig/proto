package com.fixapi.SessionModel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
public class SessionModelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionModelApplication.class, args);
	}
}

