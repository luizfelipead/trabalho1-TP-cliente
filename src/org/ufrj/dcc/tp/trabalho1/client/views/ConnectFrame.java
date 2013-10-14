package org.ufrj.dcc.tp.trabalho1.client.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.ufrj.dcc.tp.trabalho1.client.ClientSocket;
import org.ufrj.dcc.tp.trabalho1.client.ServerMessageReceiverThread;

public class ConnectFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfIp;
	private JTextField tfPort;
	private JButton btnConnectButton;
	private static ConnectFrame instance;
	
	public static ConnectFrame getInstance() {
		if(instance == null) instance = new ConnectFrame();
		return instance;
	}
	/**
	 * Create the frame.
	 */
	public ConnectFrame() {
		setResizable(false);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfIp = new JTextField("localhost");
		tfIp.setBounds(62, 38, 349, 19);
		contentPane.add(tfIp);
		tfIp.setColumns(10);
		
		JLabel lblIp = new JLabel("Ip :");
		lblIp.setBounds(25, 38, 70, 15);
		contentPane.add(lblIp);
		
		tfPort = new JTextField("2004");
		tfPort.setColumns(10);
		tfPort.setBounds(62, 87, 349, 19);
		contentPane.add(tfPort);
		
		JLabel lblPorta = new JLabel("Porta :");
		lblPorta.setBounds(12, 89, 70, 15);
		contentPane.add(lblPorta);
		
		btnConnectButton = new JButton("Conectar");
		btnConnectButton.setBounds(173, 133, 117, 25);
		btnConnectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClientView view = ClientView.getInstance();
				view.setClientSocket(new ClientSocket(tfIp.getText(), new Integer(tfPort.getText())));
				ServerMessageReceiverThread serverMessageManager;
				try {
					serverMessageManager = new ServerMessageReceiverThread(view.getClientSocket(), view, view.getClient());
					serverMessageManager.start();
					ConnectFrame.getInstance().setVisible(false);
				} catch (IOException e) {
					e.printStackTrace();
				}			
			}
		});
		contentPane.add(btnConnectButton);
	}
}