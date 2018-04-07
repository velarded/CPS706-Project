package com.cps706;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class MainConfiguration 
{
	public final static String CONFIG_FILE_LOCATION = "resources/config.txt";
//	public static int serverTcpPort;
//	public static String hisCinemaIP;
//	public static int udpPort;
	
	public MainConfiguration()
	{
//		File configFile = new File(CONFIG_FILE_LOCATION);
//		Properties properties = new Properties();
//		
//		try
//		{
//			properties.load(new FileInputStream(configFile));
//			serverTcpPort = Integer.parseInt(properties.getProperty("ServerTcpPort"));
//			hisCinemaIP = properties.getProperty("HisCinemaIP");
//			udpPort = Integer.parseInt(properties.getProperty("UdpPort"));
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
	}
	
	public static int hisCinemaServerPort() 
	{
		File configFile = new File(CONFIG_FILE_LOCATION);
		Properties properties = new Properties();
		int hisCinemaServerPort=0;
		try
		{
			properties.load(new FileInputStream(configFile));
			hisCinemaServerPort = Integer.parseInt(properties.getProperty("HisCinemaServerPort"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return hisCinemaServerPort;
	}
	
	public static String hisCinemaIP() 
	{
		File configFile = new File(CONFIG_FILE_LOCATION);
		Properties properties = new Properties();
		String hisCinemaIP=null;
		try
		{
			properties.load(new FileInputStream(configFile));
			hisCinemaIP = properties.getProperty("ServerTcpPort");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return hisCinemaIP;
	}
	
	public static int udpPort() 
	{
		File configFile = new File(CONFIG_FILE_LOCATION);
		Properties properties = new Properties();
		int udpPort=0;
		try
		{
			properties.load(new FileInputStream(configFile));
			udpPort = Integer.parseInt(properties.getProperty("UdpPort"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return udpPort;
	}
	
	public static int herCinemaServerPort() 
	{
		File configFile = new File(CONFIG_FILE_LOCATION);
		Properties properties = new Properties();
		int herCinemaServerPort=0;
		try
		{
			properties.load(new FileInputStream(configFile));
			herCinemaServerPort = Integer.parseInt(properties.getProperty("HerCinemaServerPort"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return herCinemaServerPort;
	}
}
