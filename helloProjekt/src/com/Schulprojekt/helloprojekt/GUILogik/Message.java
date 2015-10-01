package com.Schulprojekt.helloprojekt.GUILogik;

import java.sql.Timestamp;

public class Message { // hier wird ein Objekt erstellt, dass dem Server
						// übergeben werden soll
	private int MessageID; // Deklaration
	private int Receiver;
	private int Sender;
	private String MessageText;
	private byte[] Attachement;
	private Timestamp MessageTime;

	public Message(int messageID, int receiver, int sender, String messageText,
			byte[] attachement, Timestamp messageTime) {
		super();
		MessageID = messageID;
		Receiver = receiver;
		Sender = sender;
		MessageText = messageText;
		Attachement = attachement;
		MessageTime = messageTime;
	}

	public Message(int uuid, int uuid2, String messageText) {
		super();
		Receiver = uuid;
		Sender = uuid2;
		MessageText = messageText;
		Attachement = null;
		MessageTime = new Timestamp(System.currentTimeMillis());
	}

	public int getMessageID() {
		return MessageID;
	}

	public void setMessageID(int messageID) {
		MessageID = messageID;
	}

	public int getReceiver() {
		return Receiver;
	}

	public void setReceiver(int receiver) {
		Receiver = receiver;
	}

	public int getSender() {
		return Sender;
	}

	public void setSender(int sender) {
		Sender = sender;
	}

	public String getMessageText() {
		return MessageText;
	}

	public void setMessageText(String messageText) {
		MessageText = messageText;
	}

	public byte[] getAttachement() {
		return Attachement;
	}

	public void setAttachement(byte[] attachement) {
		Attachement = attachement;
	}

	public Timestamp getMessageTime() {
		return MessageTime;
	}

	public void setMessageTime(Timestamp messageTime) {
		MessageTime = messageTime;
	}
}
