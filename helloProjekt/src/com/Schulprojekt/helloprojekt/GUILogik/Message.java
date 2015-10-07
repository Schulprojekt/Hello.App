package com.Schulprojekt.helloprojekt.GUILogik;

import java.sql.Timestamp;

public class Message { 																						//hier wird ein Objekt erstellt, dass dem Server übergeben werden soll
	private int messageID; 																					//Deklaration
	private int receiver;
	private int sender;
	private String messageText;
	private byte[] attachement;
	private Timestamp messageTime;

	public Message(int messageID, int receiver, int sender, String messageText,
			byte[] attachement, Timestamp messageTime) {
		super();
		this.messageID = messageID;
		this.receiver = receiver;
		this.sender = sender;
		this.messageText = messageText;
		this.attachement = attachement;
		this.messageTime = messageTime;
	}

	public Message(int uuid, int uuid2, String messageText) {
		super();
		this.receiver = uuid;
		this.sender = uuid2;
		this.messageText = messageText;
		this.attachement = null;
		this.messageTime = new Timestamp(System.currentTimeMillis());
	}

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public int getReceiver() {
		return receiver;
	}

	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public byte[] getAttachement() {
		return attachement;
	}

	public void setAttachement(byte[] attachement) {
		this.attachement = attachement;
	}

	public Timestamp getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(Timestamp messageTime) {
		this.messageTime = messageTime;
	}
}
