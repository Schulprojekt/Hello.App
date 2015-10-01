package com.Schulprojekt.helloprojekt;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Schulprojekt.helloprojekt.GUILogik.Relationship;
import com.Schulprojekt.helloprojekt.GUILogik.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UserProfileActivity extends Activity {
	
	private final static String SERVICE_URI = "http://lt0.studio.entail.ca:8080/VehicleService.svc";		//Pfad zum server
	
	Button btn_hinzufuegen;																					//Deklaration
	TextView aliasname;
	ImageView pictures;
	Bundle b = getIntent().getExtras();																	//Füllen des Bundles
	int userId = b.getInt("userId");
	String accountName = b.getString("accountName");
	String aliasName = b.getString("aliasName");
	String password = b.getString("password");
	byte[] picture = b.getByteArray("picture");
	int userIdLogged = b.getInt("userIdLogged");
	String accountNameLogged = b.getString("accountNameLogged");
	String aliasNameLogged = b.getString("aliasNameLogged");
	String passwordLogged = b.getString("passwordLogged");
	byte[] pictureLogged = b.getByteArray("pictureLogged");
	
	User loggedUser = new User(userIdLogged,accountNameLogged,aliasNameLogged,passwordLogged,pictureLogged);
	User chatPartner = new User(userId,accountName,aliasName,password,picture);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {													//Aufbau der Activity
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
		
//    	b.putString("userId", "");
//    	b.putString("accountName", "test");
//    	b.putString("aliasName", "test");
//    	b.putString("password", "test");
//    	b.putBoolean("accountState", true);
//    	b.putByteArray("picture", new byte[]{50});
    	
    	aliasname = (TextView) findViewById(R.id.txt_profilName);
    	aliasname.setText(aliasName);
    	
    	ArrayList<User> friends = new ArrayList<User>();													//Erstellen einer UserArrayList
		friends = getFriends();																				//Füllen der ArrayList
		for (User user : friends) {
			if(user.getAccountName().equalsIgnoreCase(accountName)){
				btn_hinzufuegen.setText("Benutzer löschen");
			}
		}
		
		btn_hinzufuegen = (Button) findViewById(R.id.btn_hinzufuegen);
		if (btn_hinzufuegen.getText().toString().equalsIgnoreCase("Benutzer hinzufügen")){
			btn_hinzufuegen.setOnClickListener(new OnClickListener() {												//Methodenaufruf

				@Override
				public void onClick(View v) {
					fuegeBenutzerHinzu(loggedUser, chatPartner);
					
					File chat = new File("/"+userId);
					chat.mkdirs();
				}
			});
		} else {
			btn_hinzufuegen.setOnClickListener(new OnClickListener() {												//Methodenaufruf

				@Override
				public void onClick(View v) {
					loescheBenutzer(loggedUser, chatPartner);
					
				}
			});
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
	public void fuegeBenutzerHinzu(User loggedUser, User chatPartner){
		
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
	public void loescheBenutzer(User loggedUser, User chatPartner){
		
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
		HttpPost request3 = new HttpPost(SERVICE_URI + "/DeleteRelationship");
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
	 public ArrayList<User> getFriends(){																		//Erstellen der Methode getFriends
	    	ArrayList<User> friends = new ArrayList<User>();													//Erstellen einer ArrayList
	    	Gson gs = new Gson();																				//Erstellen eines GsonObjektes
	    	User user = new User();
	    	String jsonString = "";																				//Erstellen eines JsonStrings
				jsonString = gs.toJson(user.getAccountID());													//Füllen des JsonStrings
				StringEntity se;																				//Erstellen eines StringEntity
				try {
					se = new StringEntity(jsonString);															//Füllen des StringEntitys
				DefaultHttpClient httpClient = new DefaultHttpClient();											//Erstellen des HttpClients
				HttpPost request = new HttpPost(SERVICE_URI+ "/GetRelationship");    							//Aufrufen der Methode GetRelationship
				request.setEntity(se);
				request.setHeader("Accept", "application/json");
				request.setHeader("Content-type", "application/json");
				HttpResponse response = null;
				response = httpClient.execute(request);
				HttpEntity responseEntity = response.getEntity();
				InputStream stream = null;
				stream = responseEntity.getContent();
				friends = gs.fromJson(stream.toString(), new TypeToken<List<User>>(){}.getType());				//Füllen der ArrayList friends
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return friends;
	    }
}

