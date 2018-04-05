package com.cps706;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientBrowserMain extends JFrame
{
	private JTextField urlField;
//	private JButton goButton;
	
	public ClientBrowserMain()
	{
		super("Client");
		setSize(500,500);
		//Make panel
		JPanel panel = new JPanel();
		urlField = new JTextField(35);
		urlField.addKeyListener(new KeyAdapter()
		{
			public void keyReleased(KeyEvent ke)
			{
				if(ke.getKeyCode() == KeyEvent.VK_ENTER)
					try 
					{
						goToLocalDNS();
					} catch (Exception e)
					{
						e.printStackTrace();
					}
			}
		});
		
		panel.add(urlField);
		
	}
	
	//Contacts the local DNS using UDP socket
	public void goToLocalDNS() throws Exception
	{
		//Create client UDP socket
		DatagramSocket clientSocket = new DatagramSocket();
		//Assuming that url value will be hiscinema.com
		String url = urlField.getText();
		
		//Uses local ip address
		InetAddress ipAddr = InetAddress.getLocalHost();
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		sendData = url.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ipAddr,MainConfiguration.udpPort());
		
		System.out.println("Contacting local DNS using UDP.");
		clientSocket.send(sendPacket);
		
		//Receives
		DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
		
		
	}
	
	public static void main(String[] args)
	{
		ClientBrowserMain client = new ClientBrowserMain();
		client.show();
	}


}
