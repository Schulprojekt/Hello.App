package com.Schulprojekt.helloprojekt.GUILogik;

import java.util.ArrayList;

import com.Schulprojekt.helloprojekt.ChatActivity;
import com.Schulprojekt.helloprojekt.ContactListActivity;
import com.Schulprojekt.helloprojekt.R;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.DrawableRes;
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
			img.setImageResource(R.drawable.dummycontact);
			//img.setId(user.getAccountName());
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
