package client;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.JFrame;

public class Main{
    public static void main(String[] args){
        Browser client = new Browser();
        
        client.setTitle("Client Application");
        client.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        client.setSize( 900, 500 );
        client.setVisible( true );

        String content = "<head><title>HisCinema</title></head>"
				+"<body><h1>Videos</h1>"
				+"<a href=\"http://video.hiscinema.com/F1\">Video 1</a><br>"
				+"<a href=\"http://video.hiscinema.com/F2\">Video 2</a><br>"
				+"<a href=\"http://video.hiscinema.com/F3\">Video 3</a><br>"
				+"<a href=\"http://video.hiscinema.com/F4\">Video 4</a><br>"
				+"</body>";
        client.load(content);
    }
    
    public void playVideo() throws IOException{
    	
    	
    	Desktop.getDesktop().open(new File ("F1.mp4"));
    	
    }
    
    public void getVid(URL url){
    	String vidPath = url.getFile();
    }
    
   
    
}