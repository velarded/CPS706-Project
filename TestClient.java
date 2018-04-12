
import java.io.*;
import java.net.*;
public class TestClient 
{
	public static void main(String[] args)
	{
		Socket socket = new Socket("localhost",40470);
		OutputStream os = socket.getOutputStream();
	}
}
