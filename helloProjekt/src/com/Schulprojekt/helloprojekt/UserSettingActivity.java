package com.Schulprojekt.helloprojekt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
	private final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {													//Activity wird aufgebaut
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_setting);
		

		
		Bundle b = getIntent().getExtras();																	//Erstellen eines Bundles
		String userId = b.getString("userId");																//String userId abrufen
		String aliasName = b.getString("aliasName");														//String aliasName abrufen
		String accountName = b.getString("accountName");													//String accountName abrufen
		boolean accountState = b.getBoolean("accountState");												//Boolean accountState abrufen
		byte[] picture = b.getByteArray("picture");															//Array picture abrufen
		String password = b.getString("password");															//String password abrufen
		userSettingAliasname = (EditText) findViewById(R.id.UserSettingAliasname);							//Datenbankzugriff auf den Aliasname
		userSettingAliasname.setText(aliasName);															//aliasName setzen/ändern
		userSettingUsername = (EditText) findViewById(R.id.UserSettingUsername);							//Datenbankzugriff auf den Username
		userSettingUsername.setText(accountName);															//accountName setzen/ändern
		
																											//Datenbankzugriff für Account löschen
		btnDeleteAccount = (Button) findViewById(R.id.btnDeleteAccount);
		btnDeleteAccount.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (v == btnDeleteAccount){
					try{
						new AlertDialog.Builder(context).setTitle("Achtung!")								//Erstellen eines Popup-Fensters
						.setMessage("Wollen Sie Ihren Account wirklich löschen?")
						.setPositiveButton("Ja", new DialogInterface.OnClickListener() {					//Bei Klick auf Ja:
						public void onClick(DialogInterface dialog, int which) {
							UserServices.deleteUser(userSettingUsername.getText().toString());				//Eigener User wird gelöscht
							Intent i = new Intent(UserSettingActivity.this, LoginActivity.class);			//Erstellen eines Intent
							startActivity(i);																//Öffnen der Loginactivity
							finish();
							System.exit(0);																	//UserSettingActivity schließen
						}
						}).setNegativeButton("Nein", new DialogInterface.OnClickListener() {				//Bei Klich auf Nein:
						public void onClick(DialogInterface dialog, int which) {
																											//Keine Funktion ausführen
						}
						}).setIcon(R.drawable.ic_launcher).show();
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			}
		});
		
																										   	//Datenbankzugriff für Speichern
		btnSave = (Button) findViewById(R.id.btnSave);
		btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (v == btnSave){
					user = UserServices.updateUser(userSettingUsername.getText().toString(), 
							userSettingAliasname.getText().toString());
					
					Intent i = new Intent(UserSettingActivity.this, 										//Nächste Activity "ContactListActivity" wird gestartet
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
	public boolean onCreateOptionsMenu(Menu menu) {															//Menü wird aufgebaut
		getMenuInflater().inflate(R.menu.user_setting, menu);
		return true;																						//Wenn Menü aufgebaut ist, gibt die Methode true zurück
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {													//Wird ein Item im Menüe ausgewählt, gibt die Methode true zurück
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}