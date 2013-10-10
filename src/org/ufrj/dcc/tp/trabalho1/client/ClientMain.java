package org.ufrj.dcc.tp.trabalho1.client;

import java.io.IOException;
import java.util.Scanner;

public class ClientMain {

	public static void main(String[] args) throws IOException {
		ClientSocket clientSocket = null;
		Scanner sc = new Scanner(System.in);
		try{
			clientSocket = new ClientSocket("localhost", 2004);
			ServerMessageManager serverMessageManager = new ServerMessageManager(clientSocket);
			serverMessageManager.start();
			while (true){
				System.out.println("Digite o texto: ");
				String message = sc.nextLine();
				clientSocket.getOut().println(message);
			}
		} catch (Exception e){
			if (clientSocket!=null){
				clientSocket.close();
				sc.close();
			}
		}
	}

}
