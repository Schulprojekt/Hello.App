package com.Schulprojekt.helloprojekt;
import com.Schulprojekt.helloprojekt.GUILogik.User;
import com.Schulprojekt.helloprojekt.Spiele.HangmanActivity;
import com.Schulprojekt.helloprojekt.Spiele.TicTacToeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectGameActivity extends Activity {
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
				Intent i = new Intent(SelectGameActivity.this,													//Aufbau des Pfades zur n�chsten Activity
		        		HangmanActivity.class);													
				Bundle b = new Bundle();																		//Erstellen eines Bundles
				b.putString("loggedUser", loggedUser);							    							//F�llen des Bundles mit Key und dem dazugeh�rigen Wert
				b.putString("chatPartner", chatPartner);
				i.putExtras(b);																					//Bundle ins Intent hinzuf�gen
				startActivity(i);																				//Activity wird gestartet
			}
		});
		
		buttonTicTacToe.setOnClickListener(new OnClickListener() {												//auf den Button TicTacToe einen OnClickListenener setzen
			public void onClick(View view) {
				Intent i = new Intent(SelectGameActivity.this,													//Aufbau des Pfades zur n�chsten Activity
		        		TicTacToeActivity.class);
		        startActivity(i);																				//Activity wird gestartet
			}
		});
		
		buttonBack.setOnClickListener(new OnClickListener() {													//auf den Button Zur�ck einen OnClickListenener setzen
			public void onClick(View view) {
				finish();																						//Methode finish() aufrufen
				System.exit(0);																					//Anwendung komplett schlie�en
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {																//Men� wird aufgebaut
		getMenuInflater().inflate(R.menu.select_game, menu);
		return true;																							//wenn Men� aufgebaut ist, gibt die Methode true zur�ck
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {														//wird ein Item im Men�e ausgew�hlt, gibt die Methode true zur�ck
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
