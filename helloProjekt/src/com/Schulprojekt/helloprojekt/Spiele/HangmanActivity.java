package com.Schulprojekt.helloprojekt.Spiele;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
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
import com.Schulprojekt.helloprojekt.GUILogik.User;
import com.google.gson.Gson;


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
			public void onClick(View v) {																  	//Beim Klick auf den Button Aktion ausführen
				finish();																					//Activity beenden
				System.exit(0);																				//Activity schließen
			}
		});
		
		btn_senden = (Button) findViewById(R.id.btn_senden);												//Zugriff auf den Button "Senden"
		btn_senden.setOnClickListener(new OnClickListener() {												//Methodenaufruf

			@Override
			public void onClick(View v) {																	//Beim Klick auf den Button Aktion ausführen
				String eingabe = gibWort();																	//Aufruf der Methode gibWort()
				eingabe.trim();																				//Leerstrings entfernen
				if (!eingabe.equalsIgnoreCase("")															//Prüfung ob das Feld Eingabe leer ist
						&& !eingabe.equalsIgnoreCase(null)) {
					if (eingabe.length() <= 12){															//Prüfung ob das Feld mehr als 12 Zeichen hat
						
					if (isAlpha(eingabe) == true) {															//Prüfung ob das Feld nur Buchstaben enthält
						Intent i = new Intent(HangmanActivity.this,											//Aufbau des Pfades zur nächsten Activity
								MainHangmanActivity.class);													
						Bundle b = new Bundle();															//Erstellen eines Bundles
						b.putString("wort", eingabe.toUpperCase());											//Füllen des Bundles mit Key und dem dazugehörigen Wert
						i.putExtras(b);																		//Bundle ins Intent hinzufügen
						startActivity(i);																	//Nächste Activity starten
						finish();																			//Activity beenden	
						System.exit(0);																		//Activity schliessen
					}

					 else{
						 Toast.makeText(HangmanActivity.this, "Keine Zahlen und Sonderzeichen!", 			//Wenn die Eingabe Zahlen oder Sonderzeichen enthält, Fehlermeldung anzeigen
								 Toast.LENGTH_LONG).show();
					 }
					}else{
						Toast.makeText(HangmanActivity.this, "Nicht länger als 12 Zeichen!", 				//Wenn die Eingabe mehr als 12 Zeichen hat, Fehlermeldung anzeigen
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
			public void onClick(View v) {																	//Beim Klick auf das Textfeld Aktion ausführen
				txt_eingabe.setText("");																	//Beim Klick auf das Textfeld Inhalt löschen
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {															//Aufbau eines Menüs
		getMenuInflater().inflate(R.menu.hangman, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {													//Wird ein Item im Menü ausgewählt, wirft die Methode true zurück
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public String gibWort() {																				//Methode zum Erhalten der Eingabe
		String wort = "";																					//Deklaration
		txt_eingabe = (EditText) findViewById(R.id.txt_eingabe);											//Zugriff auf den Textfeld "Eingabe"
		wort = txt_eingabe.getText().toString();															//Füllen der Variable "Wort"
		return wort;																						//Rückgabewert
	}

	public boolean isAlpha(String text) {																	//Methode zur Prüfung ob die Eingabe nur Buchstaben enthält
		if (text.matches("[a-zA-Z]*")) {																	//Prüfung mit RegEx
			return true;
		} else {
			return false;
		}
	}
	
	public void startConnection(){
		
		Bundle bundle = getIntent().getExtras();	
		String loggedUser = bundle.getString("loggedUser");
		String chatPartner = bundle.getString("chatPartner");
		User user = new User();
		User user2 = new User();
		
		DefaultHttpClient httpClient = new DefaultHttpClient();								      			//Client erstellen
		
		Gson gson = new Gson();
		String jsonString = "";
		jsonString = gson.toJson(loggedUser);
		StringEntity se;
		try {
			se = new StringEntity(jsonString);
	
		HttpPost request = new HttpPost(SERVICE_URI+ "/GetUserByAccountName");   							//Auf die Felder AccountName 
		request.setEntity(se);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json");
		HttpResponse response;
		
			response = httpClient.execute(request);
			HttpEntity responseEntity = response.getEntity();
			char[] buffer = new char[(int) responseEntity
					.getContentLength()]; 																	//Daten im Array speichern
			InputStream stream = responseEntity.getContent();
			user = gson.fromJson(stream.toString(), User.class);
			InputStreamReader reader = new InputStreamReader(stream); 										//Reader deklarieren
			reader.read(buffer); 																			//Reader liest Buffer
			stream.close();

			
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Gson gson2 = new Gson();
		String jsonString2 = "";
		jsonString2 = gson.toJson(chatPartner);
		StringEntity se2;
		try {
			se2 = new StringEntity(jsonString);
	
		HttpPost request2 = new HttpPost(SERVICE_URI+ "/GetUserByAccountName" );  							//Auf die Felder AccountName 
		request2.setEntity(se2);
		request2.setHeader("Accept", "application/json");
		request2.setHeader("Content-type", "application/json");
		HttpResponse response2;
			response2 = httpClient.execute(request2);
			HttpEntity responseEntity = response2.getEntity();
			char[] buffer = new char[(int) responseEntity
					.getContentLength()]; 																	//Daten im Array speichern
			InputStream stream2 = responseEntity.getContent();
			user2= gson2.fromJson(stream2.toString(), User.class);
			InputStreamReader reader = new InputStreamReader(stream2); 										//Reader deklarieren
			reader.read(buffer); 																			//Reader liest Buffer
			stream2.close();

			
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
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
			                .key("sender").value(user)
			                .key("reciever").value(user2)
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
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		}
}

