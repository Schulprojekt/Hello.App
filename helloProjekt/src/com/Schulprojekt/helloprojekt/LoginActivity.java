package com.Schulprojekt.helloprojekt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginActivity extends Activity{

	public Button bregistrieren;
	public Button banmelden;
	public ImageButton imageButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		bregistrieren = (Button) findViewById(R.id.register);
		bregistrieren.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v == bregistrieren){
					startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
				}
				
			}
		});
		banmelden = (Button) findViewById(R.id.login);
		banmelden.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v == banmelden){
					Boolean methode = true;
					if(methode){
						Intent myIntent = new Intent(LoginActivity.this, ContactListActivity.class);
						LoginActivity.this.startActivity(myIntent);					
					}else{
						Toast.makeText(LoginActivity.this, "Benutzername oder Passwort falsch!", Toast.LENGTH_LONG).show();
					}
				}
				
			}
		});
//		imageButton = (ImageButton) findViewById(R.id.imageButton);
//		imageButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				if (v == imageButton){
//					Boolean methode = true;
//					
//					if(methode){
//						startActivity(new Intent(LoginActivity.this, ContactListActivity.class));					
//					}else{
//						Toast.makeText(LoginActivity.this, "Benutzername oder Passwort falsch!", Toast.LENGTH_LONG).show();
//					}
//				}
//				
//			}
//		});
//		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
	
	public void onClickListener(){
		
	}
}
