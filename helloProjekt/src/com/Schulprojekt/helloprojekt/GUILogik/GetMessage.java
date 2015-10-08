package com.Schulprojekt.helloprojekt.GUILogik;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.Activity;

import com.Schulprojekt.helloprojekt.SimpleChatActivity;

public class GetMessage extends Activity implements Runnable{
	
	SimpleChatActivity chat;
	boolean running;
	String line;
	ArrayList<String> course;
	ArrayList<String> newMessages;
	ArrayList<Message> messages;
	int i = 0;
	int mark = 0;
	int partnerId = 0;
	int accountID;
	
	public GetMessage(SimpleChatActivity chat, int accountId, int partnerId){
		this.chat = chat;
		this.partnerId = partnerId;
		this.accountID = accountId;
	}
	
	public void run(){
		while(running){
			
			MessageLoop();
			
			 try
			    {
				 Thread.sleep(8000);
				 InputStream instream = openFileInput("/data/data/com.Schulprojekt.helloprojekt/"+partnerId+".txt"); 
			        if (instream != null)
			        {
			        	InputStreamReader inputreader = new InputStreamReader(instream); 
			            BufferedReader buffreader = new BufferedReader(inputreader);
			            
			            while ((line = buffreader.readLine()) != null)
			            {
			            if(line.substring(0,10).equals("#123454321#")){
	                    	course.add(line);
	                    	i = i+1;
			            }else if(line.equals("#00000000#")){
			            	mark = i;
			            }
			            }
			            for(int r = mark; r < course.size() ; r++){
			            	chat.refresh(course.get(r));
			            }
			            try {
			            	// TODO Warum nicht schon oben die partnerId ? - true = append to file, false = overwrite
			    			FileOutputStream fileout = new FileOutputStream("/data/data/com.Schulprojekt.helloprojekt/files/" + partnerId+".txt", true);
			    			OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
			    			outputWriter.write("#00000000#");
			    			outputWriter.close();
			    		} catch (Exception e) {
			    			e.printStackTrace();
			    		}
			        }
			    }    
			 catch (Exception e) 
	            {
	                e.printStackTrace();
	            }
		}
	}
		
		public void MessageLoop(){
			messages = MessageServices.getMessages(accountID);
			if (messages != null){
	        log(messages);
			}
		}
		
		public void log(ArrayList<Message> message){
        	
        	for (Message m : message) {
        		try {
        			FileOutputStream fileout = new FileOutputStream("/data/data/com.Schulprojekt.helloprojekt/files/"+m.getSender()+".txt");
        			OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
        			outputWriter.write("#123454321#"+m.getMessageText());
        			outputWriter.close();
        			
        		} catch (Exception e) {
        			e.printStackTrace();
        		}	
			}
	}
}