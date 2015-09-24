package com.Schulprojekt.helloprojekt.Spiele;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Schulprojekt.helloprojekt.R;

public class HangmanActivity extends Activity {
	private final static String SERVICE_URI = "http://hello-server/helloservice/messengerservice.svc";
																										    //Deklaration
	Button btn_beenden;
	Button btn_senden;
	EditText txt_eingabe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {                                                    //Activity wird aufgebaut
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hangman);
		
		btn_beenden = (Button) findViewById(R.id.btn_beenden);                                             	//Zugriff auf den Button "Beenden"
		btn_beenden.setOnClickListener(new OnClickListener() {												//Methodenaufruf

			@Override
			public void onClick(View v) {																  	//Beim Klick auf den Button Aktion ausf�hren
				finish();																					//Activity beenden
				System.exit(0);																				//Activity schlie�en
			}
		});
		
		btn_senden = (Button) findViewById(R.id.btn_senden);												//Zugriff auf den Button "Senden"
		btn_senden.setOnClickListener(new OnClickListener() {												//Methodenaufruf

			@Override
			public void onClick(View v) {																	//Beim Klick auf den Button Aktion ausf�hren
				String eingabe = gibWort();																	//Aufruf der Methode gibWort()
				eingabe.trim();																				//Leerstrings entfernen
				if (!eingabe.equalsIgnoreCase("")															//Pr�fung ob das Feld Eingabe leer ist
						&& !eingabe.equalsIgnoreCase(null)) {
					if (eingabe.length() <= 12){															//Pr�fung ob das Feld mehr als 12 Zeichen hat
						
					if (isAlpha(eingabe) == true) {															//Pr�fung ob das Feld nur Buchstaben enth�lt
						Intent i = new Intent(HangmanActivity.this,											//Aufbau des Pfades zur n�chsten Activity
								MainHangmanActivity.class);													
						Bundle b = new Bundle();															//Erstellen eines Bundles
						b.putString("wort", eingabe.toUpperCase());											//F�llen des Bundles mit Key und dem dazugeh�rigen Wert
						i.putExtras(b);																		//Bundle ins Intent hinzuf�gen
						startActivity(i);																	//N�chste Activity starten
						finish();																			//Activity beenden	
						System.exit(0);																		//Activity schliessen
					}

					 else{
						 Toast.makeText(HangmanActivity.this, "Keine Zahlen und Sonderzeichen!", 			//Wenn die Eingabe Zahlen oder Sonderzeichen enth�lt, Fehlermeldung anzeigen
								 Toast.LENGTH_LONG).show();
					 }
					}else{
						Toast.makeText(HangmanActivity.this, "Nicht l�nger als 12 Zeichen!", 				//Wenn die Eingabe mehr als 12 Zeichen hat, Fehlermeldung anzeigen
								Toast.LENGTH_LONG).show();
					}
				}
				 else{		
					 Toast.makeText(HangmanActivity.this, "Das Eingabefeld darf nicht leer sein", 			//Wenn die Eingabe leer ist, Fehlermeldung anzeigen
							 Toast.LENGTH_LONG).show();
				 }

			}
		});
		
		txt_eingabe = (EditText) findViewById(R.id.txt_eingabe);											//Zugriff auf den Textfeld "Eingabe"
		txt_eingabe.setOnClickListener(new OnClickListener() {												//Methodenaufruf

			@Override
			public void onClick(View v) {																	//Beim Klick auf das Textfeld Aktion ausf�hren
				txt_eingabe.setText("");																	//Beim Klick auf das Textfeld Inhalt l�schen
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {															//Aufbau eines Men�s
		getMenuInflater().inflate(R.menu.hangman, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {													//Wird ein Item im Men� ausgew�hlt, wirft die Methode true zur�ck
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public String gibWort() {																				//Methode zum Erhalten der Eingabe
		String wort = "";																					//Deklaration
		txt_eingabe = (EditText) findViewById(R.id.txt_eingabe);											//Zugriff auf den Textfeld "Eingabe"
		wort = txt_eingabe.getText().toString();															//F�llen der Variable "Wort"
		return wort;																						//R�ckgabewert

	}

	public boolean isAlpha(String text) {																	//Methode zur Pr�fung ob die Eingabe nur Buchstaben enth�lt
		if (text.matches("[a-zA-Z]*")) {																	//Pr�fung mit RegEx
			return true;
		} else {
			return false;
		}
	}
	
	public void startConnection(){
		
		Bundle bundle = getIntent().getExtras();	
		String loggedUser = bundle.getString("loggedUser");
		String chatPartner = bundle.getString("chatPartner");
		JSONObject user = new JSONObject();
		JSONObject user2 = new JSONObject();
		
		DefaultHttpClient httpClient = new DefaultHttpClient();								      			//Client erstellen

		
		HttpGet request = new HttpGet(SERVICE_URI+ "/GetUserByAccountName/" +   							// auf die Felder AccountName + loginUsernamezugreifen
				loggedUser);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");
		HttpResponse response;
		try {
			response = httpClient.execute(request);
			HttpEntity responseEntity = response.getEntity();
			char[] buffer = new char[(int) responseEntity
					.getContentLength()]; 																	// Daten im Array speichern
			InputStream stream = responseEntity.getContent();
			InputStreamReader reader = new InputStreamReader(stream); 										// Reader deklarieren
			reader.read(buffer); 																			// Reader liest Buffer
			stream.close();

			user = new JSONObject(new String(buffer)); 														// ein JSONObject erstellens
			
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		HttpGet request2 = new HttpGet(SERVICE_URI+ "/GetUserByAccountName/" +   							// auf die Felder AccountName + loginUsernamezugreifen
				chatPartner);
		request2.setHeader("Accept", "application/json");
		request2.setHeader("Content-type", "application/json");
		HttpResponse response2;
		try {
			response2 = httpClient.execute(request2);
			HttpEntity responseEntity = response2.getEntity();
			char[] buffer = new char[(int) responseEntity
					.getContentLength()]; 																	// Daten im Array speichern
			InputStream stream = responseEntity.getContent();
			InputStreamReader reader = new InputStreamReader(stream); 										// Reader deklarieren
			reader.read(buffer); 																			// Reader liest Buffer
			stream.close();

			user2 = new JSONObject(new String(buffer)); 													// ein JSONObject erstellens
			
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		HttpPost request3 = new HttpPost(SERVICE_URI + "/CreateMessage");
        request3.setHeader("Accept", "application/json");
        request3.setHeader("Content-type", "application/json");

        // Build JSON string
        JSONStringer message;
		try {
			message = new JSONStringer()
			    .object()
			        .key("message")
			            .object()
			                .key("id").value(null)
			                .key("sender").value(user.getString("GUID"))
			                .key("reciever").value(user2.getString("GUID"))
			                .key("message").value("")
			                .key("attchment").value(new byte[]{0})
			                .key("timestamp").value(new Timestamp(System.currentTimeMillis()))
			            .endObject()
			        .endObject();
        StringEntity entity = new StringEntity(message.toString());

        request3.setEntity(entity);

        // Send request to WCF service
        DefaultHttpClient httpClient2 = new DefaultHttpClient();
        HttpResponse response3 = httpClient2.execute(request3);
        
        Log.d("WebInvoke", "Saving : " + response3.getStatusLine().getStatusCode());

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		}
	
}

