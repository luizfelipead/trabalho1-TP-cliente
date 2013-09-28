package org.ufrj.dcc.tp.trabalho1.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket extends Thread {
	private int port = 0;
	private String address = "";
	
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private String message;
	
	public ClientSocket(int port, String address) {
		super();
		this.port = port;
		this.address = address;
		//1. creating a socket to connect to the server
		try{
			socket = new Socket(address, port);
			System.out.println("Client Socket Connected to "+socket.getLocalAddress());
			//2. get Input and Output streams
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
