package com.Schulprojekt.helloprojekt;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.Schulprojekt.helloprojekt.GUILogik.User;
import com.Schulprojekt.helloprojekt.GUILogik.UserServices;

public class UserSettingActivity extends Activity {															
	public Button btnDeleteAccount;																			//Deklaration
	public Button btnSave;
	public EditText userSettingUsername;
	public EditText userSettingAliasname;
	public String userId;
	public String aliasName;
	public String accountName;
	public boolean accountState;
	public byte[] picture;
	public String password;
	public User user;
	public Drawable d;

	@Override
	protected void onCreate(Bundle savedInstanceState) {													//Activity wird aufgebaut
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_setting);
		
		// TODO prüfen ob die keys richtig sind!
		Bundle b = getIntent().getExtras();																	//Erstellen eines Bundles
		String userId = b.getString("userId");																//den String userId abrufen
		String aliasName = b.getString("aliasName");														//den String aliasName abrufen
		String accountName = b.getString("accountName");													//den String accountName abrufen
		boolean accountState = b.getBoolean("accountState");												//den Boolean accountState abrufen
		byte[] picture = b.getByteArray("picture");															//das Array picture abrufen
		String password = b.getString("password");															//den String password abrufen
		userSettingAliasname = (EditText) findViewById(R.id.UserSettingAliasname);							//Datenbankzugriff auf den Aliasname
		userSettingAliasname.setText(aliasName);															//aliasName setzen/ändern
		userSettingUsername = (EditText) findViewById(R.id.UserSettingUsername);							//Datenbankzugriff auf den Username
		userSettingUsername.setText(accountName);															//accountName setzen/ändern
		
		// Datenbankzugriff für Account löschen
		btnDeleteAccount = (Button) findViewById(R.id.btnDeleteAccount);
		btnDeleteAccount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (v == btnDeleteAccount){
					try{
						
						AlertDialog.Builder ad = new AlertDialog.Builder(getApplicationContext());
		         		ad.setMessage("Wollen Sie Ihren Account wirklich löschen?");
		         		ad.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface v, int id) {
									UserServices.deleteUser(userSettingUsername.getText().toString());
									Intent i = new Intent(UserSettingActivity.this, LoginActivity.class);
									startActivity(i);
									finish();
									System.exit(0);
								}
							});
							ad.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which) {
									
								}
							});
						
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
				if (v == btnSave){
					user = UserServices.updateUser(userSettingUsername.getText().toString(), 
							userSettingAliasname.getText().toString());
					
					Intent i = new Intent(UserSettingActivity.this, 						// sonst wird die nächste Activity ContactListActivity gestartet
							ContactListActivity.class);
					Bundle b = new Bundle();
					b.putInt("userId", user.getAccountID());
					b.putString("aliasName", user.getAlias());
					b.putString("accountName", user.getAccountName());
					b.putString("password", user.getPassword());
					i.putExtras(b);
					startActivity(i);
					finish();
					
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