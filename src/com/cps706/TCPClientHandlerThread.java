package com.cps706;

import java.io.IOException;
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
		//Will need to return the response code through the client socket. 
		
		//Close socket once finished with servicing the request.
		try 
		{
			clientSocket.close();
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
