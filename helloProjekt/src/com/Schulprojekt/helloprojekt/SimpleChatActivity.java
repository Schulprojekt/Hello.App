package com.Schulprojekt.helloprojekt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.Schulprojekt.helloprojekt.GUILogik.GetMessage;
import com.Schulprojekt.helloprojekt.GUILogik.Message;
import com.Schulprojekt.helloprojekt.GUILogik.MessageServices;
import com.Schulprojekt.helloprojekt.GUILogik.User;
import com.Schulprojekt.helloprojekt.GUILogik.UserServices;
import com.Schulprojekt.helloprojekt.Spiele.MainHangmanActivity;
import com.Schulprojekt.helloprojekt.Spiele.TicTacToeActivity;

public class SimpleChatActivity extends Activity {
	

	public ImageView imageViewGame;
	public ImageView imgMessageSend;
	public EditText txtChat;
	public LinearLayout layoutMessages;
	private User loggedUser;
	public User chatPartner;
	public String line;
	public Thread t;
	private final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_chat);
		Bundle bundle = getIntent().getExtras();
		loggedUser = new User();
		chatPartner = new User();
		loggedUser.setAccountID(bundle.getInt("loggedAccountId"));
		loggedUser.setAccountName(bundle.getString("loggedAccountName"));							
		loggedUser.setAccountPicture(bundle.getByteArray("loggedPicture"));							//Füllen des Bundles mit Key und dem dazugehörigen Wert
		chatPartner.setAccountID(bundle.getInt("partnerAccountId"));
		chatPartner.setAlias(bundle.getString("partnerAliasName"));
		chatPartner.setAccountName(bundle.getString("partnerAccountName"));
		chatPartner.setAccountPicture(bundle.getByteArray("partnerPicture"));			
		imageViewGame = (ImageView) findViewById(R.id.imageViewGameChat);
		imageViewGame.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(SimpleChatActivity.this,
		        		SelectGameActivity.class);													
				Bundle b = new Bundle();															//Erstellen eines Bundles
				b.putInt("loggedUser", loggedUser.getAccountID());
				b.putInt("chatPartner", chatPartner.getAccountID());
				i.putExtras(b);																		//Bundle ins Intent hinzufügen
				startActivity(i);
			}
		});
		
		imgMessageSend = (ImageView) findViewById(R.id.imageViewMessageSend);
		imgMessageSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendMessage();
			}
		});
		txtChat = (EditText) findViewById(R.id.editTextChat);
		layoutMessages = (LinearLayout) findViewById(R.id.layoutMessages);
		
		receiveMessage();
		t = new Thread(new GetMessage(SimpleChatActivity.this, loggedUser.getAccountID(), chatPartner.getAccountID()));
		t.start();
	}
		
//	ScrollView scv = (ScrollView) findViewById(R.id.scrollViewChat);
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat, menu);
		if(chatPartner.getAccountPicture() != null){
		byte[] bArray = chatPartner.getAccountPicture();
		Bitmap bitmap = BitmapFactory.decodeByteArray(bArray , 0, bArray.length);
		BitmapDrawable d = new BitmapDrawable(bitmap);
		getActionBar().setIcon(d);
		}
		else{
			Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.dummycontact);
			BitmapDrawable bmp = new BitmapDrawable(icon);
			getActionBar().setIcon(bmp);
		}
		getActionBar().setTitle(chatPartner.getAlias());
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch(item.getItemId())
		{
		case R.id.action_example:
				try{			       
			        	Bundle b = new Bundle();
			        	Intent i = new Intent(SimpleChatActivity.this, UserProfileActivity.class);
			        	b.putString("accountName", loggedUser.getAccountName());
			        	b.putInt("userId", loggedUser.getAccountID());
			        	b.putString("SearchedAccountName", chatPartner.getAccountName());
			        	b.putString("SearchedAliasName", chatPartner.getAlias());
			        	b.putInt("SearchedId", chatPartner.getAccountID());
//			        	b.putString("userId", "");
//			        	b.putString("accountName", chatPartner.getAccountName());
//			        	b.putString("aliasName", chatPartner.getAlias());
//			        	b.putString("password", "test");
//			        	b.putBoolean("accountState", true);
////			        	b.putString("expierencePoints", "...");
//			        	b.putByteArray("picture", new byte[]{50});
			        	
						i.putExtras(b);
						startActivity(i);
//						finish();
//						System.exit(0);
//			        }     
				}catch (Exception e){
					e.printStackTrace();
				}
				break;
		case R.id.chat_AppExit:
			finish();
			break;
		default:
			;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void sendMessage(){
		try{
	
			Message message = new Message(chatPartner.getAccountID(), loggedUser.getAccountID(), txtChat.getText().toString());
			MessageServices.createMessage(message);
	        
    		try {
    			FileOutputStream fileout = new FileOutputStream("/data/data/com.Schulprojekt.helloprojekt/files/"+chatPartner.getAccountID()+".txt", true);
    			OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
    			outputWriter.write(txtChat.getText().toString()+"\n");
    			outputWriter.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		} 
	        
	        TextView sentText = new TextView(getApplicationContext());
			sentText.setText(txtChat.getText());
			sentText.setBackgroundColor(Color.WHITE);
			sentText.setTextColor(Color.BLACK);
			sentText.setGravity(Gravity.RIGHT);
			layoutMessages.addView(sentText);
		}catch (Exception e){
			e.printStackTrace();
		}
		txtChat.setText("");
	}
	
	public void receiveMessage(){
		
	    try
	    {
//	        InputStream instream = openFileInput(Integer.toString(chatPartner.getAccountID())); 
	        File file = new File("/data/data/com.Schulprojekt.helloprojekt/files/"+chatPartner.getAccountID()+".txt");
//	        if (instream != null)
//	        {
//	            InputStreamReader inputreader = new InputStreamReader(instream); 
//	            BufferedReader buffreader = new BufferedReader(inputreader); 
	            BufferedReader br = new BufferedReader(new FileReader(file));
	            try
	            {
	                while ((line = br.readLine()) != null){
	                	
	                    if(line.startsWith("#123454321#")){
	                    	String gama = line.substring(11);
	                    	if(gama.startsWith("hangman123:") || gama.startsWith("tictactoe123:")){
	                    		final String blabla = line.substring(22);
	                    		new AlertDialog.Builder(context).setTitle("Spielanfage")								//Erstellen eines Popup-Fensters
	            				.setMessage("Sie wurden zu einem Spiel Hangman herausgefordert")
	            				.setPositiveButton("Akzeptieren", new DialogInterface.OnClickListener() {					//Bei Klick auf Ja:
	            					public void onClick(DialogInterface dialog, int which) {
	            					
	            						Intent i = new Intent(SimpleChatActivity.this, MainHangmanActivity.class);
	            						Bundle b = new Bundle();
	            						b.putString("wort", blabla.toUpperCase());
	            						i.putExtras(b);
	            						startActivity(i);
	            				
	            					}
	            				}).setNegativeButton("Ablehnen", new DialogInterface.OnClickListener() {				//Bei Klich auf Nein:
	            					public void onClick(DialogInterface dialog, int which) {
	            																									//Keine Funktion ausführen
	            					}
	            				}).setIcon(R.drawable.ic_launcher).show();
	                    	}else{
	                    		TextView receivedText = new TextView(getApplicationContext());
		                		receivedText.setText(line.substring(11));
		                		receivedText.setBackgroundColor(Color.GRAY);
		                		receivedText.setTextColor(Color.WHITE);
		                		receivedText.setGravity(Gravity.LEFT);
		                		layoutMessages.addView(receivedText);
	                    	}
	                    }else if(line.startsWith("#00000000#")){}
	                    else{
	                		TextView receivedText = new TextView(getApplicationContext());
	                		receivedText.setText(line);
	                		receivedText.setBackgroundColor(Color.WHITE);
	                		receivedText.setTextColor(Color.BLACK);
	                		receivedText.setGravity(Gravity.RIGHT);
	                		layoutMessages.addView(receivedText);
	                    }
	                }
	            }catch (Exception e) 
	            {
	                e.printStackTrace();
	            }
//	         }
	    }
	    catch (Exception e) 
	    {
	        String error="";
	        error=e.getMessage();
	    }
	}

	public void refresh(final String message){
//		try {
////			t.sleep(8000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 if(message.startsWith("#123454321#")){
         	
			String gama = message.substring(11);
         	if(gama.startsWith("hangman123:")){
         		
         		new AlertDialog.Builder(context).setTitle("Spielanfage")								//Erstellen eines Popup-Fensters
				.setMessage("Sie wurden zu einem Spiel Hangman herausgefordert")
				.setPositiveButton("Akzeptieren", new DialogInterface.OnClickListener() {					//Bei Klick auf Ja:
					public void onClick(DialogInterface dialog, int which) {
					
						Intent i = new Intent(SimpleChatActivity.this, MainHangmanActivity.class);
						Bundle b = new Bundle();
						b.putString("wort", message.substring(22));
						i.putExtras(b);
						startActivity(i);
				
					}
				}).setNegativeButton("Ablehnen", new DialogInterface.OnClickListener() {				//Bei Klich auf Nein:
					public void onClick(DialogInterface dialog, int which) {
																									//Keine Funktion ausführen
					}
				}).setIcon(R.drawable.ic_launcher).show();
         		
         	}else if(gama.startsWith("tictactoe123:")){
         		
         		new AlertDialog.Builder(context).setTitle("Spielanfrage")									//Erstellen eines Popup-Fensters
				.setMessage("Sie wurden zu einem Spiel TicTacToe herausgefordert")
				.setPositiveButton("Akzeptieren", new DialogInterface.OnClickListener() {					//Bei Klick auf Ja:
					public void onClick(DialogInterface dialog, int which) {
					
						Intent i = new Intent(SimpleChatActivity.this, TicTacToeActivity.class);
						Bundle b = new Bundle();
						b.putString("loggedUser", loggedUser.getAccountName());
						b.putString("chatPartner", chatPartner.getAccountName());
						i.putExtras(b);
						startActivity(i);
				
					}
				}).setNegativeButton("Ablehnen", new DialogInterface.OnClickListener() {				//Bei Klich auf Nein:
					public void onClick(DialogInterface dialog, int which) {
																									//Keine Funktion ausführen
					}
				}).setIcon(R.drawable.ic_launcher).show();
         		
         	}else if(gama.startsWith("#00000000#")){}
         	else{
        		TextView receivedText = new TextView(getApplicationContext());
        		receivedText.setText(message.substring(11));
        		receivedText.setBackgroundColor(Color.GRAY);
        		receivedText.setTextColor(Color.WHITE);
        		receivedText.setGravity(Gravity.LEFT);
        		layoutMessages.addView(receivedText);
         		
         	}
		 }
	}}
		 
