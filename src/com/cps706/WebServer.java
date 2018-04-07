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
	
	public void start()
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

				System.out.println("Listening for client sockets on port "+webServerPort+"...");
				while(true)
				{
//					System.out.println(serverSocket.getLocalSocketAddress());
//					System.out.println("Listening for client sockets on port "+webServerPort+"...");
					Socket clientSocket = serverSocket.accept();
					System.out.println("Received client socket!");
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
