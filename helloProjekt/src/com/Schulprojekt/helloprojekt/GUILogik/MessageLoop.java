package com.Schulprojekt.helloprojekt.GUILogik;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.graphics.AvoidXfermode.Mode;

import com.Schulprojekt.helloprojekt.ContactListActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

    public class MessageLoop implements Runnable {
    	   	
    	private final static String SERVICE_URI = "http://lt0.studio.entail.ca:8080/VehicleService.svc";
    	private boolean running = true;
    	private int userId = 0;
    	
    	public MessageLoop(int userId){
    		this.userId = userId;
    	}
    	
        public void run() {
        	Gson gs = new Gson();
			String JsonString;
			ArrayList<Message> messages = new ArrayList<Message>();

                while(running) {
                	try{
                    // Pause für 4 Sekunden
						Thread.sleep(4000);
	
        			JsonString = gs.toJson(userId);
        			DefaultHttpClient httpClient = new DefaultHttpClient();
        			HttpPost request = new HttpPost(SERVICE_URI + "/CreateMessage");
        			request.setHeader("Accept", "application/json");
        	        request.setHeader("Content-type", "application/json");
        	        StringEntity se = new StringEntity(JsonString);
        	        request.setEntity(se);
        	        HttpResponse response = httpClient.execute(request);
        	        HttpEntity responseEntity = response.getEntity();
        	        InputStream stream = responseEntity.getContent();
        	        messages = gs.fromJson(stream.toString(), new TypeToken<List<Message>>(){}.getType());
        	        
        	        log(messages);
        	        
                    }
                    catch (IllegalStateException e) {
        				e.printStackTrace();
        			} catch (IOException e) {
        				e.printStackTrace();
        			} catch (InterruptedException e) {
						e.printStackTrace();	
					}
                }
        }
        
        public void log(ArrayList<Message> message){
        	
        	for (Message m : message) {
        		try {
        			FileOutputStream fileout = new FileOutputStream("/"+m.getSender());
        			OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
        			outputWriter.write("#123454321#"+m.getMessageText());
        			outputWriter.close();
        		} catch (Exception e) {
        			e.printStackTrace();
        		}	
			}
        }
        
        public void terminate(){
        	running = false;
        }
    }
