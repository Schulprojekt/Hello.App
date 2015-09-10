package com.Schulprojekt.helloprojekt.GUILogik;

import java.util.UUID;

public class User {

	private UUID 	AccountID;
	private String 	AccountName;
	private String 	Alias;
	private String	Password;
	private Boolean AccountState;
	private Integer Experienced;
	private Byte[]	AccountPicture;	
	
	public User(UUID accountID, String accountName, String alias,
			Boolean accountState, Integer experienced, Byte[] accountPicture) {
		super();
		AccountID = accountID;
		AccountName = accountName;
		Alias = alias;
		AccountState = accountState;
		Experienced = experienced;
		AccountPicture = accountPicture;
	}
	public User(String accountName, String alias, String password,
			Boolean accountState) {
		super();
		AccountName = accountName;
		Alias = alias;
		Password = password;
		AccountState = accountState;
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

	public Boolean getAccountState() {
		return AccountState;
	}
	public void setAccountState(Boolean accountState) {
		AccountState = accountState;
	}
	public Integer getExperienced() {
		return Experienced;
	}
	public void setExperienced(Integer experienced) {
		Experienced = experienced;
	}
	public Byte[] getAccountPicture() {
		return AccountPicture;
	}
	public void setAccountPicture(Byte[] accountPicture) {
		AccountPicture = accountPicture;
	}
}
