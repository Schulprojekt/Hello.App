package com.Schulprojekt.helloprojekt.Spiele;

import com.Schulprojekt.helloprojekt.R;
import com.Schulprojekt.helloprojekt.R.id;
import com.Schulprojekt.helloprojekt.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TicTacToeActivity extends Activity {
	private Button buttonTicTacToe1, buttonTicTacToe2, buttonTicTacToe3, buttonTicTacToe4,					//Deklaration
		buttonTicTacToe5, buttonTicTacToe6, buttonTicTacToe7, buttonTicTacToe8, buttonId,
		buttonTicTacToe9, buttonNewGame, buttonExit;
	private boolean noughtsTurn = false; 																	//false=X true=O der erste Spieler beginnt mit X
	private char board[][] = new char[3][3]; 																//das Array wird mit den Positionen des Spielfeldes gefüllt
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {													//Activity wird aufgebaut
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tic_tac_toe);
		buttonTicTacToe1 = (Button) findViewById(R.id.button11);											//auf die Button zugreifen
		buttonTicTacToe2 = (Button) findViewById(R.id.button12);
		buttonTicTacToe3 = (Button) findViewById(R.id.button13);
		buttonTicTacToe4 = (Button) findViewById(R.id.button21);
		buttonTicTacToe5 = (Button) findViewById(R.id.button22);
		buttonTicTacToe6 = (Button) findViewById(R.id.button23);
		buttonTicTacToe7 = (Button) findViewById(R.id.button31);
		buttonTicTacToe8 = (Button) findViewById(R.id.button32);
		buttonTicTacToe9 = (Button) findViewById(R.id.button33);
		buttonExit = (Button) findViewById(R.id.buttonExit);
		buttonNewGame = (Button) findViewById(R.id.buttonNewGame);											
		
		buttonExitNewGame();																				//Methodenaufruf von buttonExitNewGame()
		setupOnClickListeners();																			//Methodenaufruf von setupOnClickListeners()																	
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {													//aktiviert die Actionbar, wenn man einen Parent ausgewählt hat
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	private void setupOnClickListeners() {																	//setzt OnClickListener auf alle Buttons im Tabellenlayout
	    TableLayout T = (TableLayout) findViewById(R.id.tablelayout);										//spricht das Tabellenlayout an
	    for(int y = 0; y < T.getChildCount(); y ++) {														//so lange das Layout 'Children' enthält, wird die Schleife durchlaufen
	        if(T.getChildAt(y) instanceof TableRow) {														//Tabelle wird auf Zeilen überprüft
	            TableRow R = (TableRow) T.getChildAt(y);													//spricht die Zeile der Tabelle an der Stelle y an
	            for(int x = 0; x < R.getChildCount(); x ++) {												//so lange in der Zeile 'Children' enthalten sind, wird die Schleife durchlaufen
	                View V = R.getChildAt(x);																//jeder Button wird erfasst 
	                V.setOnClickListener(new PlayOnClick(x, y));											//auf jeden Button in den Zeilen einen OnClickListener setzen 'PlayOnClick'
	            }
	        }
	    }
	}
	public void buttonExitNewGame (){																		//Methode für die Buttons Exit und New Geame außerhalb des Tabellenlayouts
		buttonNewGame.setOnClickListener(new OnClickListener() {											//auf den Button New Game einen OnClickListener setzen
			public void onClick(View view) {
				noughtsTurn = false;																		//der erste Zug wird wieder auf false also X gesetzt
				board = new char[3][3];																		//das char Array wird 'geleert'
				resetButtons();																				//Methode resetButtons() wird aufgerufen
			}
		});
		buttonExit.setOnClickListener(new OnClickListener() {												//auf den Button Exit einen OnClickListenener setzen
			public void onClick(View view) {
				finish();																					//Methode finish() aufrufen
				System.exit(0);																				//Anwendung komplett schließen
			}
		});
	}
	private void resetButtons() {																			//Methode zum Löschen der X und O auf den Buttons
	    TableLayout T = (TableLayout) findViewById(R.id.tablelayout);										//spricht das Tabellenlayout an
	    TextView textView = (TextView) findViewById(R.id.titleText);										//spricht das Textfeld an
	    textView.setText("Tic Tac Toe");																	//setzt den Text im Textfeld wieder auf 'Tic Tac Toe' zurück
	    for (int y = 0; y < T.getChildCount(); y++) {														//so lange im Tabellenlayout 'Children' sind, wird die Schleife durchlaufen
	        if (T.getChildAt(y) instanceof TableRow) {														//Tabelle wird auf Zeilen überprüft 
	            TableRow R = (TableRow) T.getChildAt(y);													//spricht die Zeile der Tabelle an der Stelle y an
	            for (int x = 0; x < R.getChildCount(); x++) {												//so lange in der Zeile 'Children' enthalten sind, wird die Schleife durchlaufen
	                if(R.getChildAt(x) instanceof Button) {													//prüft, ob ein Button an der Stelle x in der Zeile y existiert											
	                    Button B = (Button) R.getChildAt(x);												//spricht den Button an der Stelle x an
	                    B.setText("");																		//setzt den Text auf dem Button leer
	                    B.setEnabled(true);																	//aktiviert alle Buttons wieder
	                }
	            }
	        }
	    }
	}
	private class PlayOnClick implements View.OnClickListener {												//eigene Klasse PlayOnClick, welche View.OnClickListener implementiert
	    private int x = 0, y = 0;																			//setzt die Variablen x und y
	    public PlayOnClick(int x, int y) {																	//weißt die Variablen x und y fest
	        this.x = x;
	        this.y = y;
	    }
	    private boolean checkWinner(char[][] board, int size, char player) {								//Methode checkWinner
	        for (int x = 0; x < size; x++) {																//überprüft jede Spalte
	            int total = 0;
	            for (int y = 0; y < size; y++) {
	                if (board[y][x] == player) {
	                    total++;
	              
	                }
	            }
	            if (total >= size) {																		//prüft, ob die Anzahl der geklickten Felder in einer Reihe >= der Größe der Reihe 
	                return true; 																			//wenn true, muss auf Gewinnder geprüft werden
	            }
	        }
	        for (int y = 0; y < size; y++) {																
	            int total = 0;
	            for (int x = 0; x < size; x++) {															//überprüft jede Spalte
	                if (board[y][x] == player) {															//wenn das Feld gespielt wurd, erhöht sich die Anzahl der gespielten Felder
	                    total++;
	                }
	            }
	            if (total >= size) {																		//prüft, ob die Anzahl der geklickten Felder in einer Reihe >= der Größe der Reihe 																	
	                return true;																			//wenn true, muss auf Gewinnder geprüft werden
	            }
	        }
	        int total = 0;
	        for (int x = 0; x < size; x++) {					
	            for (int y = 0; y < size; y++) {
	                if (x == y && board[y][x] == player) {													//prüft von links nach rechts ob die Reihe gefüllt ist
	                    total++;									
	                }
	            }
	        }
	        if (total >= size) {																			//prüft, ob die Anzahl der geklickten Felder in einer Reihe >= der Größe der Reihe 
	            return true; 																				//wenn true, muss auf Gewinnder geprüft werden
	        }
	        total = 0;
	        for (int x = 0; x < size; x++) {																
	            for (int y = 0; y < size; y++) {
	                if (x + y == size - 1 && board[y][x] == player) {										//prüft von rechts nach links ob die Reihe gefüllt ist
	                    total++;
	                }
	            }
	        }
	        if (total >= size) {																			//prüft, ob die Anzahl der geklickten Felder in einer Reihe >= der Größe der Reihe 
	            return true;																				//wenn true, muss auf Gewinnder geprüft werden
	        }
	        return false;																					//wenn gar nichts zu trifft, gewinnt keiner
	    }
	    private boolean checkWin() {																		//Methode checkWin()
	        char winner = '\0';																				//Variable winner als leeren char deklarieren
	        if (checkWinner(board, 3, 'X')) {																//überprüfe, ob die gefüllte Reihe aus 3 X besteht
	            winner = 'X';																				//setze winner auf X
	        } else if (checkWinner(board, 3, 'O')) {														//überprüfe, ob die gefüllte Reihe aus 3 O besteht
	            winner = 'O';																				//setze winner auf O
	        }
	        if (winner == '\0') {																			//trifft gar nichts zu, return false
	            return false;
	        } else {																						//sonst lasse den Gewinner im Textfeld erscheinen 
	            TextView textView = (TextView) findViewById(R.id.titleText);
	            textView.setText(winner + " hat gewonnen");
	            return true;
	        }
	    }
	    @Override
	    public void onClick(View view) {																	//Methode onClick
	        if (view instanceof Button) {																	//alle Objekte des Typen Buttons ansprechen
	            Button B = (Button) view;
	            int id = 0;
	            board[y][x] = noughtsTurn ? 'O' : 'X';														//wurde der Button geklickt  
	            B.setText(noughtsTurn ? "O" : "X");															//erscheint ein X oder O auf dem Button
	            B.setEnabled(false);																		//disable den Button
	            noughtsTurn = !noughtsTurn;																	//beim nächsten Zug darf nicht das gleiche Zeichen gesetzt werden					
	            id = B.getId();																				//speichert in der Variable id die ID des geklickten Buttons        
	            buttonId = (Button) B.findViewById(id);													    //sucht den Button über die Variable id
	            if (checkWin() == true) {																	//überprüfe, ob checkWin() true zurück gibt
	                disableButtons();																		//führe sofort die Methode disableButtons() aus
	            }
	            if (checkWin() == false){																	//überprüfe, ob checkWin() false zurück gibt
	            	TextView textView = (TextView) findViewById(R.id.titleText);							//lasse in Textfeld 'No winner!' für unentscheiden erscheinen
	            	textView.setText("Kein Gewinner!");
	            }
	        }
	    }
	}
	public void disableButtons() {																			//Methode disable Buttons
		buttonTicTacToe1.setEnabled(false); 																//setzt den Button auf disable
		buttonTicTacToe2.setEnabled(false);
		buttonTicTacToe3.setEnabled(false);
		buttonTicTacToe4.setEnabled(false);
		buttonTicTacToe5.setEnabled(false);
		buttonTicTacToe6.setEnabled(false);
		buttonTicTacToe7.setEnabled(false);
		buttonTicTacToe8.setEnabled(false);
		buttonTicTacToe9.setEnabled(false);	
	}
	
}

