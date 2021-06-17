package com.ak.poc.rest.client.apache.httpclient;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.Header;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class RestApiClient {

	private static final String serviceURL = "http://192.168.137.1:15000/core-transfers-service/core/transfers/beneficiaries/09291000111223";

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException, ParseException {
		
		CloseableHttpClient client = HttpClients.createDefault();
	    HttpGet httpget = new HttpGet(serviceURL);


	    CloseableHttpResponse httpResponse = client.execute(httpget);
	      HttpEntity entity = httpResponse.getEntity();

	      System.out.println("----------------------------------------");
	      System.out.println(httpResponse.getCode());
	      Header[] headers = httpResponse.getHeaders();
	      for (int i = 0; i < headers.length; i++) {
	        System.out.println(headers[i]);
	      }
	      System.out.println("----------------------------------------");

	      if (entity != null) {
	        System.out.println(EntityUtils.toString(entity));
	      }


	    client.close();
		
	}
}
