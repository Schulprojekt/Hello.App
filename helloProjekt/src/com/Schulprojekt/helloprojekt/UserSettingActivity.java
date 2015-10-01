package com.Schulprojekt.helloprojekt;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UserSettingActivity extends Activity {															
	public Button btnDeleteAccount;																			//Deklaration
	public Button btnSave;
	public EditText userSettingUsername;
	public EditText userSettingAliasname;
	String userId;
	String aliasName;
	String accountName;
	boolean accountState;
//	int experiencePoints;
	byte[] picture;
	String password;
	private final static String SERVICE_URI = "http:////hello-server//helloservice" +						//Pfad zum Server
			"//messengerservice.svc";
	@Override
	protected void onCreate(Bundle savedInstanceState) {													//Activity wird aufgebaut
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_setting);
		
		Bundle b = getIntent().getExtras();																	//Erstellen eines Bundles
		String userId = b.getString("userId");																//den String userId abrufen
		String aliasName = b.getString("aliasName");														//den String aliasName abrufen
		String accountName = b.getString("accountName");													//den String accountName abrufen
		boolean accountState = b.getBoolean("accountState");												//den Boolean accountState abrufen
//		int experiencePoints = b.getInt("experiencePoints");
		byte[] picture = b.getByteArray("picture");															//das Array picture abrufen
		String password = b.getString("password");															//den String password abrufen
		userSettingAliasname = (EditText) findViewById(R.id.UserSettingAliasname);							//Datenbankzugriff auf den Aliasname
		userSettingAliasname.setText(aliasName);															//aliasName setzen/ändern
		userSettingUsername = (EditText) findViewById(R.id.UserSettingUsername);							//Datenbankzugriff auf den Username
		userSettingUsername.setText(accountName);															//accountName setzen/ändern
		
		// Datenbankzugriff für Aliasname
		try{
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			// TODO auf den user zugreifen um den usernamen zu bekommen
			HttpGet request = new HttpGet(SERVICE_URI + "/GetUserByAccountName/" + userSettingUsername);
			
			request.setHeader("Accept", "application/json");
		    request.setHeader("Content-type", "application/json");
		
		    HttpResponse response = httpClient.execute(request);
		    
		    HttpEntity responseEntity = response.getEntity();
		    
		    // Read response data into buffer
		    char[] buffer = new char[(int)responseEntity.getContentLength()];
		    InputStream stream = responseEntity.getContent();
		    InputStreamReader reader = new InputStreamReader(stream);
		    reader.read(buffer);
		    stream.close();
		
		    JSONObject user = new JSONObject(new String(buffer));
		    
		    // Populate text fields
		    userSettingAliasname.setText(user.getString("aliasName"));
		    
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
		// Datenbankzugriff für Account löschen
		btnDeleteAccount = (Button) findViewById(R.id.btnDeleteAccount);
		btnDeleteAccount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v == btnDeleteAccount){
					try{
						DefaultHttpClient httpClient = new DefaultHttpClient();
						
						// TODO auf den user zugreifen um den usernamen zu bekommen
						HttpGet request = new HttpGet(SERVICE_URI + "/DeleteUser/" + "");
						
						request.setHeader("Accept", "application/json");
					    request.setHeader("Content-type", "application/json");
					
					    HttpResponse response = httpClient.execute(request);
					    
					    HttpEntity responseEntity = response.getEntity();
					    
					    // Read response data into buffer
					    char[] buffer = new char[(int)responseEntity.getContentLength()];
					    InputStream stream = responseEntity.getContent();
					    InputStreamReader reader = new InputStreamReader(stream);
					    reader.read(buffer);
					    stream.close();
					
					    JSONObject user = new JSONObject(new String(buffer));
					    
					    // Populate text fields
					    userSettingAliasname.setText(user.getString("aliasName"));
					}catch (Exception e){
						e.printStackTrace();
					}
					
				}
				
			}
		});
		
		// Datenbankzugriff für Speichern
		btnSave = (Button) findViewById(R.id.btnSave);
		btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v == btnSave){
					
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {																//Menü wird aufgebaut
		getMenuInflater().inflate(R.menu.user_setting, menu);
		return true;																							//wenn Menü aufgebaut ist, gibt die Methode true zurück
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {														//wird ein Item im Menüe ausgewählt, gibt die Methode true zurück
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}