package com.Schulprojekt.helloprojekt.Spiele;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.Schulprojekt.helloprojekt.R;

public class SolutionActivity extends Activity {

	Button btn_ende;																						//Deklaration
	TextView txt_field;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {													//Activity wird aufgebaut
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solution);
		
		Bundle bu = getIntent().getExtras();																//Zugriff auf den �bergabewert von MainHangmanActivity
		int wert = bu.getInt("game");																		//F�llen der Variable mit dem �bergabewert
		
		if (wert > 0){																						//Pr�fung ob Wert gr��er 0 ist
			txt_field = (TextView) findViewById(R.id.txt_field);											//Zugriff auf das Textfeld
			txt_field.setText("Sie haben leider das Spiel verloren!");										//Textfeld setzen
		} else {
			txt_field = (TextView) findViewById(R.id.txt_field);											//Zugriff auf das Textfeld
			txt_field.setText("Herzlichen Gl�ckwunsch! Sie haben das Spiel gewonnen!");						//Textfeld setzen
		}
		
		btn_ende = (Button) findViewById(R.id.btn_ende);													//Zugriff auf den Button "Beenden"
		btn_ende.setOnClickListener(new OnClickListener() {													//Methodenaufruf

			@Override
			public void onClick(View v) {																	//Beim Klick auf den Button Aktion ausf�hren
				finish();																					//Activity beenden
				System.exit(0);																				//Activity schliessen
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {															//Aufbau eines Men�s
		getMenuInflater().inflate(R.menu.solution, menu);
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
}



