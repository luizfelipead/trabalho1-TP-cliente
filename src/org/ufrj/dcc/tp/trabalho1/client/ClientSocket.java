package org.ufrj.dcc.tp.trabalho1.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket extends Thread {
	private int port = 0;
	private String address = "";
	
	private Socket socket;
	private DataOutputStream out;
	private BufferedReader in;
	
	public ClientSocket(String address, int port) {
		super();
		this.port = port;
		this.address = address;
		//1. creating a socket to connect to the server
		try{
			socket = new Socket(address, port);
			System.out.println("Client Socket Connected to "+socket.getLocalAddress());
			//2. get Input and Output streams
			this.out = new DataOutputStream(socket.getOutputStream());
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DataOutputStream getOut() {
		return out;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void close() throws IOException {
		this.in.close();
		this.out.close();
		this.socket.close();
		
	}
}
