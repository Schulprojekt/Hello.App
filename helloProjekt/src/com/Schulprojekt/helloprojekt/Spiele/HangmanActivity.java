package com.Schulprojekt.helloprojekt.Spiele;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Schulprojekt.helloprojekt.R;

public class HangmanActivity extends Activity {

	Button btn_beenden;
	Button btn_senden;
	EditText txt_eingabe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hangman);
		btn_beenden = (Button) findViewById(R.id.btn_beenden);
		btn_beenden.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				System.exit(0);
			}
		});
		btn_senden = (Button) findViewById(R.id.btn_senden);
		btn_senden.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String eingabe = gibWort();
				eingabe.trim();
				if (!eingabe.equalsIgnoreCase("")
						&& !eingabe.equalsIgnoreCase(null)) {
					if (eingabe.length() <= 12){
						
					if (isAlpha(eingabe) == true) {
						Intent i = new Intent(HangmanActivity.this,
								MainHangmanActivity.class);
						Bundle b = new Bundle();
						b.putString("wort", eingabe.toUpperCase());
						i.putExtras(b);
						startActivity(i);
						finish();
						System.exit(0);
					}

					 else{
						 Toast.makeText(HangmanActivity.this, "Keine Zahlen und Sonderzeichen!", Toast.LENGTH_LONG).show();
					 }
					}else{
						Toast.makeText(HangmanActivity.this, "Nicht länger als 12 Zeichen!", Toast.LENGTH_LONG).show();
					}
				}
				 else{		
					 Toast.makeText(HangmanActivity.this, "Das Eingabefeld darf nicht leer sein", Toast.LENGTH_LONG).show();
				 }

			}
		});
		txt_eingabe = (EditText) findViewById(R.id.txt_eingabe);
		txt_eingabe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				txt_eingabe.setText("");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hangman, menu);
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

	public String gibWort() {
		String wort = "";
		txt_eingabe = (EditText) findViewById(R.id.txt_eingabe);
		wort = txt_eingabe.getText().toString();
		return wort;

	}

	public boolean isAlpha(String text) {
		if (text.matches("[a-zA-Z]*")) {
			return true;
		} else {
			return false;
		}
	}
}
