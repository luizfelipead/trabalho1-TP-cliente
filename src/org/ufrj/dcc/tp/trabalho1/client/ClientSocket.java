package org.ufrj.dcc.tp.trabalho1.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocket extends Thread {
	
	private Socket socket;
	private PrintStream out;
	private Scanner in;
	
	public ClientSocket(String address, int port) {
		super();
		//1. creating a socket to connect to the server
		try{
			socket = new Socket(address, port);
			System.out.println("Client Socket Connected to "+socket.getLocalAddress());
			//2. get Input and Output streams
			this.out = new PrintStream(socket.getOutputStream());
			this.in = new Scanner(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public PrintStream getOut() {
		return out;
	}

	public Scanner getIn() {
		return in;
	}

	public void close() throws IOException {
		this.in.close();
		this.out.close();
		this.socket.close();
		
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	
}
