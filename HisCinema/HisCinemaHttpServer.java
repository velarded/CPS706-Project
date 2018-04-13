import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
public class HisCinemaHttpServer {

  public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(MainConfiguration.hisCinemaServerPort()), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Listening for client connections on port "+MainConfiguration.hisCinemaServerPort()+"...");
    }
  
  static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException 
        {
          System.out.println("Received client connection!");
            OutputStream os = t.getResponseBody();
            File file = new File("index.html");
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
      System.out.println("Sent index.html file to client!");

            os.close();
        }
    }

}