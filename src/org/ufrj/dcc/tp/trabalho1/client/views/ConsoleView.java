package org.ufrj.dcc.tp.trabalho1.client.views;

import org.ufrj.dcc.tp.trabalho1.client.ChatMessage;
import org.ufrj.dcc.tp.trabalho1.client.ListClientsMessage;

public class ConsoleView implements View {

	@Override
	public void showMessage(ChatMessage message) {
		if (message.getFromClientId()==0){
			System.out.println("SERVIDOR: "+message.getMessage());
		} else {
			System.out.println("<ID:"+message.getFromClientId()+"> disse: "+message.getMessage());
		}
	}

	@Override
	public void showClients(ListClientsMessage message) {
		System.out.println("Clientes conectados:");
		for (Integer id : message.getClientsIds()) {
			System.out.println("<ID:"+id+">");
		}
	}
}
