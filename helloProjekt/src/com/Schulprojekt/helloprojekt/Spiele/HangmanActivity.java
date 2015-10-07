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
			public void onClick(View v) {																  	//Beim Klick auf den Button Aktion ausführen
				finish();																					//Activity beenden
				System.exit(0);																				//Activity schließen
			}
		});
		
		btn_senden = (Button) findViewById(R.id.btn_senden);												//Zugriff auf den Button "Senden"
		btn_senden.setOnClickListener(new OnClickListener() {												//Methodenaufruf

			@Override
			public void onClick(View v) {																	//Beim Klick auf den Button Aktion ausführen
				eingabe = gibWort();																	//Aufruf der Methode gibWort()
				eingabe.trim();																				//Leerstrings entfernen
				if (!eingabe.equalsIgnoreCase("")															//Prüfung ob das Feld Eingabe leer ist
						&& !eingabe.equalsIgnoreCase(null)) {
					if (eingabe.length() <= 12){															//Prüfung ob das Feld mehr als 12 Zeichen hat
						
					if (isAlpha(eingabe) == true) {															//Prüfung ob das Feld nur Buchstaben enthält
						startConnection();
//						Intent i = new Intent(HangmanActivity.this,											//Aufbau des Pfades zur nächsten Activity
//								MainHangmanActivity.class);													
//						Bundle b = new Bundle();															//Erstellen eines Bundles
//						b.putString("wort", eingabe.toUpperCase());											//Füllen des Bundles mit Key und dem dazugehörigen Wert
//						i.putExtras(b);																		//Bundle ins Intent hinzufügen
//						startActivity(i);																	//Nächste Activity starten
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
		int loggedUser = bundle.getInt("loggedUser");
		int chatPartner = bundle.getInt("chatPartner");
		

		Message message = new Message(chatPartner, loggedUser, "hangman123:"+eingabe);
		MessageServices.createMessage(message);

		}
}

