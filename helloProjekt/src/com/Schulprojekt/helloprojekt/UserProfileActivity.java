package com.Schulprojekt.helloprojekt;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Schulprojekt.helloprojekt.GUILogik.Relationship;
import com.Schulprojekt.helloprojekt.GUILogik.RelationshipServices;
import com.Schulprojekt.helloprojekt.GUILogik.User;

public class UserProfileActivity extends Activity {
	
	
	Button btn_hinzufuegen;																					//Deklaration
	TextView aliasname;
	TextView accountname;
	ImageView pictures;
	
	int userId;
	String accountName;
	String aliasName;
	
	int userIdLogged;
	String accountNameLogged;
	String aliasNameLogged;
	String passwordLogged;
	byte[] pictureLogged;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {													//Aufbau der Activity
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
		Bundle b = getIntent().getExtras();																	//Füllen des Bundles
		
		userId = b.getInt("SearchedId");
		accountName = b.getString("SearchedAccountName");
		aliasName = b.getString("SearchedAliasName");
		
		userIdLogged = b.getInt("userId");
		accountNameLogged = b.getString("accountName");
		aliasNameLogged = b.getString("aliasName");
		passwordLogged = b.getString("password");
		pictureLogged = b.getByteArray("picture");
    	
    	aliasname = (TextView) findViewById(R.id.txt_profilName);
    	aliasname.setText(aliasName);
    	
    	accountname = (TextView) findViewById(R.id.txt_profilAccName);
    	accountname.setText(accountName);
    	
    	ArrayList<User> friends = new ArrayList<User>();													//Erstellen einer ArrayList
    	friends = RelationshipServices.getRelationship(userIdLogged);																			//Füllen der ArrayList

    	for (User user : friends) {
			if(user.getAccountID() == userId){
				btn_hinzufuegen = (Button) findViewById(R.id.btn_hinzufuegen);
				btn_hinzufuegen.setText("Benutzer löschen");
			}
		}
		
		btn_hinzufuegen = (Button) findViewById(R.id.btn_hinzufuegen);
		if (btn_hinzufuegen.getText().toString().equalsIgnoreCase("Benutzer hinzufügen")){
			btn_hinzufuegen.setOnClickListener(new OnClickListener() {												//Methodenaufruf

				@Override
				public void onClick(View v) {
					Relationship relationship = new Relationship(userId, userIdLogged);
					RelationshipServices.createRelationship(relationship);
					Relationship relationship2 = new Relationship(userIdLogged, userId);
					RelationshipServices.createRelationship(relationship2);
					
					File chat = new File("/"+userId);
					chat.mkdirs();
					
					Intent i = new Intent(UserProfileActivity.this, 						// sonst wird die nächste Activity ContactListActivity gestartet
							ContactListActivity.class);
					Bundle b = new Bundle();
					
					b.putInt("userId", userIdLogged);
					b.putString("aliasName", aliasNameLogged);
					b.putString("accountName", accountNameLogged);
					b.putString("password", passwordLogged);
					
					i.putExtras(b);
					startActivity(i);
					finish();
				}
			});
		} else {
			btn_hinzufuegen.setOnClickListener(new OnClickListener() {												//Methodenaufruf

				@Override
				public void onClick(View v) {
					Relationship relationship = new Relationship(userId, userIdLogged);
					RelationshipServices.deleteRelationship(relationship);
					Relationship relationship2 = new Relationship(userIdLogged, userId);
					RelationshipServices.deleteRelationship(relationship2);
					
					Intent i = new Intent(UserProfileActivity.this, 						// sonst wird die nächste Activity ContactListActivity gestartet
							ContactListActivity.class);
					Bundle b = new Bundle();
					
					b.putInt("userId", userIdLogged);
					b.putString("aliasName", aliasNameLogged);
					b.putString("accountName", accountNameLogged);
					b.putString("password", passwordLogged);
					
					i.putExtras(b);
					startActivity(i);
					finish();
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

}

