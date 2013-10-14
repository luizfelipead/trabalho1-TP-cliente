package org.ufrj.dcc.tp.trabalho1.client.views;

import org.ufrj.dcc.tp.trabalho1.client.ChatMessage;
import org.ufrj.dcc.tp.trabalho1.client.ListClientsMessage;

public interface View {
	public void showMessage(ChatMessage message);
	public void showClients(ListClientsMessage message);
}
