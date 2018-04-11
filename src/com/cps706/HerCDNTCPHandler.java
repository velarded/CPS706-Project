package com.cps706;
import java.io.*;
import java.net.*;

public class HerCDNTCPHandler implements Runnable
{
	private Socket clientSocket;
	public HerCDNTCPHandler(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
	}
	
	@Override
	public void run() 
	{
		try
		{
			//Create InputStream from client socket to listen for request.
			DataInputStream inFromClient = new DataInputStream(clientSocket.getInputStream());
			//Create OutputStream from client socket to send out the selected video file
			DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
			String line = null;
			while((line = inFromClient.readLine()) != null)
			{
				if(line.contains("GET") && line.contains("HTTP/1.1"))
				{
					int fileRequestIndex = line.indexOf("F");
					String filename = line.substring(fileRequestIndex,fileRequestIndex+2);
					
					File videoReq = new File(filename+".mp4");
					byte[] buffer = new byte[(int)videoReq.length()];
					FileInputStream fis = new FileInputStream(videoReq);
					BufferedInputStream bis = new BufferedInputStream(fis);
					DataInputStream dis = new DataInputStream(bis);
					
					dos.writeLong(buffer.length);
					System.out.println("Length of file to be sent: "+buffer.length);
					int read;
					while((read = dis.read(buffer)) != -1)
					{
						dos.write(buffer,0,read);
					}
					dos.writeBytes(null);
					System.out.println("Sent bytes of "+filename+" video!");
					fis.close();
					bis.close();
					dis.close();
				}
				
			}
			
			//Close output stream
			dos.close();
			//Close the client socket
			clientSocket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
