package com.ak.poc.rest.client.asynchttpclient;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.AsyncHttpClientConfig;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;

public class RestApiClient {

	private static final String serviceURL = "http://192.168.137.1:15000/core-transfers-service/core/transfers/beneficiaries/09291000111223";

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		AsyncHttpClientConfig cfg = Dsl.config().setReadTimeout(20).build();
		AsyncHttpClient asyncHttpClient = Dsl.asyncHttpClient(cfg);
		BoundRequestBuilder getRequest = asyncHttpClient.prepareGet(serviceURL);
		Future<Response> responseFuture = getRequest.execute();
		Response res = responseFuture.get();
		System.out.println(res.getResponseBody());
		asyncHttpClient.close();
	}
}
