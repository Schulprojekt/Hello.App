package com.Schulprojekt.helloprojekt.GUILogik;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

    public class MessageLoop implements Runnable {
    	   	
    	private boolean running = true;
    	private int userId = 0;
    	public boolean state;
    	
    	public MessageLoop(int userId){
    		this.userId = userId;
    	}
    	
        public void run() {
//        	Gson gs = new Gson();
//			String JsonString;
			ArrayList<Message> messages = new ArrayList<Message>();
			
                while(running) {
                	try{
                		state = true;
//        			JsonString = gs.toJson(userId);
//        			DefaultHttpClient httpClient = new DefaultHttpClient();
//        			HttpPost request = new HttpPost(SERVICE_URI + "/CreateMessage");
//        			request.setHeader("Accept", "application/json");
//        	        request.setHeader("Content-type", "application/json");
//        	        StringEntity se = new StringEntity(JsonString);
//        	        request.setEntity(se);
//        	        HttpResponse response = httpClient.execute(request);
//        	        HttpEntity responseEntity = response.getEntity();
//        	        InputStream stream = responseEntity.getContent();
//        	        messages = gs.fromJson(stream.toString(), new TypeToken<List<Message>>(){}.getType());
        	        messages = MessageServices.getMessages(userId);
        	        log(messages);
        	        
                    }
                    catch (IllegalStateException e) {
        				e.printStackTrace();
        			}
                }
                state = false;
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
