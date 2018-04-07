package com.cps706;
import java.net.*;
import java.io.*;

public class HisCinemaTCPHandler implements Runnable{
	
	private Socket clientSocket;
	public HisCinemaTCPHandler(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
	}
	
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
