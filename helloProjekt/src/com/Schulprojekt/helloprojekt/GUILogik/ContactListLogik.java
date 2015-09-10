package com.Schulprojekt.helloprojekt.GUILogik;

import java.util.ArrayList;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactListLogik {

	int i = 0;
	
	public ArrayList<ContactListEntry> fillList(ArrayList<User> userList, Context con){
		ArrayList<ContactListEntry> contactList = new ArrayList<ContactListEntry>();
		for (User user : userList) {
			ContactListEntry entry = new ContactListEntry();
			LinearLayout linlay = new LinearLayout(con);
			ImageView img = new ImageView(con);
			TextView tv = new TextView(con);
			tv.setText(user.getAlias());
			entry.setLinlayout(linlay);
			entry.setContactPicture(img);
			entry.setAccountName(user.getAccountName());
			entry.setAlias(tv);
			contactList.add(entry);
		}
		return contactList;
	}
}
