package com.spacetravel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApiConfig {

	/*
	 * @Bean public RestTemplate restTemplate() { return new RestTemplate(); }
	 */
	
	@Bean
	public RestClient restClient(RestClient.Builder builder) {
		return builder.build();
	}
}
