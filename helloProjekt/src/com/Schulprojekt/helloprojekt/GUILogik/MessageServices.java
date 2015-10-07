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

public class MessageServices {
	
private static Gson gson = new GsonBuilder().create();
	
	/**
	 * @param args
	 */
	public static ArrayList<Message> getMessages(int accountId) {
		ArrayList<Message> messages = new ArrayList<Message>();
		String param = "{\"accountID\":\"" + accountId + "\"}";

		HttpResponse response = WebServerUtils.post("/message/GetMessages", param);
		
		JsonReader reader;
		try {
			reader = new JsonReader(new InputStreamReader(response.getEntity().getContent()));
			messages = gson.fromJson(reader, new TypeToken<List<Message>>(){}.getType());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return messages;
	}
	
	public static void createMessage(Message message){																			//Deklaration
		String param = "{\"receiver\":\"" + message.getReceiver() + "\", \"sender\":\"" + message.getSender() + "\", \"messageText\":\"" + message.getMessageText() + "\"}";
		HttpResponse response = WebServerUtils.post("/message/CreateMessage", param);
	}
}
