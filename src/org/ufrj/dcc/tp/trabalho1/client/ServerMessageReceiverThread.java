package org.ufrj.dcc.tp.trabalho1.client;

import java.io.IOException;

import org.ufrj.dcc.tp.trabalho1.client.views.View;

import com.google.gson.Gson;

public class ServerMessageReceiverThread extends Thread {
	
	private ClientSocket clientSocket;
	private View view;

	private static final Gson GSON = new Gson();

	public ServerMessageReceiverThread(ClientSocket clientSocket, View view) throws IOException {
		super();
		this.view = view;
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run(){
		while(true){
			String fromServer;
			while(true){
				while (clientSocket.getIn().hasNextLine()){
					fromServer = this.clientSocket.getIn().nextLine();
					
					Message message = GSON.fromJson(fromServer, Message.class);
					
					if (message.getType() == Message.LIST_CLIENT) {
						view.showClients(GSON.fromJson(fromServer, ListClientsMessage.class));
					} else {
						view.showMessage(GSON.fromJson(fromServer, ChatMessage.class));
					}
				}
			}
			
		}
	}
}
