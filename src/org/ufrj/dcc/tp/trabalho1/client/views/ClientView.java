package org.ufrj.dcc.tp.trabalho1.client.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import org.ufrj.dcc.tp.trabalho1.client.ChatMessage;
import org.ufrj.dcc.tp.trabalho1.client.ClientSocket;
import org.ufrj.dcc.tp.trabalho1.client.ListClientsMessage;

import com.google.gson.Gson;

public class ClientView implements View {
	private ClientSocket clientSocket;
	private JFrame frame;
	private ConnectFrame connectFrame;
	private static ClientView instance;
	private JTextPane chatArea;
	private JTextPane tpMessage;
	final Gson GSON = new Gson();
	
	public static ClientView getInstance(){
		if(instance == null) instance = new ClientView();
		return instance;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientView window = ClientView.getInstance();
					window.connectFrame = ConnectFrame.getInstance();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public ClientView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 550, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnTpMessager = new JMenu("TP Messager");
		menuBar.add(mnTpMessager);
		
		JMenuItem mntmConect = new JMenuItem("Conectar");
		mntmConect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				connectFrame.setVisible(true);
			}
		});
		mnTpMessager.add(mntmConect);
		
		JMenuItem mntmExit = new JMenuItem("Sair");
		mnTpMessager.add(mntmExit);
		frame.getContentPane().setLayout(null);
		
		chatArea = new JTextPane();
		chatArea.setEditable(false);
		chatArea.setBounds(12, 28, 318, 306);
		frame.getContentPane().add(chatArea);
		
		JList clients = new JList();
		clients.setBounds(342, 28, 196, 389);
		frame.getContentPane().add(clients);
		
		tpMessage = new JTextPane();
		tpMessage.setBounds(12, 363, 225, 54);
		frame.getContentPane().add(tpMessage);
		
		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChatMessage chatMessage = new ChatMessage(tpMessage.getText(), ChatMessage.PUBLIC_MESSAGE);
				clientSocket.getOut().println(GSON.toJson(chatMessage));
				tpMessage.setText("");
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBounds(249, 388, 81, 29);
		frame.getContentPane().add(btnNewButton);
	}

	public ClientSocket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(ClientSocket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void showMessage(ChatMessage message) {
		StyledDocument doc = chatArea.getStyledDocument();
		try {
			if (message.getFromClientId()==0){
				doc.insertString(doc.getLength(), "SERVIDOR: "+message.getMessage()+"\n", null);
			} else {
				doc.insertString(doc.getLength(), "<ID:"+message.getFromClientId()+"> disse: "+message.getMessage()+"\n", null);
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showClients(ListClientsMessage message) {
		
	}
}
