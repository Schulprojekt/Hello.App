package com.Schulprojekt.helloprojekt.GUILogik;

import java.util.UUID;

public class User {

	private UUID 	AccountID;
	private String 	AccountName;
	private String 	Alias;
	private Boolean AccountState;
	private Integer Experienced;
	private Byte[]	AccountPicture;
	
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
