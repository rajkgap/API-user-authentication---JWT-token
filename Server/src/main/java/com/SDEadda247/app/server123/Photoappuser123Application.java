package com.SDEadda247.app.server123;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Photoappuser123Application {

	public static void main(String[] args) {
		SpringApplication.run(Photoappuser123Application.class, args);
	}

}
