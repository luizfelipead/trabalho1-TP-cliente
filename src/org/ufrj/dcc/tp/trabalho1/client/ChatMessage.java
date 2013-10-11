package org.ufrj.dcc.tp.trabalho1.client;

public class ChatMessage {
	
	private int clientId;
	private String message;
	
	public ChatMessage(String message) {
		this.message=message;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
