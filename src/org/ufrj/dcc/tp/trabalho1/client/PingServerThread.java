package org.ufrj.dcc.tp.trabalho1.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.TimerTask;

import com.google.gson.Gson;

//Thread que envia um ping para o servidor um ping para nao ser desconectado por timeout
public class PingServerThread extends TimerTask {
	
	private static final Gson GSON = new Gson();
	
	private Socket socket;

	private PrintStream out;
	
	public PingServerThread(Socket socket, PrintStream out) throws IOException{
		this.socket = socket;
		this.out = out;
	}
	
	public PrintStream getOut() {
		return out;
	}

	public void setOut(PrintStream out) {
		this.out = out;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run(){
		ChatMessage message = new ChatMessage("", ChatMessage.PING, Message.SERVER_ID, Message.PING_ID);
		out.println(GSON.toJson(message));
	}

}
