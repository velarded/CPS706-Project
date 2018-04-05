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
	
	public static int serverTcpPort() 
	{
		File configFile = new File(CONFIG_FILE_LOCATION);
		Properties properties = new Properties();
		int serverTcpPort=0;
		try
		{
			properties.load(new FileInputStream(configFile));
			serverTcpPort = Integer.parseInt(properties.getProperty("ServerTcpPort"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return serverTcpPort;
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
}
