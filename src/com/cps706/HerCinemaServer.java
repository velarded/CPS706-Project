package com.cps706;
import java.net.*;
import java.io.*;

public class HerCinemaServer implements Runnable
{
	private int port;
	public HerCinemaServer(int port)
	{
		this.port = port;
	}
	
	@Override
	public void run() 
	{
		try
		{
			ServerSocket serverSocket = new ServerSocket(port);
			while(true)
			{
				Socket clientSocket = serverSocket.accept();
				HerCinemaTCPHandler tcpHandler = new HerCinemaTCPHandler(clientSocket);
				tcpHandler.run();
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	public static void main(String[] args)
	{
		HerCinemaServer webServer = new HerCinemaServer(MainConfiguration.herCinemaServerPort());
		webServer.run();
	}
	
}
