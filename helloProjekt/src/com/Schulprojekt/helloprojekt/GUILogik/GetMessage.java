package com.Schulprojekt.helloprojekt.GUILogik;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.Activity;

import com.Schulprojekt.helloprojekt.SimpleChatActivity;
import com.Schulprojekt.helloprojekt.Spiele.TicTacToeActivity;

public class GetMessage extends Activity implements Runnable{
	
	SimpleChatActivity chat;
	boolean running;
	String line;
	ArrayList<String> course;
	ArrayList<String> newMessages;
	int i = 0;
	int mark = 0;
	int partnerId = 0;
	Thread t;
	
	public GetMessage(SimpleChatActivity chat, int accountId, int partnerId){
		this.chat = chat;
		this.partnerId = partnerId;
		t = new Thread(new MessageLoop(accountId));
	}
	
	public void run(){
		t.start();
		while(running){
			 try
			    {
				 Thread.sleep(4000);
				 t.stop();
				 InputStream instream = openFileInput("/"+chat.chatPartner.getAccountID()); 
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
			    			FileOutputStream fileout = new FileOutputStream("/" + partnerId);
			    			OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
			    			outputWriter.write("#00000000#");
			    			outputWriter.close();
			    		} catch (Exception e) {
			    			e.printStackTrace();
			    		}
			        }
			        t.start();
			    }    
			 catch (Exception e) 
	            {
	                e.printStackTrace();
	            }
		}
	}
}