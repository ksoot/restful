package com.ak.poc.rest.client.jdk.httpclient;

import java.net.Authenticator;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RestApiClient {

	private static final String serviceURL = "http://192.168.137.1:15000/core-transfers-service/core/transfers/beneficiaries/";

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		 HttpClient client = HttpClient.newBuilder()
			        .version(Version.HTTP_1_1)
			        .followRedirects(Redirect.NORMAL)
			        .connectTimeout(Duration.ofSeconds(20))
			        .build();
		
		 HttpRequest request = HttpRequest.newBuilder()
			        .uri(URI.create(serviceURL+"09291000111223"))
			        .timeout(Duration.ofMinutes(2))
			        .header("Content-Type", "application/json")
			        .GET()
			        .build();
		 
		
		
		CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, BodyHandlers.ofString());
		response.thenAccept(res -> System.out.println(res));
		System.out.println(response.get().body().toString());
		response.join();

	}
}
