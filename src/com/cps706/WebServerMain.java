package com.cps706;

public class WebServerMain {

	public static void main(String[] args) 
	{
		System.out.println("Starting WebServer for hiscinema...");
		WebServer webServer = new WebServer(MainConfiguration.serverTcpPort());
		webServer.start();
	}

}
