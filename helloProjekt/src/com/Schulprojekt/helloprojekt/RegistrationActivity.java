package com.Schulprojekt.helloprojekt;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegistrationActivity extends Activity {
	
	private final static String SERVICE_URI = "http://";
	
	Button bregistrieren;
	JSONObject user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        
        bregistrieren = (Button) findViewById(R.id.registrieren);
        bregistrieren.setOnClickListener(new OnClickListener() {
        	
        	public void onClick(View view) {
        		
        		if(view == bregistrieren) {
        			EditText tvname = (EditText) findViewById(R.id.username);
        			EditText tvalias = (EditText) findViewById(R.id.username);
        			EditText tvpasswort = (EditText) findViewById(R.id.passwort);
        			EditText tvpasswortwh = (EditText) findViewById(R.id.passwortwh);
        			
        			String name = tvname.getText().toString();
        			String alias = tvalias.getText().toString();
        			String passwort = tvpasswort.getText().toString();
        			String passwortwh = tvpasswortwh.getText().toString();
        			
        			

        			if(name.equals("") || name.equals(null) || alias.equals("") || alias.equals(null) || passwort.equals("") || passwort.equals(null)){
        				Toast.makeText(RegistrationActivity.this, "Es werden alle Eingaben Benötigt", Toast.LENGTH_LONG).show();
        			}else{
        				if(passwortwh.equals(passwort)){
        					
        					
        					DefaultHttpClient httpClient = new DefaultHttpClient();
        			        HttpGet request = new HttpGet(SERVICE_URI + "/GetUserByAccountName");
        			 
        			        request.setHeader("Accept", "application/json");
        			        request.setHeader("Content-type", "application/json");
        			 
        			        HttpResponse response;
							try {
								response = httpClient.execute(request);
        			 
        			        HttpEntity responseEntity = response.getEntity();
        			 
        			        // Read response data into buffer
        			        char[] buffer = new char[(int)responseEntity.getContentLength()];
        			        InputStream stream = responseEntity.getContent();
        			        InputStreamReader reader = new InputStreamReader(stream);
        			        reader.read(buffer);
        			        stream.close();
        			 
        			        user = new JSONObject(new String(buffer));

							} catch (ClientProtocolException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
        					
        					if(user.isNull(name)){
        						Toast.makeText(RegistrationActivity.this, "Name schon vergeben!", Toast.LENGTH_LONG).show();
        					}else{
        					
        							
        				
//        					new User(name, alias, passwort, true);
//        					
//        					HttpPost request = new HttpPost(SERVICE_URI + "/CreateUser");
//        		            request.setHeader("Accept", "application/json");
//        		            request.setHeader("Content-type", "application/json");
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
//        		            request.setEntity(entity);
//        		 
//        		            // Send request to WCF service
//        		            DefaultHttpClient httpClient = new DefaultHttpClient();
//        		            HttpResponse response = httpClient.execute(request);
//        		            
//        		            Log.d("WebInvoke", "Saving : " + response.getStatusLine().getStatusCode());
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

        				
        					}
        				}
        				else{
        					Toast.makeText(RegistrationActivity.this, "Passwörter stimmen nicht ueberein", Toast.LENGTH_LONG).show();
        				}

        			} 
        			}       					
        	}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.registration, menu);
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
