package com.cps706;

import java.net.*;
import java.io.*;

public class HisCinemaWebServer implements Runnable
{
	private int port;
	
	public HisCinemaWebServer(int port)
	{
		this.port = port;
	}
	
	@Override
	public void run() 
	{
		try
		{
			// TODO Auto-generated method stub
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Listening for TCP connections...");
			while(true)
			{
				Socket clientSocket = serverSocket.accept();
				System.out.println("Established a socket client connection!");
				HisCinemaTCPHandler tcpHandler = new HisCinemaTCPHandler(clientSocket);
				tcpHandler.run();
			}
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	public static void main(String[] args)
	{
		HisCinemaWebServer hisCinema = new HisCinemaWebServer(MainConfiguration.hisCinemaServerPort());
		hisCinema.run();
	}

}
