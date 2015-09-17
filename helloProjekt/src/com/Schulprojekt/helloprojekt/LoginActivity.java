package com.Schulprojekt.helloprojekt;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends Activity{
	public Button btnRegistration;																				//Deklaration
	public Button btnLogin;
	public ImageView imageView;
	public EditText loginUsername;
	public EditText loginPassword;																										
	private final static String SERVICE_URI = "http://muss.noch.geaendert.werden";								//URL zum WebService
	@Override
	protected void onCreate(Bundle savedInstanceState) {														//Activity wird aufgebaut
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		loginUsername = (EditText)findViewById(R.id.loginUsername);												//auf Textfeld loginUsername zurgreifen
		loginPassword = (EditText)findViewById(R.id.loginPassword);												//auf Textfeld loginPassword zurgreifen
		btnRegistration = (Button) findViewById(R.id.register);													//auf Button register zurgreifen
		btnLogin = (Button) findViewById(R.id.login);															//auf Button login zugreifen
		imageView = (ImageView) findViewById(R.id.imageView);													//auf ImageView imageView zugreifen
		btnRegistration.setOnClickListener(new OnClickListener() {												//auf den Button register einen OnClickListener setzen
			@Override
			public void onClick(View v) {																		
				if (v == btnRegistration){
					startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));					//auf RegistrationActivity weiterleiten, wenn Button geklickt wird
				}
				
			}
		});
		btnLogin.setOnClickListener(new OnClickListener() {														//auf den Button login einen OnClickListener setzen
			@Override
			public void onClick(View v) {
				if (v == btnLogin){																				//wird der Button geklickt, wird die Verbindung zur Datenbank aufgebaut
					try{
						DefaultHttpClient httpClient = new DefaultHttpClient();
						HttpGet request = new HttpGet(SERVICE_URI + "/GetUserByAccountName/" +					//auf die Felder AccountName + loginUsername zugreifen
						loginUsername);
						request.setHeader("Accept", "application/json");
				        request.setHeader("Content-type", "application/json");
				        HttpResponse response = httpClient.execute(request);
				        HttpEntity responseEntity = response.getEntity();
				        char[] buffer = new char[(int)responseEntity.getContentLength()];						//Daten im Array speichern
				        InputStream stream = responseEntity.getContent();
				        InputStreamReader reader = new InputStreamReader(stream);								//Reader deklarieren
				        reader.read(buffer);																	//Reader liest Buffer
				        stream.close();

				        JSONObject user = new JSONObject(new String(buffer));									//ein JSONObject erstellens
				        loginUsername.setText(user.getString("accountName"));									//in das Feld loginUsername die Eingabe accountName setzen
				        if(loginUsername == null){																//ist loginUsername nulll, kommt die Fehlermeldung
				        	Toast.makeText(LoginActivity.this, "Benutzername oder Passwort falsch!", 
				        			Toast.LENGTH_LONG).show();
				        }else{
				        	startActivity(new Intent(LoginActivity.this, ContactListActivity.class));			//sonst wird die nächste Activity ContactListActivity gestartet
				        } 
					}catch (Exception e){
						e.printStackTrace();
					}
					Boolean methode = true;
					if(methode){
						startActivity(new Intent(LoginActivity.this, ContactListActivity.class));					
					}else{
						Toast.makeText(LoginActivity.this, "Benutzername oder Passwort falsch!", 
								Toast.LENGTH_LONG).show();
					}
				}
				
			}
		});
		imageView.setOnClickListener(new OnClickListener() {													//auf das ImageView imageView einen OnClickListener setzen
			@Override
			public void onClick(View v) {					
				if (v == imageView){																			//wird imageView geklickt, wird der Boolean auf false gesetzt
					Boolean methode = false;
					
					if(methode){																				//wurde der Boolean methode erzeugt, wird die nächste Activity ContactListActivity aufgerufen
						startActivity(new Intent(LoginActivity.this, ContactListActivity.class));					
					}else{																						//sonst kommt die Fehlermeldung
						Toast.makeText(LoginActivity.this, "Benutzername oder Passwort falsch!", 
								Toast.LENGTH_LONG).show();
					}
				}
				
			}
		});
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {																//Menü wird aufgebaut
		getMenuInflater().inflate(R.menu.login, menu);
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
	public void onClickListener(){
		
	}
}
