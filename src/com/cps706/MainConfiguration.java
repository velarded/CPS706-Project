package com.cps706;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class MainConfiguration 
{
	public final static String CONFIG_FILE_LOCATION = "resources/config.txt";
	public static int serverTcpPort;
	
	public void configuration()
	{
		File configFile = new File(CONFIG_FILE_LOCATION);
		Properties properties = new Properties();
		
		try
		{
			properties.load(new FileInputStream(configFile));
			serverTcpPort = Integer.parseInt(properties.getProperty("ServerTcpPort"));	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
