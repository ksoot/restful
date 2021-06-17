package com.ak.poc.rest.client.apache.cfx;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;

public class RestApiClient {

	private static final String serviceURL = "http://192.168.137.1:15000/core-transfers-service/core/transfers/beneficiaries/09291000111223";

	public static void main(String[] args) {
		System.out.println("Apache cfx");
		WebClient client = WebClient.create(serviceURL);
				
		client.accept(MediaType.APPLICATION_JSON_TYPE);
		String responseData = client.get(String.class);
		System.out.println(responseData);
	}
}
