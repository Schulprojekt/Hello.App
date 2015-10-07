package com.Schulprojekt.helloprojekt.GUILogik;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class RelationshipServices {

private static Gson gson = new GsonBuilder().create();
	
	/**
	 * @param args
	 */
	public static ArrayList<User> getRelationship(int accountId) {
		ArrayList<User> contacts = new ArrayList<User>();
		String param = "{\"accountId\":\"" + accountId + "\"}";

		HttpResponse response = WebServerUtils.post("/Relationship/GetRelationship", param);
		
		JsonReader reader;
		try {
			reader = new JsonReader(new InputStreamReader(response.getEntity().getContent()));
			contacts = gson.fromJson(reader, new TypeToken<ArrayList<User>>(){}.getType());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contacts;
	}
	
	public static void createRelationship(Relationship relationship){
		String param = "{\"friendsId\":\"" + relationship.getFriendsId() + "\", \"userId\":\"" + relationship.getUserId() + "\"}";
		HttpResponse response = WebServerUtils.post("/Relationship/createRelationship", param);
	}

	public static void deleteRelationship(Relationship relationship){
		String param = "{\"friendsId\":\"" + relationship.getFriendsId() + "\", \"userId\":\"" + relationship.getUserId() + "\"}";
		HttpResponse response = WebServerUtils.post("/Relationship/DeleteRelationship", param);
	}
}
