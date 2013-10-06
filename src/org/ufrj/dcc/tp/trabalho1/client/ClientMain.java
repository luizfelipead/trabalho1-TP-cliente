package org.ufrj.dcc.tp.trabalho1.client;

import java.io.IOException;
import java.util.Scanner;

public class ClientMain {

	public static void main(String[] args) throws IOException {
		ClientSocket clientSocket = null;
		Scanner sc = new Scanner(System.in);
		try{
			clientSocket = new ClientSocket("localhost", 2004);
			new ServerMessageManager(clientSocket).start();
			while (true){
				System.out.println("Digite o texto: ");
				String message = sc.nextLine();
				System.out.println(sc);
				clientSocket.getOut().writeBytes(message);
	
			}
		} catch (Exception e){
			if (clientSocket!=null){
				clientSocket.close();
				sc.close();
			}
				
		}
		
	}

}
