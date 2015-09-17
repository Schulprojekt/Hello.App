package com.Schulprojekt.helloprojekt;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.Schulprojekt.helloprojekt.GUILogik.ContactListEntry;
import com.Schulprojekt.helloprojekt.GUILogik.ContactListLogik;
import com.Schulprojekt.helloprojekt.GUILogik.User;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ContactListActivity extends Activity {

	ArrayList<User> userList = new ArrayList<User>();
	ArrayList<ContactListEntry> contactList;
	User u1 = new User("test", "test", "test",true);
	User u2 = new User("test", "test", "test",true);
	ContactListLogik conlog = new ContactListLogik();
	Bundle bundle = new Bundle();
	String userName;
	private final static String SERVICE_URI = "http://lt0.studio.entail.ca:8080/VehicleService.svc";
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		User user =  (User) bundle.getParcelable("user");
		userName = user.getAccountName();
		onLoadVehicle(userName);
		setContentView(R.layout.activity_contact_list);
		final LinearLayout linlayoutVertical = (LinearLayout) findViewById(R.id.linLayoutContactVertical);
		findViewById(R.id.scrollViewContact);
		findViewById(R.id.textViewContact);
		userList.add(u1);
		userList.add(u2);
		contactList = conlog.fillList(userList, getApplicationContext());
		for (ContactListEntry contact : contactList) {
			LinearLayout lilayout = contact.getLinlayout();
			lilayout.setOrientation(LinearLayout.HORIZONTAL);
			contact.getContactPicture().setOnClickListener(getOnKlickListener(contact.getContactPicture()));
			lilayout.addView(contact.getContactPicture());
			lilayout.addView(contact.getAlias());
			linlayoutVertical.addView(lilayout);
		}
	}

	public void onLoadVehicle(String userName) {
	    try {
	        // Send GET request to <service>/GetVehicle/<plate>
	        DefaultHttpClient httpClient = new DefaultHttpClient();
	        HttpGet request = new HttpGet(SERVICE_URI + "/GetRelationship/" + userName);
	 
	        request.setHeader("Accept", "application/json");
	        request.setHeader("Content-type", "application/json");
	 
	        HttpResponse response = httpClient.execute(request);
	 
	        HttpEntity responseEntity = response.getEntity();
	 
	        // Read response data into buffer
	        char[] buffer = new char[(int)responseEntity.getContentLength()];
	        InputStream stream = responseEntity.getContent();
	        InputStreamReader reader = new InputStreamReader(stream);
	        reader.read(buffer);
	        stream.close();
	 
	        JSONObject vehicle = new JSONObject(new String(buffer));
	 
	        // Populate text fields
//	        makeEdit.setText(vehicle.getString("make"));
//	        plateEdit.setText(vehicle.getString("plate"));
//	        modelEdit.setText(vehicle.getString("model"));
//	        yearEdit.setText(vehicle.getString("year"));
	 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_list, menu);
		getActionBar().setIcon(R.drawable.dummycontact);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		case R.id.act_ContactSearch:
			startActivity(new Intent(ContactListActivity.this, ContactSearchActivity.class));
		case R.id.act_AppExit:
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public View.OnClickListener getOnKlickListener(ImageView img){
	    return new View.OnClickListener() {
	        public void onClick(View v) {
	        	Intent in = new Intent(ContactListActivity.this, ChatActivity.class);
//				in.putExtra("ContactImage", (CharSequence) imgV.getBackground());
				startActivity(in);
	        }
	    };
}
}