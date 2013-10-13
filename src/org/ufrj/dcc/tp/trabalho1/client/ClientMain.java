package org.ufrj.dcc.tp.trabalho1.client;

import java.io.IOException;
import java.util.Scanner;

import org.ufrj.dcc.tp.trabalho1.client.views.ConsoleView;

import com.google.gson.Gson;

public class ClientMain {

	public static void main(String[] args) throws IOException {
		ClientSocket clientSocket = null;
		Scanner sc = new Scanner(System.in);
		final Gson GSON = new Gson();
		
		try{
			clientSocket = new ClientSocket("localhost", 2004);
			ServerMessageReceiverThread serverMessageManager = new ServerMessageReceiverThread(clientSocket, new ConsoleView());
			serverMessageManager.start();
			while (true){
				String message = sc.nextLine();
				if (message.equals(ClientSocket.GOODBYE_MSG)){
					clientSocket.close();
					System.exit(0);
					clientSocket.getOut().println(message);
				} else{
					ChatMessage chatMessage = new ChatMessage(message, ChatMessage.PUBLIC_MESSAGE);
					clientSocket.getOut().println(GSON.toJson(chatMessage));
				}
			}
		} catch (Exception e){
			if (clientSocket!=null){
				clientSocket.close();
			}
		}
	}

}
