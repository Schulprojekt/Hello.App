package com.Schulprojekt.helloprojekt.GUILogik;


public class User {																							//hier wird ein Objekt erstellt, dass dem Server übergeben werden soll
	private int 	accountID;																				//Deklaration
	private String 	accountName;
	private String 	alias;
	private String	password;
	private byte[]	accountPicture;
	public User(){
		
	}
	public User(int i, String accountName, String alias, byte[] accountPicture) {
		super();
		this.accountID = i;
		this.accountName = accountName;
		this.alias = alias;
		this.accountPicture = accountPicture;
	}
	public User(int i, String accountName, String alias, String password, byte[] accountPicture) {
		super();
		this.accountID = i;
		this.accountName = accountName;
		this.alias = alias;
		this.password = password;
		this.accountPicture = accountPicture;
	}
	public User(String accountName, String alias, String password) {
		super();
		this.accountName = accountName;
		this.alias = alias;
		this.password = password;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public byte[] getAccountPicture() {
		return accountPicture;
	}
	public void setAccountPicture(byte[] bs) {
		this.accountPicture = bs;
	}
}
