package com.Schulprojekt.helloprojekt;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

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
				// TODO Auto-generated method stub
				try{
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpGet request = new HttpGet(SERVICE_URI + "/GetUserByAccountName/" + txtContactSearch);
					
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
			        txtContactSearch.setText(user.getString("accountName"));
			        
			        if(txtContactSearch == null){
			        	Toast.makeText(ContactSearchActivity.this, "Benutzer ist nicht vorhanden!", Toast.LENGTH_LONG).show();
			        }else{
			        	startActivity(new Intent(ContactSearchActivity.this, ContactListActivity.class));
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
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
