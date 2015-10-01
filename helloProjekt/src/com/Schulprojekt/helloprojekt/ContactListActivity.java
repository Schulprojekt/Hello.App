package com.Schulprojekt.helloprojekt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.R.drawable;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ContactListActivity extends Activity {

	ArrayList<User> userList = new ArrayList<User>();
	ArrayList<ContactListEntry> contactList;
	String userName;
	User user;
	private final static String SERVICE_URI = "http://lt0.studio.entail.ca:8080/VehicleService.svc";
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		user = new User();
		user.setAccountID(UUID.randomUUID());
		user.setAccountName(b.getString("accountName"));
		user.setAlias(b.getString("aliasName"));
		user.setPassword(b.getString("password"));
		user.setAccountState(b.getBoolean("accountState"));
		user.setAccountPicture(b.getByteArray("picture"));
		setContentView(R.layout.activity_contact_list);
		//Test
		Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.dummycontact);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		icon.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] byteArray = stream.toByteArray();
		User u1 = new User(UUID.randomUUID(), "KnesKa", "KnesKa", true, byteArray);
		User u2 = new User(UUID.randomUUID(), "RehdTi", "RehdTi", true, byteArray);
		User u3 = new User(UUID.randomUUID(), "StehCh", "StehCh", true, byteArray);
		User u4 = new User(UUID.randomUUID(), "PetzSa", "PetzSa", true, byteArray);
		//Test END
		final LinearLayout linlayoutVertical = (LinearLayout) findViewById(R.id.linLayoutContactVertical);
		findViewById(R.id.scrollViewContact);
		findViewById(R.id.textViewContact);
		//Test
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);
		userList.add(u4);
		//Test END
		
		ArrayList<User> friends = new ArrayList<User>();
		friends = getFriends();
		
		int i = 0;
		
		ArrayList<ContactListEntry> contactList = new ArrayList<ContactListEntry>();
		
		for (User user : friends) {
			LinearLayout linlay = new LinearLayout(getApplicationContext());
			linlay.setOrientation(LinearLayout.HORIZONTAL);
			ImageView img = new ImageView(getApplicationContext());
			BitmapDrawable bmp = new BitmapDrawable(BitmapFactory.decodeByteArray(user.getAccountPicture(), 0, user.getAccountPicture().length));
			img.setImageDrawable(bmp);
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
		for (ContactListEntry contact : contactList) {
			LayoutParams layout = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
			contact.getLinlayout().setLayoutParams(layout);
			linlayoutVertical.addView(contact.getLinlayout());
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.contact_list, menu);
		getActionBar().setIcon(R.drawable.dummycontact);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.contact_profile:
			Intent in = new Intent(ContactListActivity.this, UserSettingActivity.class);
			Bundle b = new Bundle();
			b.putString("userId", "Test");
			b.putString("accountName", user.getAccountName());
			b.putString("aliasName", user.getAlias());
			b.putBoolean("accountState", user.getAccountState());
			b.putByteArray("picture", user.getAccountPicture());
			b.putString("password", user.getPassword());
			in.putExtras(b);
			startActivity(in);
			break;
		case R.id.act_ContactSearch:
			startActivity(new Intent(ContactListActivity.this, ContactSearchActivity.class));
			break;
		case R.id.act_AppExit:
			finish();
			break;
			default:	
		}
		return super.onOptionsItemSelected(item);
	}
	
	public View.OnClickListener getOnKlickListener(ImageView img){
	    return new View.OnClickListener() {
	        public void onClick(View v) {
	        	Intent in = new Intent(ContactListActivity.this, SimpleChatActivity.class);
	        	Bundle b = new Bundle();
	        	b.putString("loggedAccountName", user.getAccountName());
	        	b.putByteArray("loggedPicture", user.getAccountPicture());
				b.putString("partnerAccountName", userList.get(v.getId()).getAlias().toString());
				b.putByteArray("partnerPicture", userList.get(v.getId()).getAccountPicture());
				in.putExtras(b);
				startActivity(in);
	        }
	    };
}
    public ArrayList<User> getFriends(){
    	ArrayList<User> friends = new ArrayList<User>();
    	Gson gs = new Gson();
    	String jsonString = "";
			jsonString = gs.toJson(user.getAccountID());
			StringEntity se;
			try {
				se = new StringEntity(jsonString);
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost request = new HttpPost(SERVICE_URI+ "/GetRelationship");    // auf die Felder AccountName + loginUsernamezugreifen
			request.setEntity(se);
			request.setHeader("Accept", "application/json");
			request.setHeader("Content-type", "application/json");
			HttpResponse response = null;
			response = httpClient.execute(request);
			HttpEntity responseEntity = response.getEntity();
			InputStream stream = null;
			stream = responseEntity.getContent();
			friends = gs.fromJson(stream.toString(), new TypeToken<List<User>>(){}.getType());
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return friends;
    }
}