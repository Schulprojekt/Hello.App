package com.Schulprojekt.helloprojekt.GUILogik;

public class ContactListEntry {

	private byte[] ContactPicture;
	private String alias;
	private String accountName;
	public byte[] getContactPicture() {
		return ContactPicture;
	}
	public void setContactPicture(byte[] contactPicture) {
		ContactPicture = contactPicture;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
