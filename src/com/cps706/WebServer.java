package com.cps706;

import java.net.ServerSocket;
import java.net.Socket;

public class WebServer 
{
	private int webServerPort;
	public WebServer(int webServerPort)
	{
		this.webServerPort = webServerPort;
	}
	
	public void createMainThread()
	{
		WebServerMainThread wsmt = new WebServerMainThread();
		wsmt.run();
	}
	
	class WebServerMainThread implements Runnable{
		
		@Override
		public void run()
		{
			try 
			{
				ServerSocket serverSocket = new ServerSocket(webServerPort);
				while(true)
				{
					Socket clientSocket = serverSocket.accept();
					TCPClientHandlerThread tcht = new TCPClientHandlerThread(clientSocket);
					tcht.run();						
				}
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	
	
}
