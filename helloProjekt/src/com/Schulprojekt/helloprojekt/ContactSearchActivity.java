package com.Schulprojekt.helloprojekt;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Schulprojekt.helloprojekt.GUILogik.User;
import com.Schulprojekt.helloprojekt.GUILogik.UserServices;
import com.google.gson.Gson;

public class ContactSearchActivity extends Activity {
	public User user;
	public EditText txtContactSearch;
	public Button btnContactSearch;
	public Drawable d;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {															//Activity wird aufgebaut
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_search);
		
		Bundle b = getIntent().getExtras();																	//Erstellen eines Bundles
		final int userId = b.getInt("userId");																//den String userId abrufen
		final String aliasName = b.getString("aliasName");														//den String aliasName abrufen
		final String accountName = b.getString("accountName");													//den String accountName abrufen
		final byte[] picture = b.getByteArray("picture");															//das Array picture abrufen
		final String password = b.getString("password");															//den String password abrufen
		
		btnContactSearch = (Button) findViewById(R.id.btnContactSearch);
		txtContactSearch = (EditText) findViewById(R.id.txtContactSearch);
		btnContactSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(v == btnContactSearch){
					user = UserServices.getUserByAccountName(txtContactSearch.getText().toString());
					
					if (user.getAccountName() == null) {
						Toast.makeText(ContactSearchActivity.this, "Benutzer konnte nicht gefunden werden!", Toast.LENGTH_LONG).show();
					}else{
						
						Intent i = new Intent(ContactSearchActivity.this, 						// sonst wird die nächste Activity ContactListActivity gestartet
								UserProfileActivity.class);
						Bundle b = new Bundle();
						
						b.putString("SearchedAliasName", user.getAlias());
						b.putString("SearchedAccountName", user.getAccountName());
						
						b.putInt("userId", userId);
						b.putString("aliasName", aliasName);
						b.putString("accountName", accountName);
						b.putByteArray("picture", picture);
						b.putString("password", password);
						
						i.putExtras(b);
						startActivity(i);
						finish();
						System.exit(0);
						
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {																	//Aufbau eines Menüs
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {															//Wird ein Item im Menü ausgewählt, wirft die Methode true zurück
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
