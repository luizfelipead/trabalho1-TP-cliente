package org.ufrj.dcc.tp.trabalho1.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Timer;

public class ClientSocket extends Thread {
	
	public static final String GOODBYE_MSG = "EXIT";

	private static final long TIME_TO_PING_SERVER = 10000;
	
	private Socket socket;
	private PrintStream out;
	private Scanner in;
	
	private PingServerThread pingTimerTask;
	
	public ClientSocket(String address, int port) {
		super();
		//1. creating a socket to connect to the server
		try{
			socket = new Socket(address, port);
			System.out.println("[INFO] Client Socket Connected to "+socket.getInetAddress());
			//2. get Input and Output streams
			this.out = new PrintStream(socket.getOutputStream());
			this.in = new Scanner(socket.getInputStream());
			
			pingTimerTask = new PingServerThread(socket,out);
			Timer timer = new Timer();
			timer.schedule(pingTimerTask, 0, TIME_TO_PING_SERVER);
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
