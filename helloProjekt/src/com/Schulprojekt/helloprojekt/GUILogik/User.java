package com.Schulprojekt.helloprojekt.GUILogik;

import java.util.UUID;

public class User {																							//hier wird ein Objekt erstellt, dass dem Server übergeben werden soll
	private UUID 	AccountID;																				//Deklaration
	private String 	AccountName;
	private String 	Alias;
	private String	Password;
	private byte[]	AccountPicture;	
	public User(){
		
	}
	public User(UUID i, String accountName, String alias, byte[] accountPicture) {
		super();
		AccountID = i;
		AccountName = accountName;
		Alias = alias;
		AccountPicture = accountPicture;
	}
	public User(String accountName, String alias, String password) {
		super();
		AccountName = accountName;
		Alias = alias;
		Password = password;
	}
	public UUID getAccountID() {
		return AccountID;
	}
	public void setAccountID(UUID accountID) {
		AccountID = accountID;
	}
	public String getAccountName() {
		return AccountName;
	}
	public void setAccountName(String accountName) {
		AccountName = accountName;
	}
	public String getAlias() {
		return Alias;
	}
	public void setAlias(String alias) {
		Alias = alias;
	}
	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	public byte[] getAccountPicture() {
		return AccountPicture;
	}
	public void setAccountPicture(byte[] bs) {
		AccountPicture = bs;
	}
}
