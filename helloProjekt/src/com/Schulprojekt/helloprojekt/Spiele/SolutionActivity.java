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

	Button btn_ende;
	TextView txt_field;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solution);
		
		Bundle bu = getIntent().getExtras();
		int wert = bu.getInt("game");
		
		if (wert > 0){
			txt_field = (TextView) findViewById(R.id.txt_field);
			txt_field.setText("Sie haben leider das Spiel verloren!");
		} else {
			txt_field = (TextView) findViewById(R.id.txt_field);
			txt_field.setText("Herzlichen Glückwunsch! Sie haben das Spiel gewonnen!");
		}
		
		btn_ende = (Button) findViewById(R.id.btn_ende);
		btn_ende.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				System.exit(0);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.solution, menu);
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

