package com.Schulprojekt.helloprojekt.GUILogik;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ContactListLogik {

	int i = 0;
	
	public ArrayList<ContactListEntry> fillList(List<User> userList, Context con){
		ArrayList<ContactListEntry> contactList = new ArrayList<ContactListEntry>();
		for (User user : userList) {
			ContactListEntry entry = new ContactListEntry();
			LinearLayout linlay = new LinearLayout(con);
			ImageView img = new ImageView(con);
			
			entry.setLinlayout(linlay);
			entry.setContactPicture(img);
			entry.setAccountName(user.getAccountName());
			entry.setAlias(user.getAlias());
			contactList.add(entry);
		}
		return contactList;
	}
}
