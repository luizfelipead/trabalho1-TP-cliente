package org.ufrj.dcc.tp.trabalho1.client;

import java.io.IOException;

public class ServerMessageManager extends Thread {
	
	private ClientSocket clientSocket;

	public ServerMessageManager(ClientSocket clientSocket) throws IOException {
		super();
		this.clientSocket = clientSocket;
		
		while(true){
			String fromServer = clientSocket.getIn().readLine();
			while (fromServer!=null){
				System.out.println(fromServer);
			}
		}
	}
	
	

}
