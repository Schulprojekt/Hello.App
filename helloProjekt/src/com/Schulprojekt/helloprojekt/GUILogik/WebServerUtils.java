package com.Schulprojekt.helloprojekt.GUILogik;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class WebServerUtils {

	private static String webserviceAdress = "http://10.18.208.31:8080/hello-webservice/api";
	static HttpClient httpClient = new DefaultHttpClient();

	public static HttpResponse post(String url, String param) {
		HttpResponse response = null;
		try {
			HttpPost request = new HttpPost(webserviceAdress + url);
			StringEntity params = new StringEntity(param);
			request.addHeader("content-type", "application/json;charset=UTF-8");
			request.setEntity(params);
			response = httpClient.execute(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public static HttpResponse get(String url) {
		HttpResponse response = null;
		try {
			HttpGet request = new HttpGet(webserviceAdress + url);
			request.addHeader("content-type", "application/json;charset=UTF-8");
			response = httpClient.execute(request);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public static boolean validateResponse(HttpResponse response) {
		if (response.getStatusLine().toString().equals("HTTP/1.1 200 OK")) {
			return true;
		} else {
			return false;
		}
	}
}
