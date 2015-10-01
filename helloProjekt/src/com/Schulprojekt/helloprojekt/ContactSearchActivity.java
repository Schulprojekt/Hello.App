package com.Schulprojekt.helloprojekt;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Schulprojekt.helloprojekt.GUILogik.User;
import com.google.gson.Gson;

public class ContactSearchActivity extends Activity {
	
	private final static String SERVICE_URI = "http://muss.noch.geaendert.werden";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_search);
		Button btnContactSearch = (Button) findViewById(R.id.btnContactSearch);
		final EditText txtContactSearch = (EditText) findViewById(R.id.txtContactSearch);
		btnContactSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				User user = new User();
				
				DefaultHttpClient httpClient = new DefaultHttpClient();								      			//Client erstellen
				
				Gson gson = new Gson();
				String jsonString = "";
				jsonString = gson.toJson(txtContactSearch);
				StringEntity se;
				try{
					se = new StringEntity(jsonString);
					
					HttpPost request = new HttpPost(SERVICE_URI+ "/GetUserByAccountName");   							//Auf die Felder AccountName 
					request.setEntity(se);
					request.setHeader("Accept", "application/json");
					request.setHeader("Content-type", "application/json");
					HttpResponse response;
					
						response = httpClient.execute(request);
						HttpEntity responseEntity = response.getEntity();
						char[] buffer = new char[(int) responseEntity
								.getContentLength()]; 																	//Daten im Array speichern
						InputStream stream = responseEntity.getContent();
						user = gson.fromJson(stream.toString(), User.class);
						InputStreamReader reader = new InputStreamReader(stream); 										//Reader deklarieren
						reader.read(buffer); 																			//Reader liest Buffer
						stream.close();
		        
			        
			        if(user == null){
			        	Toast.makeText(ContactSearchActivity.this, "Benutzer ist nicht vorhanden!", Toast.LENGTH_LONG).show();
			        }else{
			        	Intent i = new Intent(ContactSearchActivity.this, UserProfileActivity.class);
			        	
			        	Bundle b = new Bundle();
			        	b.putString("userId", "");
			        	b.putString("accountName", user.getAccountName());
			        	b.putString("aliasName", user.getAlias());
			        	b.putString("password", user.getPassword());
			        	b.putBoolean("accountState", user.getAccountState());
//			        	b.putString("expierencePoints", user.getString("expierencePoints"));
			        	b.putByteArray("picture", user.getAccountPicture());
			       
//			        	Bundle b = new Bundle();
//			        	Intent i = new Intent(ContactSearchActivity.this, UserProfileActivity.class);
//			        	b.putString("userId", "");
//			        	b.putString("accountName", "test");
//			        	b.putString("aliasName", "test");
//			        	b.putString("password", "test");
//			        	b.putBoolean("accountState", true);
//			        	b.putString("expierencePoints", "...");
//			        	b.putByteArray("picture", new byte[]{50});
			        	
						i.putExtras(b);
						startActivity(i);
			        }     
				}catch (Exception e){
					e.printStackTrace();
				}
				Boolean methode = true;
				if(methode){
					startActivity(new Intent(ContactSearchActivity.this, ContactListActivity.class));					
				}else{
					Toast.makeText(ContactSearchActivity.this, "Benutzer ist nicht vorhanden!", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
