package com.Schulprojekt.helloprojekt.GUILogik;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.Schulprojekt.helloprojekt.SimpleChatActivity;

public class GetMessage implements Runnable{
	
	SimpleChatActivity chat;
	boolean running;
	String line;
	
	public GetMessage(SimpleChatActivity chat){
		this.chat = chat;
	}
	
	public void run(){
		while(running){
			 try
			    {
			        InputStream instream = openFileInput("/"+chat.chatPartner.getAccountID()); 
			        if (instream != null)
			        {
//			            InputStreamReader inputreader = new InputStreamReader(instream); 
			            InputStreamReader inputreader = new InputStreamReader(instream); 
			            BufferedReader buffreader = new BufferedReader(inputreader);
			            
			            while ((line = buffreader.readLine()) != null)
			            	instream.
			            	instream.toString().substring(instream.toString().lastIndexOf("gelesen")+6);
			            	line.lastIndexOf("gelesen");
			            	
		                    if(line.substring(0,10).equals("#123454321#")){
		                    	
		                    	if(line.substring(11,21).equalsIgnoreCase("hangman123:")){
			        }
			        
			    }
			 catch (Exception e) 
	            {
	                e.printStackTrace();
	            }
		}
	}

}
