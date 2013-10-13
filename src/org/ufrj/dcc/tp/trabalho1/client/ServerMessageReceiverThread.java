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
					ChatMessage message = GSON.fromJson(fromServer, ChatMessage.class);
					view.showMessage(message);
				}
			}
			
		}
	}
}
