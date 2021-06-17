package com.ak.poc.rest.client.jdk.jersey;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

public class RestApiClient {

	private static final String serviceURL = "http://192.168.137.1:15000/core-transfers-service/core/transfers/beneficiaries/09291000111223";

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(serviceURL);
		//WebTarget webTarget = client.target("http://192.168.137.1:15000/core-transfers-service");
		//WebTarget employeeWebTarget = webTarget.path("core/transfers/beneficiaries/09291000111223");
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		System.out.println(response.getStatus());
		System.out.println(response.readEntity(String.class));
		client.close();

	}

}
