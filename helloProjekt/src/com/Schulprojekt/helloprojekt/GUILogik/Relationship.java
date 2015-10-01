package com.Schulprojekt.helloprojekt.GUILogik;

import java.util.UUID;

public class Relationship {																					//hier wird ein Objekt erstellt, dass dem Server übergeben werden soll
	private UUID friendsId;																					//Deklaration
	private UUID userId;
	
	public Relationship(UUID friendsId, UUID userId) {
		super();
		this.friendsId = friendsId;
		this.userId = userId;
	}
	public UUID getFriendsId() {
		return friendsId;
	}
	public void setFriendsId(UUID friendsId) {
		this.friendsId = friendsId;
	}
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	
	

}
