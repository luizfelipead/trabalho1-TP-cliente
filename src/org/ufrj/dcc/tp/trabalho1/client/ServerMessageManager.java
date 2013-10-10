package org.ufrj.dcc.tp.trabalho1.client;

import java.io.IOException;

public class ServerMessageManager extends Thread {
	
	private ClientSocket clientSocket;

	public ServerMessageManager(ClientSocket clientSocket) throws IOException {
		super();
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run(){
		while(true){
			String fromServer;
			while (clientSocket.getIn().hasNextLine()){
				fromServer = this.clientSocket.getIn().nextLine();
				System.out.println(fromServer);
				
			}
			
		}
	}
	
	

}
