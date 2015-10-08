package com.Schulprojekt.helloprojekt;

import android.app.Activity;
import android.content.Intent;
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
import com.Schulprojekt.helloprojekt.GUILogik.md5Generator;


public class RegistrationActivity extends Activity {
	public Button bregistrieren;																				//Deklaration
	public EditText tvname;
	public EditText tvalias;
	public EditText tvpasswort;
	public EditText tvpasswortwh;
	public User user;
	public Drawable d;
	public String md5Pass;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {														//Activity wird aufgebaut
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        
        bregistrieren = (Button) findViewById(R.id.registrieren);												//Auf Button register zugreifen
        bregistrieren.setOnClickListener(new OnClickListener() {												//Auf den Button register einen OnClickListener setzen
        	public void onClick(View view) {
        		if(view == bregistrieren) {
        			tvname = (EditText) findViewById(R.id.username);											//Auf Textfeld username zugreifen
        			tvalias = (EditText) findViewById(R.id.username);											//Auf Textfeld username zugreifen - bei der Registrierung entspricht der Alias dem Username
        			tvpasswort = (EditText) findViewById(R.id.passwort);										//Auf Textfeld passwort zugreifen
        			tvpasswortwh = (EditText) findViewById(R.id.passwortwh);									//Auf Textfeld passwortwh zugreifen
        			String name = tvname.getText().toString();													//tvname in einen String umwandeln und in dem String name speichern
        			String alias = tvalias.getText().toString();												//tvalias in einen String umwandeln und in dem String alias speichern
        			String passwort = tvpasswort.getText().toString();											//tvpasswort in einen String umwandeln und in dem String passwort speichern
        			String passwortwh = tvpasswortwh.getText().toString();										//tvpasswortwh in einen String umwandeln und in dem String passwortwh speichern
        			
        			if(name.equals("") || alias.equals(null) 
        					|| passwort.equals("") || passwort.equals(null)){									//Prüfen ob name oder passwort leer sind
        				Toast.makeText(RegistrationActivity.this, "Es werden alle Eingaben Benötigt", 
        						Toast.LENGTH_LONG).show();
        			}else{
        				if(passwortwh.equals(passwort)){														//Prüfen ob passwortwh passwort entspricht
    						try{
    							user = UserServices.getUserByAccountName(name);
    							if (user.getAccountName() == null) { 											//Wenn loginUsername null ist, kommt die Fehlermeldung
    								md5Pass = md5Generator.getMd5(passwort);
    								user = UserServices.createUser(name, md5Pass);
    								
    								Intent i = new Intent(RegistrationActivity.this, 							//Sonst wird die nächste Activity "ContactListActivity" gestartet
    										ContactListActivity.class);
    								Bundle b = new Bundle();
    								b.putInt("userId", user.getAccountID());
    								b.putString("aliasName", user.getAlias());
    								b.putString("accountName", user.getAccountName());
    								b.putString("password", user.getPassword());
    								i.putExtras(b);
    								startActivity(i);
    								finish();
    								
    							} else {
    								Toast.makeText(RegistrationActivity.this,
    										"Der Benutzername ist bereits vergeben!",
    										Toast.LENGTH_LONG).show();
    							}
    							
							} catch (Exception e) {
								e.printStackTrace();
							}
        				}
        				else{
        					Toast.makeText(RegistrationActivity.this, "Passwörter stimmen nicht überein", 		
        							Toast.LENGTH_LONG).show();
        				}
        			} 
        			}       					
        	}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {																//Menü wird aufgebaut
        return true;																							//Wenn Menü aufgebaut ist, gibt die Methode true zurück
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {														//Wird ein Item im Menüe ausgewählt, gibt die Methode true zurück
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
