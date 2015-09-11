package com.Schulprojekt.helloprojekt.GUILogik;

import java.util.ArrayList;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactListLogik {

	int i = 0;
	
	public ArrayList<ContactListEntry> fillList(ArrayList<User> userList, Context con){
		ArrayList<ContactListEntry> contactList = new ArrayList<ContactListEntry>();
		for (User user : userList) {
			
			LinearLayout linlay = new LinearLayout(con);
			ImageView img = new ImageView(con);
			TextView tv = new TextView(con);
			tv.setText(user.getAlias());
			ContactListEntry entry = new ContactListEntry(linlay, img, tv, user.getAccountName());
			contactList.add(entry);
		}
		return contactList;
	}
	
	public View.OnClickListener getOnKlickListener(ImageView img){
		    return new View.OnClickListener() {
		        public void onClick(View v) {
		        	
		        }
		    };
	}
}
