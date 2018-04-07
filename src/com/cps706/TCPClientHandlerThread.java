package com.cps706;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
		try
		{
	     DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
	    File file = new File("index.html");
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String line = null;

	    outToClient.writeBytes("HTTP/1.1 200 OK\n");
	    outToClient.writeBytes("Content-Type: text/html\n");
	    outToClient.writeBytes("\r\n");
	    while((line = br.readLine()) != null)
	    {
	    	outToClient.writeBytes(line+"\n");
	    }
	    outToClient.flush();
	    outToClient.close();
	    clientSocket.close();
		}
		catch(Exception e)
		{

		}
	}

}
