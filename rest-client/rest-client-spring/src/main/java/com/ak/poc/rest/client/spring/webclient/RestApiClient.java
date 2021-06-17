package com.ak.poc.rest.client.spring.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@SpringBootApplication
public class RestApiClient {

	private static final String serviceURL = "http://192.168.137.1:15000/core-transfers-service/core/transfers/beneficiaries/09291000111223";
	
	
	public static void main(String[] args) {
		SpringApplication.run(RestApiClient.class, args);
	
		WebClient webClient = WebClient.create("http://192.168.137.1:15000/core-transfers-service");
		 
		Mono<String> r = webClient.get()
		        .uri("/core/transfers/beneficiaries/09291000111223")
		        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		        .retrieve()
		        .bodyToMono(String.class);
		System.out.println("---------------------------");
		r.subscribe(user -> System.out.println(user));
		System.out.println("---------------------------");
		
	}
}
