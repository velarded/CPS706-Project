import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
public class HerCDNHTTPServer {

	public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(MainConfiguration.herCinemaServerPort()), 0);
        server.createContext("/F1", new MyHandler());
        server.createContext("/F2", new MyHandler());
        server.createContext("/F3", new MyHandler());
        server.createContext("/F4", new MyHandler());
        server.setExecutor(null); 
        server.start();
        System.out.println("Listening for client connections on port "+MainConfiguration.herCinemaServerPort()+"...");
    }
	
	static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException 
        {
        	System.out.println("Received client connection!");
        	String uri = t.getRequestURI().toString().replace("/", "").trim();
            String response400 = "Not valid file name.";
            OutputStream os = t.getResponseBody();

            if(uri.equals("F1") || uri.equals("F2") || uri.equals("F3") || uri.equals("F4"))
            {
            	File file = new File(uri+".mp4");
            	String fileLength = ""+(int)file.length();
               	t.sendResponseHeaders(200, file.length());
            	FileInputStream fis = new FileInputStream(file);
            	BufferedInputStream bis = new BufferedInputStream(fis);
				DataInputStream dis = new DataInputStream(bis);
				
            	byte[] buffer = new byte[(int)file.length()];
            	int read;
            	while((read = dis.read(buffer)) != -1)
            		os.write(buffer, 0, read);
            	
            	fis.close();
				bis.close();
				dis.close();
				System.out.println("Sent file "+uri);
            }
            else
            {
            	t.sendResponseHeaders(400, response400.length());
            	os.write(response400.getBytes());
            }
            os.close();
        }
    }

}