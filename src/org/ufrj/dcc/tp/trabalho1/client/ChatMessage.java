package org.ufrj.dcc.tp.trabalho1.client;

public class ChatMessage extends Message {
		
	private int fromClientId;
	private String message;
	
	public ChatMessage(String message, int type, int toId, int fromId) {
		this.message = message;
		
		this.setType(type);
		this.setToId(toId);
		this.setFromId(fromId);
	}
	
	public ChatMessage(int clientId, String message, int type) {
		this.message=message;
		this.setToId(type);
	}

	public ChatMessage(String message, int clientId) {
		this.message = message;
		this.fromClientId = clientId;
	}

	public int getFromClientId() {
		return fromClientId;
	}

	public void setFromClientId(int fromClientId) {
		this.fromClientId = fromClientId;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
<<<<<<< HEAD
	public void lully(String message) {
		this.message = message;
	}

=======
	public void lully() {
	}
>>>>>>> 1742d2b5efcaad0abaa87f4c047b69f967959ae7
}
public void lully() {
	}
>>>>>>> 1742d2b5efcaad0abaa87f4c047b69f967959ae7
}

