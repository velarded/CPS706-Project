import java.net.*;
import java.io.*;

public class HerCDNServer implements Runnable
{
	private int port;
	public HerCDNServer(int port)
	{
		this.port = port;
	}
	
	@Override
	public void run() 
	{
		try
		{
			ServerSocket serverSocket = new ServerSocket(MainConfiguration.herCinemaServerPort());
			System.out.println("HerCinema Content Server waiting for client connections...");
			while(true)
			{
				Socket clientSocket = serverSocket.accept();
				System.out.println("Received client connection!");
				HerCDNTCPHandler tcpHandler = new HerCDNTCPHandler(clientSocket);
				tcpHandler.run(); 
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	public static void main(String[] args)
	{
		HerCDNServer webServer = new HerCDNServer(MainConfiguration.herCinemaServerPort());
		webServer.run();
	}
	
}
