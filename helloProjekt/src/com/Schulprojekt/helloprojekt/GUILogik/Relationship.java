package com.Schulprojekt.helloprojekt.GUILogik;


public class Relationship {																					//hier wird ein Objekt erstellt, dass dem Server übergeben werden soll
	private int id;
	private int friendsId;																					//Deklaration
	private int userId;
	
	public Relationship(int friendsId, int userId) {
		super();
		this.id = 0;
		this.friendsId = friendsId;
		this.userId = userId;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFriendsId() {
		return friendsId;
	}
	public void setFriendsId(int friendsId) {
		this.friendsId = friendsId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

}
