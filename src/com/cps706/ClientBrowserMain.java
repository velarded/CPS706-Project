package com.cps706;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ClientBrowserMain extends JFrame
{
	private JTextField urlField;
	private JEditorPane editorPane;
//	private JButton goButton;
	
	public ClientBrowserMain() throws IOException
	{
		super("Client");
		setSize(500,500);
		//Make panel
		JPanel panel = new JPanel();
		urlField = new JTextField(35);
		//Button b; b = new Button ("Enter");
		
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
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		panel.add(urlField);
		//panel.add(b);
		//Setting page display
		String content = "<head><title>HisCinema</title></head>"
				+"<body><h1>Videos</h1>"
				+"<a href=\"http://video.hiscinema.com/F1\">Video 1</a><br>"
				+"<a href=\"http://video.hiscinema.com/F2\">Video 2</a><br>"
				+"<a href=\"http://video.hiscinema.com/F3\">Video 3</a><br>"
				+"<a href=\"http://video.hiscinema.com/F4\">Video 4</a><br>"
				+"</body>";
//		editorPane.setPage("http://");
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		try{
			editorPane.setPage("http://localhost");
		}catch (IOException ex){
		editorPane.setContentType("text/html");
		}
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.NORTH);
		getContentPane().add(new JScrollPane(editorPane), BorderLayout.CENTER);	
	}
	
	//Contacts the local DNS using UDP socket
	public void goToLocalDNS() throws Exception
	{
		//Create client UDP socket 
		DatagramSocket clientSocket = new DatagramSocket();
		//Assuming that url value will be www.hiscinema.com
		String url = urlField.getText().replace("www.", "");
		
		//Uses local ip address
		InetAddress ipAddr = InetAddress.getByName(MainConfiguration.localdnsIP());
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		sendData = url.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ipAddr,MainConfiguration.udpPort());
		
		System.out.println("Contacting local DNS using UDP to resolve: "+url);
		clientSocket.send(sendPacket);
		
		if(url.equals("hiscinema.com"))
		{
			//Receives UDP packet
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			
			//contains IP of hiscinema.com
			String modifiedSentence = new String(receivePacket.getData());
			System.out.println("FROM SERVER:" + modifiedSentence);
			clientSocket.close();
			
			Socket TCPclientSocket = new Socket("modifiedSentence", MainConfiguration.hisCinemaServerPort());
			

			System.out.println("set html");
			String content = "<head><title>HisCinema</title></head>"
					+"<body><h1>Videos</h1>"
					+"<a href=\"http://video.hiscinema.com/F1\">Video 1</a><br>"
					+"<a href=\"http://video.hiscinema.com/F2\">Video 2</a><br>"
					+"<a href=\"http://video.hiscinema.com/F3\">Video 3</a><br>"
					+"<a href=\"http://video.hiscinema.com/F4\">Video 4</a><br>"
					+"</body>";
			File indexHTML = new File("index.html");
			BufferedReader br = new BufferedReader(new FileReader(indexHTML));
			String line = null;
			while((line = br.readLine()) != null)
			{
				System.out.println(line);
			}
			
			//set page of edit, will be the resolved IP address of hisCinema.
			editorPane.setPage(new URL("http://localhost"));
		}
		
	}
	
	public static void main(String[] args) throws IOException
	{
		ClientBrowserMain client = new ClientBrowserMain();
		client.show();
	}


}
