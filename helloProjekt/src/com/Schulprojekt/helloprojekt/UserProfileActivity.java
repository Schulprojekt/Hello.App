package com.Schulprojekt.helloprojekt;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UserProfileActivity extends Activity {
	
	Button btn_hinzufuegen;
	TextView aliasname;
	ImageView picture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
		Bundle b = getIntent().getExtras();																	//Zugriff auf den Übergabewert von HangmanActivity
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

