package com.Schulprojekt.helloprojekt.GUILogik;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ContactListEntry {

	LinearLayout linlayout;
	ImageView ContactPicture;
	private TextView alias;
	private String accountName;
	
	public LinearLayout getLinlayout() {
		return linlayout;
	}
	public void setLinlayout(LinearLayout linlayout) {
		this.linlayout = linlayout;
	}
	public ImageView getContactPicture() {
		return ContactPicture;
	}
	public void setContactPicture(ImageView contactPicture) {
		ContactPicture = contactPicture;
	}
	public TextView getAlias() {
		return alias;
	}
	public void setAlias(TextView alias) {
		this.alias = alias;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}