package com.Schulprojekt.helloprojekt;

import java.util.ArrayList;

import com.Schulprojekt.helloprojekt.GUILogik.ContactListEntry;
import com.Schulprojekt.helloprojekt.GUILogik.ContactListLogik;
import com.Schulprojekt.helloprojekt.GUILogik.User;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class ContactListActivity extends Activity {

	ArrayList<User> userList;
	ArrayList<ContactListEntry> contactList;
	//ContactListLogik conlog = new ContactListLogik();
	User u1 = new User("test", "test", "test",true);
	User u2 = new User("test", "test", "test",true);
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_list);
		//contactList = conlog.fillList(userList, this);
		final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollViewContact);
		scrollView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v == scrollView)
				{
				userList.add(u1);
				userList.add(u2);
				//contactList = conlog.fillList(userList, null);
				for (ContactListEntry contact : contactList) {
					LinearLayout lilayout = contact.getLinlayout();
					//conlog.getOnKlickListener(contact.getContactPicture());
					lilayout.addView(contact.getContactPicture());
					lilayout.addView(contact.getAlias());
					scrollView.addView(lilayout);
				}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact_list, menu);
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
