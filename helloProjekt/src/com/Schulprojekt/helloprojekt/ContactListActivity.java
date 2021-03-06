package com.Schulprojekt.helloprojekt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Schulprojekt.helloprojekt.GUILogik.ContactListEntry;
import com.Schulprojekt.helloprojekt.GUILogik.RelationshipServices;
import com.Schulprojekt.helloprojekt.GUILogik.User;
//import com.Schulprojekt.helloprojekt.GUILogik.SimpleThreads;

public class ContactListActivity extends Activity {

	ArrayList<User> contacts = new ArrayList<User>();														// Deklaration
	ArrayList<ContactListEntry> contactList;
	String userName;
	User user;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {													//Activity wird aufgebaut
		super.onCreate(savedInstanceState);
//		SimpleThreads st = new SimpleThreads();
		Bundle b = getIntent().getExtras();																	//Erstellen eines Bundles
		user = new User();																					//Erstellen eines neuen Users
		user.setAccountID(b.getInt("userId"));															    //F�llen der Accountid
		user.setAccountName(b.getString("accountName"));													//F�llen des Accountnamens
		user.setAlias(b.getString("aliasName"));															//F�llen des Aliasnamens
		user.setPassword(b.getString("password"));															//F�llen des Passwortes
		user.setAccountPicture(b.getByteArray("picture"));													//F�llen des Profilbildes
		setContentView(R.layout.activity_contact_list);

		final LinearLayout linlayoutVertical = (LinearLayout) findViewById(R.id.linLayoutContactVertical);
		findViewById(R.id.scrollViewContact);
		findViewById(R.id.textViewContact);

		
		contacts = RelationshipServices.getRelationship(b.getInt("userId"));								//F�llen der ArrayList
		
		int i = 0;
		
		ArrayList<ContactListEntry> contactList = new ArrayList<ContactListEntry>();						//Erstellen einer ArrayList
		
		for (User user : contacts) {																		//Erweiterte For-Schleife zum Auslesen der Freundesliste
			
			File file = new File("/data/data/com.Schulprojekt.helloprojekt/files/"+user.getAccountID()+".txt");
			
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			LinearLayout linlay = new LinearLayout(getApplicationContext());
			linlay.setOrientation(LinearLayout.HORIZONTAL);
			ImageView img = new ImageView(getApplicationContext());
			if(user.getAccountPicture() != null){
			BitmapDrawable bmp = new BitmapDrawable(BitmapFactory.decodeByteArray(user.getAccountPicture(), 0, user.getAccountPicture().length));
			img.setImageDrawable(bmp);
			}
			else{
				Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.dummycontact);
				BitmapDrawable bmp = new BitmapDrawable(icon);
				img.setImageDrawable(bmp);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				icon.compress(Bitmap.CompressFormat.PNG, 100, baos);
				byte[] byteArray = baos.toByteArray();
				user.setAccountPicture(byteArray);
				contacts.set(i, user);
			}
			img.setOnClickListener(getOnKlickListener(img));
			img.setId(i);
			TextView tv = new TextView(getApplicationContext());
			tv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
			tv.setTextColor(Color.BLACK);
			tv.setGravity(Gravity.CENTER_VERTICAL);
			tv.setTextSize(24);
			tv.setText(user.getAlias());
			linlay.addView(img);
			linlay.addView(tv);
			ContactListEntry entry = new ContactListEntry(linlay, img, tv, user.getAccountName());
			contactList.add(entry);
			i = i+1;
		}
		for (ContactListEntry contact : contactList) {														//Erweiterte For-Schleife zum Auslesen der Kontaktliste
			LayoutParams layout = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
			contact.getLinlayout().setLayoutParams(layout);
			linlayoutVertical.addView(contact.getLinlayout());
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {															//Aufbau eines Men�s
		getMenuInflater().inflate(R.menu.contact_list, menu);
		getActionBar().setIcon(R.drawable.dummycontact);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {													//Wird ein Item im Men� ausgew�hlt, wirft die Methode true zur�ck
		switch (item.getItemId()) {
		case R.id.contact_profile:
			Intent in = new Intent(ContactListActivity.this, UserSettingActivity.class);					//Erstellen des Intent
			Bundle b = new Bundle();																		//Erstellen des Bundles
			b.putInt("userId", user.getAccountID());														//F�llen des Bundles
			b.putString("accountName", user.getAccountName());
			b.putString("aliasName", user.getAlias());
			b.putByteArray("picture", user.getAccountPicture());
			b.putString("password", user.getPassword());
			in.putExtras(b);
			startActivity(in);																				//Starten der Activity
			break;
		case R.id.act_ContactSearch:
			Intent intent = new Intent(ContactListActivity.this, ContactSearchActivity.class);				//Erstellen des Intent
			Bundle bundle = new Bundle();																	//Erstellen des Bundles
			bundle.putInt("userId", user.getAccountID());													//F�llen des Bundles
			bundle.putString("accountName", user.getAccountName());
			bundle.putString("aliasName", user.getAlias());
			bundle.putByteArray("picture", user.getAccountPicture());
			bundle.putString("password", user.getPassword());
			intent.putExtras(bundle);
			startActivity(intent);												
			break;
		case R.id.contactListRefresh:
			Intent i = getIntent();
			finish();
			startActivity(i);
		case R.id.act_AppExit:
			finish();
			break;
			default:	
		}
		return super.onOptionsItemSelected(item);
	}
	
	public View.OnClickListener getOnKlickListener(ImageView img){
	    return new View.OnClickListener() {
	        public void onClick(View v) {
	        	Intent in = new Intent(ContactListActivity.this, SimpleChatActivity.class);					//Erstellen des Intent
	        	Bundle b = new Bundle();																	//Erstellen des Bundles
	        	b.putInt("loggedAccountId", user.getAccountID());
	        	b.putString("loggedAccountName", user.getAccountName());									//F�llen des Bundles
	        	b.putByteArray("loggedPicture", user.getAccountPicture());
	        	b.putInt("partnerAccountId", contacts.get(v.getId()).getAccountID());
				b.putString("partnerAliasName", contacts.get(v.getId()).getAlias().toString());
				b.putString("partnerAccountName", contacts.get(v.getId()).getAccountName().toString());
				b.putByteArray("partnerPicture", contacts.get(v.getId()).getAccountPicture());
				in.putExtras(b);
				startActivity(in);																			//Starten der neuen Activity
	        }
	    };
	}
}