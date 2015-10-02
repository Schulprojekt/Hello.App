package com.Schulprojekt.helloprojekt;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.Schulprojekt.helloprojekt.GUILogik.Message;
import com.Schulprojekt.helloprojekt.GUILogik.User;
import com.Schulprojekt.helloprojekt.Spiele.HangmanActivity;
import com.Schulprojekt.helloprojekt.Spiele.TicTacToeActivity;
import com.google.gson.Gson;

public class SelectGameActivity extends Activity {
	private final static String SERVICE_URI = "http://hello-server/helloservice/messengerservice.svc";		//Pfad zur Datenbank
	private Button buttonHangman, buttonTicTacToe, buttonBack;													//Deklaration 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {														//Activity wird aufgebaut
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_game);
		buttonHangman = (Button) findViewById(R.id.buttonHangman);												//auf den Button buttonHangman zugreifen
		buttonTicTacToe = (Button) findViewById(R.id.buttonTicTacToe);											//auf den Button buttonTicTacToe zugreifen
		buttonBack = (Button) findViewById(R.id.buttonBack);													//auf den Button buttonBack zugreifen
		
		buttonHangman.setOnClickListener(new OnClickListener() {												//auf den Button Hangman einen OnClickListenener setzen
			public void onClick(View view) {
				Bundle bundle = getIntent().getExtras();	
				String loggedUser = bundle.getString("loggedUser");
				String chatPartner = bundle.getString("chatPartner");
				Intent i = new Intent(SelectGameActivity.this,													//Aufbau des Pfades zur nächsten Activity
		        		HangmanActivity.class);													
				Bundle b = new Bundle();																		//Erstellen eines Bundles
				b.putString("loggedUser", loggedUser);							    							//Füllen des Bundles mit Key und dem dazugehörigen Wert
				b.putString("chatPartner", chatPartner);
				i.putExtras(b);																					//Bundle ins Intent hinzufügen
				startActivity(i);																				//Activity wird gestartet
			}
		});
		
		buttonTicTacToe.setOnClickListener(new OnClickListener() {												//auf den Button TicTacToe einen OnClickListenener setzen
			public void onClick(View view) {
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
			
				HttpPost request = new HttpPost(SERVICE_URI+ "/GetUserByAccountName");   							//Aufruf der Methode GetUserByAccountName
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
			
				HttpPost request2 = new HttpPost(SERVICE_URI+ "/GetUserByAccountName" );  							//Aufruf der Methode GetUserByAccountName
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
			
				try {
				Gson gson3 = new Gson();
				String JsonString3;
				Message message = new Message(user2.getAccountID(), user.getAccountID(), "tictactoe123:start");
				JsonString3 = gson3.toJson(message);
				DefaultHttpClient httpClient3 = new DefaultHttpClient();
				HttpPost request3 = new HttpPost(SERVICE_URI + "/CreateMessage");									//Aufruf der Methode CreateMessage
				request3.setHeader("Accept", "application/json");
		        request3.setHeader("Content-type", "application/json");
		        StringEntity se3 = new StringEntity(JsonString3);
		        request3.setEntity(se3);
		        HttpResponse response3 = httpClient3.execute(request3);
		        
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		buttonBack.setOnClickListener(new OnClickListener() {													//auf den Button Zurück einen OnClickListenener setzen
			public void onClick(View view) {
				finish();																						//Methode finish() aufrufen
				System.exit(0);																					//Anwendung komplett schließen
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {																//Menü wird aufgebaut
		getMenuInflater().inflate(R.menu.select_game, menu);
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
