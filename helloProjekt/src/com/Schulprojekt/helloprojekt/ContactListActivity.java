package com.Schulprojekt.helloprojekt;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.R.drawable;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Telephony.Sms.Conversations;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Schulprojekt.helloprojekt.GUILogik.ContactListEntry;
import com.Schulprojekt.helloprojekt.GUILogik.User;

public class ContactListActivity extends Activity {

	ArrayList<User> userList = new ArrayList<User>();
	ArrayList<ContactListEntry> contactList;
	String userName;
	private final static String SERVICE_URI = "http://lt0.studio.entail.ca:8080/VehicleService.svc";
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		User user =  (User) getIntent().getExtras().getParcelable("LoggedUser");
//		userName = user.getAccountName();
		//onLoadVehicle(userName);
		setContentView(R.layout.activity_contact_list);
		Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.dummycontact);
		User u1 = new User(UUID.randomUUID(), "KnesKa", "KnesKa", true, icon);
		User u2 = new User(UUID.randomUUID(), "RehdTi", "RehdTi", true, icon);
		User u3 = new User(UUID.randomUUID(), "StehCh", "StehCh", true, icon);
		User u4 = new User(UUID.randomUUID(), "PetzSa", "PetzSa", true, icon);
		final LinearLayout linlayoutVertical = (LinearLayout) findViewById(R.id.linLayoutContactVertical);
		findViewById(R.id.scrollViewContact);
		findViewById(R.id.textViewContact);
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);
		userList.add(u4);
		int i = 0;
		
		ArrayList<ContactListEntry> contactList = new ArrayList<ContactListEntry>();
		
		for (User user : userList) {
			LinearLayout linlay = new LinearLayout(getApplicationContext());
			linlay.setOrientation(LinearLayout.HORIZONTAL);
			ImageView img = new ImageView(getApplicationContext());
			img.setImageBitmap(user.getAccountPicture());
			img.setOnClickListener(getOnKlickListener(img));
			img.setId(i);
			TextView tv = new TextView(getApplicationContext());
			tv.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
			tv.setTextColor(Color.BLACK);
			tv.setGravity(Gravity.CENTER_VERTICAL);
			tv.setTextSize(24);
			tv.setText(user.getAlias());
			linlay.addView(img);
			linlay.addView(tv);
			ContactListEntry entry = new ContactListEntry(linlay, img, tv, user.getAccountName());
			contactList.add(entry);
			i = i+1;
		}
		
//		contactList = fillList(userList, getApplicationContext());
		
		for (ContactListEntry contact : contactList) {
			LayoutParams layout = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
			contact.getLinlayout().setLayoutParams(layout);
			linlayoutVertical.addView(contact.getLinlayout());
		}
	}
	
//	public ArrayList<ContactListEntry> fillList(ArrayList<User> userList, Context con){
////		int i = 0;
////		ArrayList<ContactListEntry> contactList = new ArrayList<ContactListEntry>();
////		for (User user : userList) {
////			LinearLayout linlay = new LinearLayout(con);
////			ImageView img = new ImageView(con);
////			img.setImageResource(R.drawable.dummycontact);
////			img.setId(i);
////			TextView tv = new TextView(con);
////			tv.setText(user.getAlias());
////			ContactListEntry entry = new ContactListEntry(linlay, img, tv, user.getAccountName());
////			contactList.add(entry);
////			i = i+1;
////		}
////		return contactList;
//	}

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
			finish();
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public View.OnClickListener getOnKlickListener(ImageView img){
	    return new View.OnClickListener() {
	        public void onClick(View v) {
	        	Intent in = new Intent(ContactListActivity.this, SimpleChatActivity.class);
	        	Bundle b = new Bundle();
				b.putString("username", userList.get(v.getId()).getAlias().toString());
				Bitmap bmp = userList.get(v.getId()).getAccountPicture();
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte[] byteArray = stream.toByteArray();
				b.putByteArray("picture", byteArray);
				in.putExtras(b);
				startActivity(in);
	        }
	    };
}
}