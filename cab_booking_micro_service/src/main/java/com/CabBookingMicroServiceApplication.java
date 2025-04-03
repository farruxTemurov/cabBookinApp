package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com")
@EntityScan(basePackages = "com.bean")
@EnableJpaRepositories(basePackages = "com.repository")
@EnableDiscoveryClient
public class CabBookingMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabBookingMicroServiceApplication.class, args);
		System.err.println("Booking micro service's up on port 8181");
	}

	@Bean
	@LoadBalanced // Enables Eureka to resolve service names automatically
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
