package com.Schulprojekt.helloprojekt;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import com.Schulprojekt.helloprojekt.GUILogik.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegistrationActivity extends Activity {
	private final static String SERVICE_URI = "http://hello-server/helloservice/messengerservice.svc";														//URL zum WebService
	Button bregistrieren;																						//Deklaration
	JSONObject user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {														//Activity wird aufgebaut
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        
        bregistrieren = (Button) findViewById(R.id.registrieren);												//auf Button register zurgreifen
        bregistrieren.setOnClickListener(new OnClickListener() {												//auf den Button register einen OnClickListener setzen
        	public void onClick(View view) {
        		if(view == bregistrieren) {
        			EditText tvname = (EditText) findViewById(R.id.username);									//auf Textfeld username zugreifen
        			EditText tvalias = (EditText) findViewById(R.id.username);									//auf Textfeld username zugreifen - bei der Registrierung entspricht der Alias dem Username
        			EditText tvpasswort = (EditText) findViewById(R.id.passwort);								//auf Textfeld passwort zugreifen
        			EditText tvpasswortwh = (EditText) findViewById(R.id.passwortwh);							//auf Textfeld passwortwh zugreifen
        			String name = tvname.getText().toString();													//tvname in einen String umwandeln und in dem String name speichern
        			String alias = tvalias.getText().toString();												//tvalias in einen String umwandeln und in dem String alias speichern
        			String passwort = tvpasswort.getText().toString();											//tvpasswort in einen String umwandeln und in dem String passwort speichern
        			String passwortwh = tvpasswortwh.getText().toString();										//tvpasswortwh in einen String umwandeln und in dem String passwortwh speichern
        			
        			
        			if(name.equals("") || name.equals(null) || alias.equals("") || alias.equals(null) 
        					|| passwort.equals("") || passwort.equals(null)){									//prüfen ob name, alias oder passwort leer sind
        				Toast.makeText(RegistrationActivity.this, "Es werden alle Eingaben Benötigt", 
        						Toast.LENGTH_LONG).show();
        			}else{
        				if(passwortwh.equals(passwort)){														//prüfen ob passwortwh passwort entspricht
        					
    			        	Bundle b = new Bundle();
    			        	Intent i = new Intent(RegistrationActivity.this, UserProfileActivity.class);
    			        	b.putString("userId", "");
    			        	b.putString("accountName", "test");
    			        	b.putString("aliasName", "test");
    			        	b.putString("password", "test");
    			        	b.putBoolean("accountState", true);
//    			        	b.putString("expierencePoints", "...");
    			        	b.putByteArray("picture", new byte[]{50});
    			        	
//    			        	b.putString("userId", user.getString("userId"));
//    			        	b.putString("accountName", user.getString("accountName"));
//    			        	b.putString("aliasName", user.getString("aliasName"));
//    			        	b.putString("password", user.getString("password"));
//    			        	b.putBoolean("accountState", user.getString("accountState"));
//    			        	b.putString("expierencePoints", user.getString("expierencePoints"));
//    			        	b.putByteArray("picture", user.getString("picture"));
    			        	
    						i.putExtras(b);
    						startActivity(i);
//        					
//        					DefaultHttpClient httpClient = new DefaultHttpClient();								//Client erstellen
//        			        HttpGet request = new HttpGet(SERVICE_URI + "/GetUserByAccountName/"+name);				//URL erstellen
//        			        request.setHeader("Accept", "application/json");
//        			        request.setHeader("Content-type", "application/json");
//        			        HttpResponse response = null;
//        			        
//							try {
//								response = httpClient.execute(request);
//        			        HttpEntity responseEntity = response.getEntity();	
//        			        // Read response data into buffer
//        			        char[] buffer = new char[(int)responseEntity.getContentLength()];					//Daten im Array speichern
//        			        InputStream stream = responseEntity.getContent();
//        			        InputStreamReader reader = new InputStreamReader(stream);							//Reader deklarieren
//        			        reader.read(buffer);																//Reader liest Buffer ein
//        			        stream.close();
//        			        user = new JSONObject(new String(buffer));											//ein JSONObject erstellens
//        			        tvname.setText(user.getString("accountName"));
//        			        
//							} catch (ClientProtocolException e) {
//								e.printStackTrace();
//							} catch (IOException e) {
//								e.printStackTrace();
//							} catch (JSONException e) {
//								e.printStackTrace();
//							}
//							
////							
////        					if(tvname.getText() == null){
//        					if(!(response == null)){
//        						Toast.makeText(RegistrationActivity.this, "Name schon vergeben!", 
//        								Toast.LENGTH_LONG).show();
//        					}else{
//        				
//        					new User(name, alias, passwort, true);
//        					
//        					HttpPost request2 = new HttpPost(SERVICE_URI + "/CreateUser");
//        		            request2.setHeader("Accept", "application/json");
//        		            request2.setHeader("Content-type", "application/json");
//        		 
//        		            // Build JSON string
//        		            JSONStringer user;
//							try {
//								user = new JSONStringer()
//								    .object()
//								        .key("user")
//								            .object()
//								                .key("userId").value(null)
//								                .key("accountName").value(name)
//								                .key("accountState").value("")
//								                .key("expierencePoints").value(0)
//								                .key("picture").value(new byte[]{50})
//								                .key("password").value(passwort.getBytes())
//								            .endObject()
//								        .endObject();
//        		            StringEntity entity = new StringEntity(user.toString());
//        		 
//        		            request2.setEntity(entity);
//        		 
//        		            // Send request to WCF service
//        		            DefaultHttpClient httpClient2 = new DefaultHttpClient();
//        		            HttpResponse response2 = httpClient2.execute(request2);
//        		            
//        		            Log.d("WebInvoke", "Saving : " + response2.getStatusLine().getStatusCode());
//
//							} catch (JSONException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							} catch (UnsupportedEncodingException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							} catch (ClientProtocolException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							} catch (IOException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}

        				
//        					}
    						finish();
        				}
        				else{
        					Toast.makeText(RegistrationActivity.this, "Passwörter stimmen nicht ueberein", 		
        							Toast.LENGTH_LONG).show();
        				}

        			} 
        			}       					
        	}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {																//Menü wird aufgebaut
        //getMenuInflater().inflate(R.menu.registration, menu);
        return true;																							//wenn Menü aufgebaut ist, gibt die Methode true zurück
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {														//wird ein Item im Menüe ausgewählt, gibt die Methode true zurück
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    

    

}
