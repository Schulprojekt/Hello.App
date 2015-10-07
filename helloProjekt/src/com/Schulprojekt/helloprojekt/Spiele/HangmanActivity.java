package com.Schulprojekt.helloprojekt.Spiele;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Schulprojekt.helloprojekt.R;
import com.Schulprojekt.helloprojekt.GUILogik.Message;
import com.Schulprojekt.helloprojekt.GUILogik.MessageServices;


public class HangmanActivity extends Activity {
																										    //Deklaration
	Button btn_beenden;
	Button btn_senden;
	EditText txt_eingabe;
	String eingabe;

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
				eingabe = gibWort();																	//Aufruf der Methode gibWort()
				eingabe.trim();																				//Leerstrings entfernen
				if (!eingabe.equalsIgnoreCase("")															//Pr�fung ob das Feld Eingabe leer ist
						&& !eingabe.equalsIgnoreCase(null)) {
					if (eingabe.length() <= 12){															//Pr�fung ob das Feld mehr als 12 Zeichen hat
						
					if (isAlpha(eingabe) == true) {															//Pr�fung ob das Feld nur Buchstaben enth�lt
						startConnection();
//						Intent i = new Intent(HangmanActivity.this,											//Aufbau des Pfades zur n�chsten Activity
//								MainHangmanActivity.class);													
//						Bundle b = new Bundle();															//Erstellen eines Bundles
//						b.putString("wort", eingabe.toUpperCase());											//F�llen des Bundles mit Key und dem dazugeh�rigen Wert
//						i.putExtras(b);																		//Bundle ins Intent hinzuf�gen
//						startActivity(i);																	//N�chste Activity starten
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
		int loggedUser = bundle.getInt("loggedUser");
		int chatPartner = bundle.getInt("chatPartner");
		

		Message message = new Message(chatPartner, loggedUser, "hangman123:"+eingabe);
		MessageServices.createMessage(message);

		}
}

