package com.micro.centralconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class CentralConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralConfigApplication.class, args);
	}

}
