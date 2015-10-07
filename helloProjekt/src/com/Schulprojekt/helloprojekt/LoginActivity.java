package com.Schulprojekt.helloprojekt;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.Schulprojekt.helloprojekt.GUILogik.User;
import com.Schulprojekt.helloprojekt.GUILogik.UserServices;
import com.Schulprojekt.helloprojekt.GUILogik.md5Generator;
import com.google.gson.Gson;

public class LoginActivity extends Activity {
	// TODO private statt public?
	public Button btnRegistration; 																// Deklaration
	public Button btnLogin;
	public ImageView imageView;
	public EditText loginUsername;
	public EditText loginPassword;
	public Drawable d;
	public User user;
	public String md5Pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) { 										// Activity wird aufgebaut
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_login);
		loginUsername = (EditText) findViewById(R.id.loginUsername); 							// auf Textfeld loginUsername zurgreifen
		loginPassword = (EditText) findViewById(R.id.loginPassword);							// auf Textfeld loginPassword zurgreifen
		btnRegistration = (Button) findViewById(R.id.register); 								// auf Buttonregister zurgreifen
		btnLogin = (Button) findViewById(R.id.login); 											// auf Button login zugreifen
		imageView = (ImageView) findViewById(R.id.imageView); 									// auf ImageView imageView zugreifen
		btnRegistration.setOnClickListener(new OnClickListener() { 								// auf den Button Register einen OnClickListener setzen
					@Override
					public void onClick(View v) {
						if (v == btnRegistration) {
							startActivity(new Intent(LoginActivity.this,
									RegistrationActivity.class)); 								// auf RegistrationActivity weiterleiten, wenn Button geklickt wird
						}

					}
				});
		btnLogin.setOnClickListener(new OnClickListener() { 									// auf den Button login einen OnClickListener setzen
			@Override
			public void onClick(View v) {
				if (v == btnLogin) { 															// wird der Button geklickt, wird die Verbindung zur Datenbank aufgebaut
					String pass = loginPassword.getText().toString();
					try {
						user = UserServices.getUserByAccountName(loginUsername.getText().toString());
						
						if (user.getAccountName() == null) { 											// ist loginUsername null, kommt die Fehlermeldung
							Toast.makeText(LoginActivity.this,
									"Benutzername oder Passwort falsch!",
									Toast.LENGTH_LONG).show();
						} else {
							md5Pass = md5Generator.getMd5(pass);
							// TODO md5 passwort -> user.getPassword().equals(loginPassword.md5)
							if (user.getPassword().trim().equals(md5Pass)) {
								Intent i = new Intent(LoginActivity.this, 						// sonst wird die nächste Activity ContactListActivity gestartet
										ContactListActivity.class);
								Bundle b = new Bundle();
								b.putInt("userId", user.getAccountID());
								b.putString("aliasName", user.getAlias());
								b.putString("accountName", user.getAccountName());
								
//								Bitmap bmp = ((BitmapDrawable)d).getBitmap();
//								ByteArrayOutputStream baos = new ByteArrayOutputStream();
//								bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
//								byte[] byteArray = baos.toByteArray();
//								
//								b.putByteArray("picture", byteArray);
								b.putString("password", user.getPassword());
								i.putExtras(b);
								startActivity(i);
								finish();
							} else {
								Toast.makeText(LoginActivity.this,
										"Benutzername oder Passwort falsch!",
										Toast.LENGTH_LONG).show();
							}
						}	
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});
		
		// TODO muss das überhaupt noch?
		imageView.setOnClickListener(new OnClickListener() { 									// auf das ImageView imageView einen OnClickListener setzen
					@Override
					public void onClick(View v) {
						if (v == imageView) { 													// wird imageView geklickt, wird der Boolean auf false gesetzt
							String pass = loginPassword.getText().toString();
							
							try {
								user = UserServices.getUserByAccountName(loginUsername.getText().toString());
								
								if (user.getAccountName() == null) { 											// ist loginUsername null, kommt die Fehlermeldung
									Toast.makeText(LoginActivity.this,
											"Benutzername oder Passwort falsch!",
											Toast.LENGTH_LONG).show();
								} else {
									String eins = user.getPassword().trim();
									
									// TODO md5 passwort -> user.getPassword().equals(loginPassword.md5)
									if (user.getPassword().trim().equals(pass)) {
										Intent i = new Intent(LoginActivity.this, 						// sonst wird die nächste Activity ContactListActivity gestartet
												ContactListActivity.class);
										Bundle b = new Bundle();
										b.putInt("userId", user.getAccountID());
										b.putString("aliasName", user.getAlias());
										b.putString("accountName", user.getAccountName());
										
//										Bitmap bmp = ((BitmapDrawable)d).getBitmap();
//										ByteArrayOutputStream baos = new ByteArrayOutputStream();
//										bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
//										byte[] byteArray = baos.toByteArray();
//										
//										b.putByteArray("picture", byteArray);
										b.putString("password", user.getPassword());
										i.putExtras(b);
										startActivity(i);
										finish();
									} else {
										Toast.makeText(LoginActivity.this,
												"Benutzername oder Passwort falsch!",
												Toast.LENGTH_LONG).show();
									}
								}	
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}

					}
				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) { 											// Menü wird aufgebaut
		//getMenuInflater().inflate(R.menu.login, menu);
		return true; 																			// wenn Menü aufgebaut ist, gibt die Methode true zurück
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) { 										// wird ein Item im Menüe ausgewählt, gibt die Methode true zurück
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onClickListener() {

	}
}