package com.Schulprojekt.helloprojekt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;


public class RegistrationActivity extends Activity {
	public Button bregistrieren;		//Deklaration
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
        
        bregistrieren = (Button) findViewById(R.id.registrieren);												//auf Button register zurgreifen
        bregistrieren.setOnClickListener(new OnClickListener() {												//auf den Button register einen OnClickListener setzen
        	public void onClick(View view) {
        		if(view == bregistrieren) {
        			tvname = (EditText) findViewById(R.id.username);											//auf Textfeld username zugreifen
        			tvalias = (EditText) findViewById(R.id.username);											//auf Textfeld username zugreifen - bei der Registrierung entspricht der Alias dem Username
        			tvpasswort = (EditText) findViewById(R.id.passwort);										//auf Textfeld passwort zugreifen
        			tvpasswortwh = (EditText) findViewById(R.id.passwortwh);									//auf Textfeld passwortwh zugreifen
        			String name = tvname.getText().toString();													//tvname in einen String umwandeln und in dem String name speichern
        			String alias = tvalias.getText().toString();												//tvalias in einen String umwandeln und in dem String alias speichern
        			String passwort = tvpasswort.getText().toString();											//tvpasswort in einen String umwandeln und in dem String passwort speichern
        			String passwortwh = tvpasswortwh.getText().toString();										//tvpasswortwh in einen String umwandeln und in dem String passwortwh speichern
        			
        			
        			// TODO alias muss beim create noch auf client oder serverseite gleich dem accountName gesetzt werden!
        			
        			
        			if(name.equals("") || alias.equals(null) 
        					|| passwort.equals("") || passwort.equals(null)){									//prüfen ob name oder passwort leer sind
        				Toast.makeText(RegistrationActivity.this, "Es werden alle Eingaben Benötigt", 
        						Toast.LENGTH_LONG).show();
        			}else{
        				if(passwortwh.equals(passwort)){														//prüfen ob passwortwh passwort entspricht
    						try{
    							user = UserServices.getUserByAccountName(name);
    							if (user.getAccountName() == null) { 											// ist loginUsername null, kommt die Fehlermeldung
    								md5Pass = md5Generator.getMd5(passwort);
    								user = UserServices.createUser(name, md5Pass);
    								
    								Intent i = new Intent(RegistrationActivity.this, 						// sonst wird die nächste Activity ContactListActivity gestartet
    										ContactListActivity.class);
    								Bundle b = new Bundle();
    								b.putInt("userId", user.getAccountID());
    								b.putString("aliasName", user.getAlias());
    								b.putString("accountName", user.getAccountName());
//    								
//    								Bitmap bmp = ((BitmapDrawable)d).getBitmap();
//    								ByteArrayOutputStream baos = new ByteArrayOutputStream();
//    								bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
//    								byte[] byteArray = baos.toByteArray();
//    								
//    								b.putByteArray("picture", byteArray);
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
        //getMenuInflater().inflate(R.menu.registration, menu);
        return true;																							//wenn Menü aufgebaut ist, gibt die Methode true zurück
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {														//wird ein Item im Menüe ausgewählt, gibt die Methode true zurück
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    

    

}
