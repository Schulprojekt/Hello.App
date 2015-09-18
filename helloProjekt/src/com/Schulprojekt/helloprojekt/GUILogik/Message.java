package com.Schulprojekt.helloprojekt.GUILogik;

import java.sql.Timestamp;
import java.util.UUID;

public class Message {
	
	private int MessageID;
	private UUID Receiver;
	private UUID Sender;
	private String MessageText;
	private Byte[] Attachement;
	private Timestamp MessageTime;
	
	public Message(int messageID, UUID receiver, UUID sender,
			String messageText, Byte[] attachement, Timestamp messageTime) {
		super();
		MessageID = messageID;
		Receiver = receiver;
		Sender = sender;
		MessageText = messageText;
		Attachement = attachement;
		MessageTime = messageTime;
	}
	
	public int getMessageID() {
		return MessageID;
	}
	
	public void setMessageID(int messageID) {
		MessageID = messageID;
	}
	
	public UUID getReceiver() {
		return Receiver;
	}
	
	public void setReceiver(UUID receiver) {
		Receiver = receiver;
	}
	
	public UUID getSender() {
		return Sender;
	}
	
	public void setSender(UUID sender) {
		Sender = sender;
	}
	
	public String getMessageText() {
		return MessageText;
	}
	
	public void setMessageText(String messageText) {
		MessageText = messageText;
	}
	
	public Byte[] getAttachement() {
		return Attachement;
	}
	
	public void setAttachement(Byte[] attachement) {
		Attachement = attachement;
	}
	
	public Timestamp getMessageTime() {
		return MessageTime;
	}
	
	public void setMessageTime(Timestamp messageTime) {
		MessageTime = messageTime;
	}
}
