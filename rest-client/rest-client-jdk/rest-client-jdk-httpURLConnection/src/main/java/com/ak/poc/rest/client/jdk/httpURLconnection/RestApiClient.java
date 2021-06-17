package com.ak.poc.rest.client.jdk.httpURLconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RestApiClient {

	private static final String serviceURL = "http://192.168.137.1:15000/core-transfers-service/core/transfers/beneficiaries/09291000111223";

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

		URL url = new URL(serviceURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("accept", "application/json");
		connection.setConnectTimeout(20000);
		connection.setReadTimeout(2 * 60000);

		String response = responseBuilder(connection);
		System.out.println(response);
		connection.disconnect();

	}

	private static String responseBuilder(HttpURLConnection con) throws IOException {
		StringBuilder fullResponseBuilder = new StringBuilder();

		// read status and message
		fullResponseBuilder.append("status : ").append(con.getResponseCode()).append("\n").append(" message : ").append(con.getResponseMessage()).append("\n");

		// read headers
		fullResponseBuilder.append("headers:").append("\n");
		con.getHeaderFields().entrySet().stream().filter(entry -> entry.getKey() != null).forEach(entry -> {
			fullResponseBuilder.append(entry.getKey()).append(": ");
			List headerValues = entry.getValue();
			Iterator it = headerValues.iterator();
			if (it.hasNext()) {
				fullResponseBuilder.append(it.next());
				while (it.hasNext()) {
					fullResponseBuilder.append(", ").append(it.next());
				}
			}
			fullResponseBuilder.append("\n");
		});

		// read response content
		BufferedReader in = null;
		String inputLine;
		int status = con.getResponseCode();
		if (status > 299) {
			in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		} else {
			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		}
		
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		fullResponseBuilder.append("body :").append("\n");
		fullResponseBuilder.append(content);
		in.close();
		return fullResponseBuilder.toString();
	}
}
