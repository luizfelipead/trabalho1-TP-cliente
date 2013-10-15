package org.ufrj.dcc.tp.trabalho1.client.views;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
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
import org.ufrj.dcc.tp.trabalho1.client.Client;
import org.ufrj.dcc.tp.trabalho1.client.ClientSocket;
import org.ufrj.dcc.tp.trabalho1.client.ListClientsMessage;
import org.ufrj.dcc.tp.trabalho1.client.Message;

import com.google.gson.Gson;

public class ClientView implements View {
	private ClientSocket clientSocket;
	private JFrame frame;
	private ConnectFrame connectFrame;
	private static ClientView instance;
	private JTextPane chatArea;
	private JTextPane tpMessage;
	final Gson GSON = new Gson();
	private JList clients;
	private Client client;
	
	
	private Map<Integer, String> clientsNames;
	
	
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
	private ClientView() {
		client = new Client(null, null);
		connectFrame = ConnectFrame.getInstance();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Chat");
		frame.setResizable(false);
		frame.setBounds(100, 100, 550, 469);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnTpMessenger = new JMenu("TP Messenger");
		menuBar.add(mnTpMessenger);
		
		final JMenuItem mntmConnect = new JMenuItem("Conectar");
		mntmConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				connectFrame.setVisible(true);
			}
		});
		mnTpMessenger.add(mntmConnect);
		
		JMenuItem mntmExit = new JMenuItem("Sair");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnTpMessenger.add(mntmExit);
		
		frame.getContentPane().setLayout(null);
		
		chatArea = new JTextPane();
		chatArea.setEditable(false);
		chatArea.setBounds(12, 28, 318, 306);
		frame.getContentPane().add(chatArea);
		
		clients = new JList();
		clients.setBounds(342, 28, 196, 389);
		frame.getContentPane().add(clients);
		
		tpMessage = new JTextPane();
		tpMessage.setBounds(12, 363, 225, 54);
		frame.getContentPane().add(tpMessage);
		
		final JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Client toClient = (Client) clients.getSelectedValue();
				ChatMessage chatMessage;
				
				if (toClient == null || toClient.getId() == Message.SERVER_ID) {
					chatMessage = new ChatMessage(tpMessage.getText(), ChatMessage.PUBLIC_MESSAGE, Message.SERVER_ID, getClient().getId());
				} else {
					chatMessage = new ChatMessage(tpMessage.getText(), ChatMessage.PRIVATE_MESSAGE, toClient.getId(), getClient().getId());
				}
				
				clientSocket.getOut().println(GSON.toJson(chatMessage));
				
				StyledDocument doc = chatArea.getStyledDocument();
				
				try {
					doc.insertString(doc.getLength(), "Eu disse: "+tpMessage.getText()+"\n", null);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				
				tpMessage.setText("");
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBounds(249, 388, 81, 29);
		
		connectFrame.addOnConnectListener(new ConnectFrame.OnConnectListener(){

			@Override
			public void onConnect() {
				mntmConnect.setEnabled(false);
				
				frame.setTitle(frame.getTitle() + " - " + client.getName());
			}
			
		});	
		
		
		tpMessage.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					btnNewButton.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					tpMessage.setText("");
				}
			}
			
		});
		

		
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
				doc.insertString(doc.getLength(), clientsNames.get(message.getFromClientId())+" disse: "+message.getMessage()+"\n", null);
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void showClients(ListClientsMessage message) {
		clientsNames = message.getClientsIds();
		
		DefaultListModel models = new DefaultListModel();
		
		models.addElement(new Client(Message.PUBLIC_MESSAGE, "Todos"));
		
		for (Entry<Integer, String> client : clientsNames.entrySet()) {
			models.addElement(new Client(client.getKey(), client.getValue()));
		}
		clients.setModel(models);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}