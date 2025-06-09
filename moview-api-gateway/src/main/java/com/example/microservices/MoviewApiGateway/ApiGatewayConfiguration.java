package com.example.microservices.MoviewApiGateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		
		return builder.routes()
				.route(p -> p
						.path("/get")
						.filters(f -> f.addRequestHeader("MyHeader", "MyURI")
								.addRequestParameter("Param", "myValue"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/movie/**")
						.uri("lb://movie-service"))
				.route(p -> p.path("/review/**")
						.uri("lb://review-service"))
				.route(p -> p.path("/user/**")
						.uri("lb://user-registration-service"))
				.build();
		
	}
	
}
