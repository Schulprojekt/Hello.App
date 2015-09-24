package com.Schulprojekt.helloprojekt;

import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.Schulprojekt.helloprojekt.GUILogik.Message;

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

public class SimpleChatActivity extends Activity {
	
	private final static String SERVICE_URI = "http://muss.noch.geaendert.werden";

	public Bundle b;
	public Intent in;
	public ImageView imageViewGame;
	public ImageView imgMessageSend;
	public EditText txtChat;
	public LinearLayout layoutMessages;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_chat);
		in = getIntent();
		b = in.getExtras();
		imageViewGame = (ImageView) findViewById(R.id.imageViewGameChat);
		imageViewGame.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
			        Intent i = new Intent(SimpleChatActivity.this,
			        		SelectGameActivity.class);
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
		byte[] bArray = b.getByteArray("picture");
		Bitmap bitmap = BitmapFactory.decodeByteArray(bArray , 0, bArray.length);
		BitmapDrawable d = new BitmapDrawable(bitmap);
		getActionBar().setIcon(d);
		getActionBar().setTitle(b.getString("username"));
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch(item.getItemId())
		{
		case R.id.action_settings:
			return true;
		case R.id.chat_AppExit:
			finish();
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void sendMessage(){
		TextView sentText = new TextView(getApplicationContext());
		sentText.setText(txtChat.getText());
		sentText.setBackgroundColor(Color.GRAY);
		sentText.setTextColor(Color.BLACK);
		sentText.setGravity(Gravity.RIGHT);
		layoutMessages.addView(sentText);
		try{
			Message message = new Message(UUID.randomUUID(), UUID.randomUUID(), txtChat.getText().toString());
			JSONObject messa = new JSONObject();
			messa.put("id", message.getMessageID());
			messa.put("sender", message.getSender());
			messa.put("receiver", message.getReceiver());
			messa.put("message", message.getMessageText());
			messa.put("attchment", message.getAttachement());
			messa.put("timestamp", message.getMessageTime());
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet request = new HttpGet(SERVICE_URI + "/CreateMessage");
			
			request.setHeader("Accept", "application/json");
	        request.setHeader("Content-type", "application/json");

	        HttpResponse response = httpClient.execute(request);
	        
	        HttpEntity responseEntity = response.getEntity();
	        
//	        // Read response data into buffer
//	        char[] buffer = new char[(int)responseEntity.getContentLength()];
//	        InputStream stream = responseEntity.getContent();
//	        InputStreamReader reader = new InputStreamReader(stream);
//	        reader.read(buffer);
//	        stream.close();
//
//	        JSONObject user = new JSONObject(new String(buffer));
	        
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
