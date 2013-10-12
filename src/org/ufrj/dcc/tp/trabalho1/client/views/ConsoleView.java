package org.ufrj.dcc.tp.trabalho1.client.views;

import org.ufrj.dcc.tp.trabalho1.client.ChatMessage;

public class ConsoleView implements View {

	@Override
	public void showMessage(ChatMessage message) {
		if (message.getFromClientId()==0){
			System.out.println("SERVIDOR: "+message.getMessage());
		} else {
			System.out.println("<ID:"+message.getFromClientId()+"> disse: "+message.getMessage());
		}
	}

}
