package com.Schulprojekt.helloprojekt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import com.Schulprojekt.helloprojekt.GUILogik.Md5Generator;

public class LoginActivity extends Activity {
	public Button btnRegistration; 																			//Deklaration
	public Button btnLogin;
	public ImageView imageView;
	public EditText loginUsername;
	public EditText loginPassword;
	public Drawable d;
	public User user;
	public String md5Pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) { 													//Activity wird aufgebaut
		super.onCreate(savedInstanceState);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		setContentView(R.layout.activity_login);
		loginUsername = (EditText) findViewById(R.id.loginUsername); 										//Auf Textfeld loginUsername zurgreifen
		loginPassword = (EditText) findViewById(R.id.loginPassword);										//Auf Textfeld loginPassword zurgreifen
		btnRegistration = (Button) findViewById(R.id.register); 											//Auf Buttonregister zurgreifen
		btnLogin = (Button) findViewById(R.id.login); 														//Auf Button login zugreifen
		imageView = (ImageView) findViewById(R.id.imageViewLogin); 											//Auf ImageView imageView zugreifen
		btnRegistration.setOnClickListener(new OnClickListener() { 											//Auf den Button Register einen OnClickListener setzen
					@Override
					public void onClick(View v) {
						if (v == btnRegistration) {
							startActivity(new Intent(LoginActivity.this,
									RegistrationActivity.class)); 											//Auf RegistrationActivity weiterleiten, wenn Button geklickt wird
						}

					}
				});
		btnLogin.setOnClickListener(new OnClickListener() { 												//Auf den Button login einen OnClickListener setzen
			@Override
			public void onClick(View v) {
				if (v == btnLogin) { 																		//Wird der Button geklickt, wird die Verbindung zur Datenbank aufgebaut
					String pass = loginPassword.getText().toString();
					try {
						user = UserServices.getUserByAccountName(loginUsername.getText().toString());
						
						if (user.getAccountName() == null) { 												//Wenn loginUsername null ist, kommt die Fehlermeldung
							Toast.makeText(LoginActivity.this,
									"Benutzername oder Passwort falsch!",
									Toast.LENGTH_LONG).show();
						} else {
							md5Pass = Md5Generator.getMd5(pass);
							if (user.getPassword().trim().equals(md5Pass)) {
								Intent i = new Intent(LoginActivity.this, 									//Sonst wird die nächste Activity ContactListActivity gestartet
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
		
		imageView.setOnClickListener(new OnClickListener() { 												//Auf das ImageView imageView einen OnClickListener setzen
					@Override
					public void onClick(View v) {
						if (v == imageView) { 																		//Wird der Button geklickt, wird die Verbindung zur Datenbank aufgebaut
							String pass = loginPassword.getText().toString();
							try {
								user = UserServices.getUserByAccountName(loginUsername.getText().toString());
								
								if (user.getAccountName() == null) { 												//Wenn loginUsername null ist, kommt die Fehlermeldung
									Toast.makeText(LoginActivity.this,
											"Benutzername oder Passwort falsch!",
											Toast.LENGTH_LONG).show();
								} else {
									md5Pass = Md5Generator.getMd5(pass);
									if (user.getPassword().trim().equals(md5Pass)) {
										Intent i = new Intent(LoginActivity.this, 									//Sonst wird die nächste Activity ContactListActivity gestartet
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
	public boolean onCreateOptionsMenu(Menu menu) { 														//Menü wird aufgebaut
		return true; 																						//Wenn Menü aufgebaut ist, gibt die Methode true zurück
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) { 													//Wird ein Item im Menü ausgewählt, gibt die Methode true zurück
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onClickListener() {

	}
}