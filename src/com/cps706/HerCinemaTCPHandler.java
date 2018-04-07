package com.cps706;
import java.io.*;
import java.net.*;

public class HerCinemaTCPHandler implements Runnable
{
	private Socket clientSocket;
	public HerCinemaTCPHandler(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() 
	{
		try
		{
			//Create InputStream from client socket to listen for request.
			DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
			//Create OutputStream from client socket to send out the selected video file
			DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
			
			//Close input and output streams
			dis.close();
			dos.close();
			//Close the client socket
			clientSocket.close();
		}
		catch(Exception e)
		{
			
		}
	}
}
