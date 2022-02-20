package com.micro.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator locateRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r ->
						r.path("/api/posts/*")
						.uri("lb://post-service"))
//				.route(r ->
//						r.path("/api/posts/*")
//						.uri("lb://timeline-service"))
				.build();
	}

	@RestController
	class TestController {

		@GetMapping
		public String trace(String value) {
			return "trace test for value: " + value;
		}

	}

}

