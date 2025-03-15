package com.koustav.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
/*@EnableEurekaClient
* Since Spring Boot 2.4, the @EnableEurekaClient annotation is no longer required.
* If you have the spring-cloud-starter-netflix-eureka-client dependency in your pom.xml,
* your application will automatically register itself with the Eureka server without needing this annotation.*/
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
