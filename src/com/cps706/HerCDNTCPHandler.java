package com.cps706;
import java.io.*;
import java.net.*;

public class HerCDNTCPHandler implements Runnable
{
	private Socket clientSocket;
	public HerCDNTCPHandler(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() 
	{
		try
		{
			//Create InputStream from client socket to listen for request.
			BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			//Create OutputStream from client socket to send out the selected video file
			DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
			String line = null;
			while((line = br.readLine()) != null)
			{
				System.out.println(line);
			}
			
			//Close input and output streams
			br.close();
			dos.close();
			//Close the client socket
			clientSocket.close();
		}
		catch(Exception e)
		{
			
		}
	}
}
