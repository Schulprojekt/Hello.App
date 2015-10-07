package com.Schulprojekt.helloprojekt;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.app.AlertDialog;
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
		Thread t = new Thread(new GetMessage(this, loggedUser.getAccountID(), chatPartner.getAccountID()));
		t.start();
	}
		
	ScrollView scv = (ScrollView) findViewById(R.id.scrollViewChat);
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat, menu);
		byte[] bArray = chatPartner.getAccountPicture();
		Bitmap bitmap = BitmapFactory.decodeByteArray(bArray , 0, bArray.length);
		BitmapDrawable d = new BitmapDrawable(bitmap);
		getActionBar().setIcon(d);
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
			        	b.putString("userAccountName", loggedUser.getAccountName());
			        	b.putString("partnerAccountName", chatPartner.getAccountName());
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
    			FileOutputStream fileout = new FileOutputStream("/"+chatPartner.getAccountID());
    			OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
    			outputWriter.write("123gelesen"+txtChat.getText().toString());
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
	        InputStream instream = openFileInput("/"+chatPartner.getAccountID()); 
	        if (instream != null)
	        {
	            InputStreamReader inputreader = new InputStreamReader(instream); 
	            BufferedReader buffreader = new BufferedReader(inputreader); 
	            try
	            {
	                while ((line = buffreader.readLine()) != null)
	                	
	                    if(line.substring(0,10).equals("#123454321#")){
	                    	
	                    	if(line.substring(11,21).equalsIgnoreCase("hangman123:") || line.substring(11,23).equalsIgnoreCase("tictactoe123:")){
	                    	}else{
	                    		TextView receivedText = new TextView(getApplicationContext());
		                		receivedText.setText(line.substring(11));
		                		receivedText.setBackgroundColor(Color.GRAY);
		                		receivedText.setTextColor(Color.WHITE);
		                		receivedText.setGravity(Gravity.LEFT);
		                		layoutMessages.addView(receivedText);
	                    	}
	                    }else{
	                		TextView receivedText = new TextView(getApplicationContext());
	                		receivedText.setText(line);
	                		receivedText.setBackgroundColor(Color.WHITE);
	                		receivedText.setTextColor(Color.BLACK);
	                		receivedText.setGravity(Gravity.RIGHT);
	                		layoutMessages.addView(receivedText);
	                    }
	            }catch (Exception e) 
	            {
	                e.printStackTrace();
	            }
	         }
	    }
	    catch (Exception e) 
	    {
	        String error="";
	        error=e.getMessage();
	    }
	}

	public void refresh(String message){
		 if(line.substring(0,10).equals("#123454321#")){
         	
         	if(line.substring(11,21).equalsIgnoreCase("hangman123:")){
         		
         		AlertDialog.Builder ad = new AlertDialog.Builder(getApplicationContext());
         		ad.setMessage("Sie wurden zu einem Spiel Hangman herausgefordert");
         		ad.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface v, int id) {
							Intent i = new Intent(SimpleChatActivity.this, MainHangmanActivity.class);
							Bundle b = new Bundle();
							b.putString("wort", line.substring(22));
							i.putExtras(b);
							startActivity(i);
						}
					});
					ad.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					});
         		
         	}else if(line.substring(11,21).equalsIgnoreCase("tictactoe123:")){
         		AlertDialog.Builder ad = new AlertDialog.Builder(getApplicationContext());
         		ad.setMessage("Sie wurden zu einem Spiel TicTacToe herausgefordert");
         		ad.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface v, int id) {
							Intent i = new Intent(SimpleChatActivity.this, TicTacToeActivity.class);
							Bundle b = new Bundle();
							b.putString("loggedUser", loggedUser.getAccountName());
							b.putString("chatPartner", chatPartner.getAccountName());
							i.putExtras(b);
							startActivity(i);
						}
					});
					ad.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					});
         	}else{
        		TextView receivedText = new TextView(getApplicationContext());
        		receivedText.setText(message.substring(11));
        		receivedText.setBackgroundColor(Color.GRAY);
        		receivedText.setTextColor(Color.WHITE);
        		receivedText.setGravity(Gravity.LEFT);
        		layoutMessages.addView(receivedText);
         		
         	}
		 }
	}}
		 
