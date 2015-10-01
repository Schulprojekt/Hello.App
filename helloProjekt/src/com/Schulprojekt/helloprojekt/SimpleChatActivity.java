package com.Schulprojekt.helloprojekt;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
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
import android.widget.TextView;

import com.Schulprojekt.helloprojekt.GUILogik.Message;
import com.Schulprojekt.helloprojekt.GUILogik.User;
import com.google.gson.Gson;

public class SimpleChatActivity extends Activity {
	
	private final static String SERVICE_URI = "http://hello-server/helloservice/messengerservice.svc";

	public ImageView imageViewGame;
	public ImageView imgMessageSend;
	public EditText txtChat;
	public LinearLayout layoutMessages;
	private User loggedUser;
	private User chatPartner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_chat);
		Bundle bundle = getIntent().getExtras();
		loggedUser = new User();
		chatPartner = new User();
		loggedUser.setAccountName(bundle.getString("loggedAccountName"));							
		loggedUser.setAccountPicture(bundle.getByteArray("loggedPicture"));							//Füllen des Bundles mit Key und dem dazugehörigen Wert
		chatPartner.setAlias(bundle.getString("partnerAliasName"));
		chatPartner.setAccountName(bundle.getString("partnerAccountName"));
		chatPartner.setAccountPicture(bundle.getByteArray("partnerPicture"));			
		imageViewGame = (ImageView) findViewById(R.id.imageViewGameChat);
		imageViewGame.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(SimpleChatActivity.this,
		        		SelectGameActivity.class);													
				Bundle b = new Bundle();															//Erstellen eines Bundles
				b.putString("loggedUser", loggedUser.getAccountName());
				b.putString("chatPartner", chatPartner.getAccountName());
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
	}
		

	

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
			Gson gs = new Gson();
			String JsonString;
			Message message = new Message(chatPartner.getAccountID(), loggedUser.getAccountID(), txtChat.getText().toString());
			JsonString = gs.toJson(message);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost request = new HttpPost(SERVICE_URI + "/CreateMessage");
			request.setHeader("Accept", "application/json");
	        request.setHeader("Content-type", "application/json");
	        StringEntity se = new StringEntity(JsonString);
	        request.setEntity(se);
	        HttpResponse response = httpClient.execute(request);
//	        HttpEntity responseEntity = response.getEntity();
	        TextView sentText = new TextView(getApplicationContext());
			sentText.setText(txtChat.getText());
			sentText.setBackgroundColor(Color.WHITE);
			sentText.setTextColor(Color.BLACK);
			sentText.setGravity(Gravity.RIGHT);
			layoutMessages.addView(sentText);
		}catch (Exception e){
			e.printStackTrace();
		}
		receiveMessage(txtChat.getText().toString());
		txtChat.setText("");
	}
	
	public void receiveMessage(String text){
		TextView receivedText = new TextView(getApplicationContext());
		receivedText.setText(text);
		receivedText.setBackgroundColor(Color.GRAY);
		receivedText.setTextColor(Color.WHITE);
		receivedText.setGravity(Gravity.LEFT);
		layoutMessages.addView(receivedText);
		
	}
	
}
