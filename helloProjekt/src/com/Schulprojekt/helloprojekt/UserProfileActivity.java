package com.Schulprojekt.helloprojekt;

import java.io.File;
import java.util.ArrayList;

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
import com.Schulprojekt.helloprojekt.GUILogik.RelationshipServices;
import com.Schulprojekt.helloprojekt.GUILogik.User;

public class UserProfileActivity extends Activity {
	
	
	Button btn_hinzufuegen;																					//Deklaration
	TextView aliasname;
	TextView accountname;
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
    	
    	accountname = (TextView) findViewById(R.id.txt_profilAccName);
    	accountname.setText(accountName);
    	
    	ArrayList<User> friends = new ArrayList<User>();													//Erstellen einer UserArrayList
		friends = getFriends();																				//Füllen der ArrayList
		for (User user : friends) {
			if(user.getAccountID() == userId){
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
	
																			
		Relationship relationship = new Relationship(userId, userIdLogged);
		RelationshipServices.createRelationship(relationship);

		}
	public void loescheBenutzer(User loggedUser, User chatPartner){
		
		Relationship relationship = new Relationship(userId, userIdLogged);
		RelationshipServices.deleteRelationship(relationship);

		
		}
	
	public ArrayList<User> getFriends(){																		//Erstellen der Methode getFriends
	    	ArrayList<User> friends = new ArrayList<User>();													//Erstellen einer ArrayList
	    	
	    	friends = RelationshipServices.getRelationship(userIdLogged);
	    	
			return friends;
	    }
}

