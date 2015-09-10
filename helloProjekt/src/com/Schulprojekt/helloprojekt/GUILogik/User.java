package com.Schulprojekt.helloprojekt.GUILogik;

import java.sql.Blob;

public class User {

	private Long 	AccountID;
	private String 	name;
	private String 	firstName;
	private String 	AccountName;
	private Boolean AccountState;
	private Integer Experienced;
	private Blob	AccountPicture;
	
	public Long getAccountID() {
		return AccountID;
	}
	public void setAccountID(Long accountID) {
		AccountID = accountID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAccountName() {
		return AccountName;
	}
	public void setAccountName(String accountName) {
		AccountName = accountName;
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
	public Blob getAccountPicture() {
		return AccountPicture;
	}
	public void setAccountPicture(Blob accountPicture) {
		AccountPicture = accountPicture;
	}
}
