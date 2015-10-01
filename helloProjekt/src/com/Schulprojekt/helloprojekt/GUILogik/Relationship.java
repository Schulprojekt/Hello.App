package com.Schulprojekt.helloprojekt.GUILogik;

import java.util.UUID;

public class Relationship {
	private int id;
	private UUID friendsId;
	private UUID userId;
	
	public Relationship(int id, UUID friendsId, UUID userId) {
		super();
		this.id = id;
		this.friendsId = friendsId;
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
