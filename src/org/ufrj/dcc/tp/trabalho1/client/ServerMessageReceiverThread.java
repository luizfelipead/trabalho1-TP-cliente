package org.ufrj.dcc.tp.trabalho1.client;

import java.io.IOException;

import com.google.gson.Gson;

public class ServerMessageReceiverThread extends Thread {
	
	private ClientSocket clientSocket;

	private static final Gson GSON = new Gson();

	public ServerMessageReceiverThread(ClientSocket clientSocket) throws IOException {
		super();
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run(){
		while(true){
			String fromServer;
			while(true){
				while (clientSocket.getIn().hasNextLine()){
					fromServer = this.clientSocket.getIn().nextLine();
					ChatMessage message = GSON.fromJson(fromServer, ChatMessage.class);
					System.out.println("<ID:"+message.getClientId()+"> disse: "+message.getMessage());
					
				}
			}
			
		}
	}
	
	

}
