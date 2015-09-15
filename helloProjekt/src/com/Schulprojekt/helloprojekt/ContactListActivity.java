package com.Schulprojekt.helloprojekt;

import java.util.ArrayList;

import com.Schulprojekt.helloprojekt.GUILogik.ContactListEntry;
import com.Schulprojekt.helloprojekt.GUILogik.ContactListLogik;
import com.Schulprojekt.helloprojekt.GUILogik.User;

import android.R.color;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class ContactListActivity extends Activity {

	ArrayList<User> userList = new ArrayList<User>();
	ArrayList<ContactListEntry> contactList;
	User u1 = new User("test", "test", "test",true);
	User u2 = new User("test", "test", "test",true);
	ImageView imgV;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_list);
		
		//contactList = conlog.fillList(userList, this);
		imgV = (ImageView) findViewById(R.id.imageView1);
		final LinearLayout linlayoutVertical = (LinearLayout) findViewById(R.id.linLayoutContactVertical);
		final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollViewContact);
		final TextView textV = (TextView) findViewById(R.id.textViewContact);
		
		imgV.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(ContactListActivity.this, ChatActivity.class);
				in.putExtra("ContactImage", (CharSequence) imgV.getBackground());
				startActivity(in);
				
//				ContactListLogik conlog = new ContactListLogik();
//				userList.add(u1);
//				userList.add(u2);
//				contactList = conlog.fillList(userList, getApplicationContext());
//				textV.setText("test");
//				for (ContactListEntry contact : contactList) {
//					LinearLayout lilayout = contact.getLinlayout();
//					lilayout.setOrientation(LinearLayout.HORIZONTAL);
//					contact.getContactPicture().setOnClickListener(conlog.getOnKlickListener(contact.getContactPicture()));
//					lilayout.addView(contact.getContactPicture());
//					lilayout.addView(contact.getAlias());
//					linlayoutVertical.addView(lilayout);
//				}
			}
		});
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
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
