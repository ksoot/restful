package com.ak.poc.rest.client.okhttp;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RestClient {
	
	private static final String serviceURL = "http://192.168.137.1:15000/core-transfers-service/core/transfers/beneficiaries/09291000111223";
	
	public static void main(String[] args) throws IOException {
		System.out.println("OkHttp");
		
		
		
		
		OkHttpClient client = 	new OkHttpClient.Builder()
			    .readTimeout(16, TimeUnit.SECONDS).build();
		
		Request request = new Request.Builder().url(serviceURL).get().build();
		System.out.println("----");
		Response response = client.newCall(request).execute();
		System.out.println("----");
		String res = response.body().string();
		System.out.println(res);
		
		
	}
}
