package com.Schulprojekt.helloprojekt;
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
	private Button buttonHangman, buttonTicTacToe, buttonBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_game);
		buttonHangman = (Button) findViewById(R.id.buttonHangman);
		buttonTicTacToe = (Button) findViewById(R.id.buttonTicTacToe);
		buttonBack = (Button) findViewById(R.id.buttonBack);
		buttonHangman.setOnClickListener(new OnClickListener() {												//auf den Button Hangman einen OnClickListenener setzen
			public void onClick(View view) {
				Intent i = new Intent(SelectGameActivity.this,
		        		HangmanActivity.class);
		        startActivity(i);																							//wird auf die nächste Activity geleitet
			}
		});
		buttonTicTacToe.setOnClickListener(new OnClickListener() {												//auf den Button TicTacToe einen OnClickListenener setzen
			public void onClick(View view) {
				Intent i = new Intent(SelectGameActivity.this,
		        		TicTacToeActivity.class);
		        startActivity(i);																				//wird auf die nächste Activity geleitet
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
