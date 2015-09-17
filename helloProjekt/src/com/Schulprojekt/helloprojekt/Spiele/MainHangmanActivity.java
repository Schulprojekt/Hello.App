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
import android.widget.TextView;

import com.Schulprojekt.helloprojekt.R;

public class MainHangmanActivity extends Activity {
																										    //Deklaration
	Button btn_schliessen;
	Button btn_loesen;
	Button btn_a;
	Button btn_b;
	Button btn_c;
	Button btn_d;
	Button btn_e;
	Button btn_f;
	Button btn_g;
	Button btn_h;
	Button btn_i;
	Button btn_j;
	Button btn_k;
	Button btn_l;
	Button btn_m;
	Button btn_n;
	Button btn_o;
	Button btn_p;
	Button btn_q;
	Button btn_r;
	Button btn_s;
	Button btn_t;
	Button btn_u;
	Button btn_v;
	Button btn_w;
	Button btn_x;
	Button btn_y;
	Button btn_z;
	EditText txt_loese;
	TextView txt_wort;
	TextView txt_versuche;
	int tries = 5;
	int win = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {													//Activity wird aufgebaut
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_hangman);
		
		txt_versuche = (TextView) findViewById(R.id.txt_versuche);											//Zugriff auf das Label "Versuche"
		txt_versuche.setText(txt_versuche.getText().toString() + tries +" ");								//Setzen des Labels
		
		txt_wort = (TextView) findViewById(R.id.txt_wort);													//Zugriff auf das Label "Wort"
		Bundle b = getIntent().getExtras();																	//Zugriff auf den �bergabewert von HangmanActivity
		String suchwort = b.getString("wort");																//F�llen der Variable mit dem �bergabewert
		String wort = "";																					//Deklaration
		for (int i = 1; i <= suchwort.length(); i++) {														//Schleife um f�r jeden Buchstaben im Wort einen Unterstrich zu setzen
			if (i == suchwort.length()) {																	//Pr�fung ob beim letzten Buchstaben angekommen
				wort += ("_");																				//Nur Unterstrich setzen
			} else {
				wort += ("_ ");																				//Sonst Unterstrich und Leerstring setzen
			}
		}

		txt_wort.setText(wort);																				//Label "Wort" neu setzen

		btn_schliessen = (Button) findViewById(R.id.btn_schliessen);										//Zugriff auf den Button "Schliessen"
		btn_schliessen.setOnClickListener(new OnClickListener() {											//Methodenaufruf

			@Override
			public void onClick(View v) {																	//Beim Klick auf den Button Aktion ausf�hren
				finish();																					//Activity beenden
				System.exit(0);																				//Activity schliessen
			}
		});

		btn_loesen = (Button) findViewById(R.id.btn_loesen);												//Zugriff auf den Button "L�sen"
		btn_loesen.setOnClickListener(new OnClickListener() {												//Methodenaufruf

			@Override
			public void onClick(View v) {																	//Beim Klick des Button Aktion ausf�hren
				txt_loese = (EditText) findViewById(R.id.txt_loese);

				if (btn_loesen.getText().toString().equalsIgnoreCase("OK")) {								//Pr�fung auf den Textinhalt des "L�sen"-Button
					
					txt_loese.setOnClickListener(new OnClickListener() {									//Methodenaufruf

						@Override
						public void onClick(View v) {														//Beim Klick des Textfeldes Aktion ausf�hren
							txt_loese.setText("");															//Textinhalt l�schen
						}
					});

					if (!txt_loese.getText().toString()														//Pr�fung ob das L�sungsfeld gef�llt ist
							.equalsIgnoreCase("L�sungswort eingeben")
							|| !txt_loese.getText().toString()
									.equalsIgnoreCase("")
							|| !txt_loese.getText().toString()
									.equalsIgnoreCase(null)) {
						Bundle b = getIntent().getExtras();													//Zugriff auf den �bergabewert von HangmanActivity
						String suchwort = b.getString("wort");												//F�llen der Variable mit dem �bergabewert
						if (txt_loese.getText().toString()													//Pr�fung ob das L�sungswort mit dem gesuchten Wort �bereinstimmt
								.equalsIgnoreCase(suchwort)) {
							win = 0;																		//Variable "Win" auf 0 setzen
							Intent in = new Intent(MainHangmanActivity.this,								//Aufbau des Pfades zur n�chsten Activity
									SolutionActivity.class);
							Bundle bu = new Bundle();														//Erstellen eines Bundles
							bu.putInt("game", win);															//F�llen des Bundles mit Key und dem dazugeh�rigen Wert
							in.putExtras(bu);																//Bundle ins Intent hinzuf�gen
							startActivity(in);																//N�chste Activity starten
							finish();																		//Activity beenden
							System.exit(0);																	//Activity schliessen
						} else {
							win = 1;																		//Sonst Variable "Win" auf 1 setzen
							Intent in = new Intent(MainHangmanActivity.this,								//Aufbau des Pfades zur n�chsten Activity
									SolutionActivity.class);
							Bundle bu = new Bundle();														//Erstellen eines Bundles
							bu.putInt("game", win);															//F�llen des Bundles mit Key und dem dazugeh�rigen Wert
							in.putExtras(bu);																//Bundle ins Intent hinzuf�gen
							startActivity(in);																//N�chste Activity starten
							finish();																		//Activity beenden
							System.exit(0);																	//Activity schliessen													
						}
					}

				} else {
					txt_loese.setVisibility(1);																//Textfeld "L�se" sichtbar machen
					btn_loesen.setText("OK");																//Buttontext auf "OK" setzen 
					txt_loese.setOnClickListener(new OnClickListener() {                                    //Methodenaufruf

						@Override
						public void onClick(View v) {														//Beim Klick auf das Textfeld Aktion ausf�hren
							txt_loese.setText("");															//Textinhalt l�schen
						}
					});
				}
			}
		});
																											//--Gilt f�r die Buttons a-z--
		btn_a = (Button) findViewById(R.id.btn_a);															//Zugriff auf die Buttons
		btn_a.setOnClickListener(new OnClickListener() {													//Methodenaufruf

			@Override																						
			public void onClick(View v) {																	//Beim Klick auf den Button Aktion ausf�hren
				checkLetter("A");																			//Methode mit dem Buchstaben des Button aufrufen
				btn_a.setVisibility(View.INVISIBLE);														//Button nicht sichtbar setzen
																											
			}
		});

		btn_b = (Button) findViewById(R.id.btn_b);
		btn_b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("B");
				btn_b.setVisibility(View.INVISIBLE);
			}
		});

		btn_c = (Button) findViewById(R.id.btn_c);
		btn_c.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("C");
				btn_c.setVisibility(View.INVISIBLE);
			}
		});

		btn_d = (Button) findViewById(R.id.btn_d);
		btn_d.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("D");
				
				btn_d.setVisibility(View.INVISIBLE);
			}
		});

		btn_e = (Button) findViewById(R.id.btn_e);
		btn_e.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("E");
		
				btn_e.setVisibility(View.INVISIBLE);
			}
		});

		btn_f = (Button) findViewById(R.id.btn_f);
		btn_f.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("F");
				
				btn_f.setVisibility(View.INVISIBLE);
			}
		});

		btn_g = (Button) findViewById(R.id.btn_g);
		btn_g.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("G");
				
				btn_g.setVisibility(View.INVISIBLE);
			}
		});

		btn_h = (Button) findViewById(R.id.btn_h);
		btn_h.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("H");
				btn_h.setVisibility(View.INVISIBLE);
			}
		});

		btn_i = (Button) findViewById(R.id.btn_i);
		btn_i.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("I");
				btn_i.setVisibility(View.INVISIBLE);
			}
		});

		btn_j = (Button) findViewById(R.id.btn_j);
		btn_j.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("J");
				btn_j.setVisibility(View.INVISIBLE);
			}
		});

		btn_k = (Button) findViewById(R.id.btn_k);
		btn_k.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("K");
				btn_k.setVisibility(View.INVISIBLE);
			}
		});

		btn_l = (Button) findViewById(R.id.btn_l);
		btn_l.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("L");
				btn_l.setVisibility(View.INVISIBLE);
			}
		});

		btn_m = (Button) findViewById(R.id.btn_m);
		btn_m.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("M");
				btn_m.setVisibility(View.INVISIBLE);
			}
		});

		btn_n = (Button) findViewById(R.id.btn_n);
		btn_n.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("N");
				btn_n.setVisibility(View.INVISIBLE);
			}
		});

		btn_o = (Button) findViewById(R.id.btn_o);
		btn_o.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("O");
				btn_o.setVisibility(View.INVISIBLE);
			}
		});

		btn_p = (Button) findViewById(R.id.btn_p);
		btn_p.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("P");
				btn_p.setVisibility(View.INVISIBLE);
			}
		});

		btn_q = (Button) findViewById(R.id.btn_q);
		btn_q.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("Q");
				btn_q.setVisibility(View.INVISIBLE);
			}
		});

		btn_r = (Button) findViewById(R.id.btn_r);
		btn_r.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("R");
				btn_r.setVisibility(View.INVISIBLE);
			}
		});

		btn_s = (Button) findViewById(R.id.btn_s);
		btn_s.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("S");
				btn_s.setVisibility(View.INVISIBLE);
			}
		});

		btn_t = (Button) findViewById(R.id.btn_t);
		btn_t.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("T");
				btn_t.setVisibility(View.INVISIBLE);
			}
		});

		btn_u = (Button) findViewById(R.id.btn_u);
		btn_u.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("U");
				btn_u.setVisibility(View.INVISIBLE);
			}
		});

		btn_v = (Button) findViewById(R.id.btn_v);
		btn_v.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("V");
				btn_v.setVisibility(View.INVISIBLE);
			}
		});

		btn_w = (Button) findViewById(R.id.btn_w);
		btn_w.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("W");
				btn_w.setVisibility(View.INVISIBLE);
			}
		});

		btn_x = (Button) findViewById(R.id.btn_x);
		btn_x.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("X");
				btn_x.setVisibility(View.INVISIBLE);
			}
		});

		btn_y = (Button) findViewById(R.id.btn_y);
		btn_y.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("Y");
				btn_y.setVisibility(View.INVISIBLE);
			}
		});

		btn_z = (Button) findViewById(R.id.btn_z);
		btn_z.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				checkLetter("Z");
				btn_z.setVisibility(View.INVISIBLE);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {															//Aufbau eines Men�s
		getMenuInflater().inflate(R.menu.main_hangman, menu);
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

	public void checkLetter(String letter) {																//Methode zur �berpr�fung ob der Buchstabe im Wort enthalten ist
		Bundle b = getIntent().getExtras();																	//Zugriff auf den �bergabewert von HangmanActivity
		String suchwort = b.getString("wort");																//F�llen der Variable mit dem �bergabewert
		String wort = "";																					//Deklaration
		int j = 0;									
		if (suchwort.contains(letter)) {																	//Pr�fung ob Buchstabe enthalten ist
			for (int i = 0; i < suchwort.length(); i++) {													//Schleife zu entsetzen der Unterstriche in Buchstaben
				if (txt_wort.getText().toString().charAt(j) == '_') {										//Pr�fung ob Unterstrich vorhanden ist
					if (suchwort.charAt(i) == letter.charAt(0)) {											//Pr�fung ob an der Stelle der Buchstabe ist
						if (i == suchwort.length()) {														//Pr�fung ob man am letzten Buchstaben des Wortes angelangt ist
							wort += (letter);																//Buchstaben setzen
						} else {
							wort += (letter + " ");															//Sonst Buchstaben und Leerstring setzen
						}
					} else {
						if (i == suchwort.length()) {														//Pr�fung ob man am letzten Buchstaben des Wortes angelangt ist										
							wort += ("_");																	//Unterstrich setzen
						} else {
							wort += ("_ ");																	//Sonst Unterstrich und Leerstring setzen
						}
					}
				}
				 else {
				  if (i == suchwort.length()) {																//Pr�fung ob man am letzten Buchstaben des Wortes angelangt ist														
				  wort += (suchwort.charAt(i));																//Buchstaben setzen
				  } else {
				  wort += (suchwort.charAt(i) + " ");														//Sonst Buchstaben und Leerstring setzen
				  }
				  }
				j = j + 2;																					//Variable j um 2 erh�hen
			}

			txt_wort.setText(wort);																			//Label "Wort" neu setzen
		} 
		else{
			tries = tries - 1;                                                                              //Wenn Buchstabe nicht enthalten, ein Versuch abziehen
			if (tries < 1){																					//Pr�fung ob Versuche kleiner 1 ist
				win = 1;																					//Variable "Win" auf 1 setzen
				Intent in = new Intent(MainHangmanActivity.this,											//Aufbau des Pfades zur n�chsten Activity
						SolutionActivity.class);
				Bundle bu = new Bundle();																	//Erstellen eines Bundles
				bu.putInt("game", win);																		//F�llen des Bundles mit Key und dem dazugeh�rigen Wert
				in.putExtras(bu);																			//Bundle ins Intent hinzuf�gen
				startActivity(in);																			//N�chste Activity starten
				finish();																					//Activity beenden	
				System.exit(0);																				//Activity schliessen

			}
			txt_versuche.setText(txt_versuche.getText().toString().substring(0,11) + tries +" ");           //Label "Versuche" neu setzen
		}
		if (!txt_wort.getText().toString().contains("_")){													//Pr�fun ob alle Buchstaben gefunden worden sind
			win = 0;																						//Variable "Win" auf 0 setzen
			Intent in = new Intent(MainHangmanActivity.this,												//Aufbau des Pfades zur n�chsten Activity
					SolutionActivity.class);
			Bundle bu = new Bundle();																		//Erstellen eines Bundles
			bu.putInt("game", win);																			//F�llen des Bundles mit Key und dem dazugeh�rigen Wert
			in.putExtras(bu);																				//Bundle ins Intent hinzuf�gen
			startActivity(in);																				//N�chste Activity starten
			finish();																						//Activity beenden
			System.exit(0);																					//Activity schliessen
		}
	}

}
