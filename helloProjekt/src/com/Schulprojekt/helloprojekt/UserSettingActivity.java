package com.Schulprojekt.helloprojekt;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.Schulprojekt.helloprojekt.GUILogik.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UserSettingActivity extends Activity {

	public Button btnDeleteAccount;
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
	
	private final static String SERVICE_URI = "http:////hello-server//helloservice//messengerservice.svc";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_setting);
		
		Bundle b = getIntent().getExtras();
		String userId = b.getString("userId");
		String aliasName = b.getString("aliasName");
		String accountName = b.getString("accountName");
		boolean accountState = b.getBoolean("accountState");
//		int experiencePoints = b.getInt("experiencePoints");
		byte[] picture = b.getByteArray("picture");
		String password = b.getString("password");
		
		// Datenbankzugriff für Username
		// TODO auf den user zugreifen um den usernamen zu bekommen
		userSettingAliasname = (EditText) findViewById(R.id.UserSettingAliasname);	
		userSettingAliasname.setText(aliasName);

		userSettingUsername = (EditText) findViewById(R.id.UserSettingUsername);	
		userSettingUsername.setText(accountName);
		
		
		
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_setting, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}