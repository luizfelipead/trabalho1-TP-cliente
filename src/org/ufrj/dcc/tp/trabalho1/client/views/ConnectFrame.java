package org.ufrj.dcc.tp.trabalho1.client.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.ufrj.dcc.tp.trabalho1.client.Client;
import org.ufrj.dcc.tp.trabalho1.client.ClientSocket;
import org.ufrj.dcc.tp.trabalho1.client.ServerMessageReceiverThread;

public class ConnectFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfIp;
	private JTextField tfPort;
	private JButton btnConnectButton;
	private static ConnectFrame instance;
	
	private List<OnConnectListener> listeners;
	
	public static ConnectFrame getInstance() {
		if(instance == null) instance = new ConnectFrame();
		return instance;
	}
	/**
	 * Create the frame.
	 */
	private ConnectFrame() {
		setResizable(false);
		setBounds(100, 100, 450, 156);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setBounds(10, 13, 70, 15);
		contentPane.add(lblNome);
		
		tfName = new JTextField("Nome");
		tfName.setBounds(62, 13, 349, 19);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfIp = new JTextField("localhost");
		tfIp.setBounds(62, 48, 349, 19);
		contentPane.add(tfIp);
		tfIp.setColumns(10);
		
		JLabel lblIp = new JLabel("Ip :");
		lblIp.setBounds(37, 48, 70, 15);
		contentPane.add(lblIp);
		
		tfPort = new JTextField("2004");
		tfPort.setColumns(10);
		tfPort.setBounds(62, 83, 349, 19);
		contentPane.add(tfPort);
		
		JLabel lblPorta = new JLabel("Porta :");
		lblPorta.setBounds(12, 83, 70, 15);
		contentPane.add(lblPorta);
		
		btnConnectButton = new JButton("Conectar");
		btnConnectButton.setBounds(173, 118, 117, 25);
		btnConnectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClientView view = ClientView.getInstance();
				view.setClientSocket(new ClientSocket(tfIp.getText(), new Integer(tfPort.getText())));
				ServerMessageReceiverThread serverMessageManager;
				try {					
					Client client = view.getClient();
					client.setName(tfName.getText());
					
					serverMessageManager = new ServerMessageReceiverThread(view.getClientSocket(), view, client);
					serverMessageManager.start();
					ConnectFrame.getInstance().setVisible(false);
					
					for (OnConnectListener listener : listeners) {
						listener.onConnect();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}			
			}
		});
		contentPane.add(btnConnectButton);
		
		listeners = new ArrayList<OnConnectListener>();
	}
	
	
	public void addOnConnectListener(OnConnectListener onConnectListener) {
		this.listeners.add(onConnectListener);
	}
	
	public interface OnConnectListener {
		
		public void onConnect();
		
	}
	
}