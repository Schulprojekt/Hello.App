package com.Schulprojekt.helloprojekt;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONStringer;

import com.Schulprojekt.helloprojekt.GUILogik.Message;
import com.Schulprojekt.helloprojekt.GUILogik.Relationship;
import com.Schulprojekt.helloprojekt.GUILogik.User;
import com.google.gson.Gson;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfileActivity extends Activity {
	
	private final static String SERVICE_URI = "http://lt0.studio.entail.ca:8080/VehicleService.svc";		//Pfad zum server
	
	Button btn_hinzufuegen;																					//Deklaration
	TextView aliasname;
	ImageView picture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {													//Aufbau der Activity
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
		Bundle b = getIntent().getExtras();																	//Füllen des Bundles
		String userId = b.getString("userId");
		String accountName = b.getString("accountName");
		String aliasName = b.getString("aliasName");
		String password = b.getString("password");
		Boolean accountState = b.getBoolean("accountState");
//		String expierencePoints = b.getString("expierencePoints");
		byte[] picture = b.getByteArray("picture");
		
		
		
    	b.putString("userId", "");
    	b.putString("accountName", "test");
    	b.putString("aliasName", "test");
    	b.putString("password", "test");
    	b.putBoolean("accountState", true);
//    	b.putString("expierencePoints", "...");
    	b.putByteArray("picture", new byte[]{50});
    	
    	aliasname = (TextView) findViewById(R.id.txt_profilName);
    	aliasname.setText(aliasName);
    	
    	
		
		btn_hinzufuegen = (Button) findViewById(R.id.btn_hinzufuegen);
		if (btn_hinzufuegen.getText().toString().equalsIgnoreCase("Benutzer hinzufügen")){
			
		} else {
			
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.user_profile, menu);
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
	public void startConnection(){
		
		Bundle bundle = getIntent().getExtras();	
		String loggedUser = bundle.getString("loggedUser");
		String chatPartner = bundle.getString("chatPartner");
		User user = new User();
		User user2 = new User();
		
		DefaultHttpClient httpClient = new DefaultHttpClient();								      			//Client erstellen
		
		Gson gson = new Gson();
		String jsonString = "";
		jsonString = gson.toJson(loggedUser);
		StringEntity se;
		try {
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

			
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Gson gson2 = new Gson();
		String jsonString2 = "";
		jsonString2 = gson.toJson(chatPartner);
		StringEntity se2;
		try {
			se2 = new StringEntity(jsonString);
	
		HttpPost request2 = new HttpPost(SERVICE_URI+ "/GetUserByAccountName" );  							//Auf die Felder AccountName 
		request2.setEntity(se2);
		request2.setHeader("Accept", "application/json");
		request2.setHeader("Content-type", "application/json");
		HttpResponse response2;
			response2 = httpClient.execute(request2);
			HttpEntity responseEntity = response2.getEntity();
			char[] buffer = new char[(int) responseEntity
					.getContentLength()]; 																	//Daten im Array speichern
			InputStream stream2 = responseEntity.getContent();
			user2= gson2.fromJson(stream2.toString(), User.class);
			InputStreamReader reader = new InputStreamReader(stream2); 										//Reader deklarieren
			reader.read(buffer); 																			//Reader liest Buffer
			stream2.close();

			
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		try {
		Gson gson3 = new Gson();																			//Erstellen eines GsonObjektes
		String JsonString3;																					//Erstellen eines JsonStrings
		Relationship relationship = new Relationship(user2.getAccountID(),user.getAccountID());
		JsonString3 = gson3.toJson(relationship);
		DefaultHttpClient httpClient3 = new DefaultHttpClient();
		HttpPost request3 = new HttpPost(SERVICE_URI + "/CreateRelationship");
		request3.setHeader("Accept", "application/json");
        request3.setHeader("Content-type", "application/json");
        StringEntity se3 = new StringEntity(JsonString3);
        request3.setEntity(se3);
        HttpResponse response3 = httpClient3.execute(request3);
        
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		}
}

