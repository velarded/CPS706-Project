package com.cps706;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Socket;

/*
 * This class is responsible for handling the requests from the TCP client socket.  
 */
public class TCPClientHandlerThread implements Runnable{
	private Socket clientSocket;
	
	public TCPClientHandlerThread(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
	}
	
	//TODO: implement functionality for servicing request.
	@Override
	public void run() 
	{
		System.out.println("Received TCP request from client!");
		//Will need to return the response code through the client socket. 
		
		try 
		{
			//Create output stream attacked to socket		
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			//For now initially just send back index.html contents
			File indexHTML = new File("index.html");
			BufferedReader br = new BufferedReader(new FileReader(indexHTML));
			StringBuilder sb = new StringBuilder();
			String content = null;
			while((content = br.readLine())!=null)
			{
				sb.append(content);
			}
			outToServer.writeBytes(sb.toString());
			//Close socket once finished with servicing the request.
			clientSocket.close();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
