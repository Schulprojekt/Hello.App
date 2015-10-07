package com.Schulprojekt.helloprojekt.GUILogik;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class UserServices {

	private static Gson gson = new GsonBuilder().create();
	
	/**
	 * @param args
	 */
	public static User getUserByAccountName(String accountName) {
		User user = null;
		String param = "{\"accountName\":\"" + accountName + "\"}";

		HttpResponse response = WebServerUtils.post("/user/GetUserByAccountName", param);
		
		JsonReader reader;
		try {
			reader = new JsonReader(new InputStreamReader(response.getEntity().getContent()));
			user = (User) gson.fromJson(reader, User.class);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	public static User createUser(String accountName, String password){
		User user = null;
		String param = "{\"accountName\":\"" + accountName + "\", \"password\":\"" + password + "\"}";
		
		HttpResponse response = WebServerUtils.post("/user/CreateUser", param);
		
		JsonReader reader;
		try {
			reader = new JsonReader(new InputStreamReader(response.getEntity().getContent()));
			user = (User) gson.fromJson(reader, User.class);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	public static void deleteUser(String accountName){
		String param = "{\"accountName\":\"" + accountName + "\"}";
		
		HttpResponse response = WebServerUtils.post("/user/DeleteUser", param);
	}
	
	public static User updateUser(String accountName, String newAlias){
		User user = null;
		
		user = getUserByAccountName(accountName);
		
		String param = "{\"accountID\":\"" + user.getAccountID() + "\", \"accountName\":\"" + user.getAccountName() + "\", \"alias\":\"" + newAlias + "\", \"password\":\"" + user.getPassword() + "\"}";
		
		HttpResponse response = WebServerUtils.post("/user/UpdateUser", param);
		
		JsonReader reader;
		try {
			reader = new JsonReader(new InputStreamReader(response.getEntity().getContent()));
			user = (User) gson.fromJson(reader, User.class);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

}
