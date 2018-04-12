package client;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JEditorPane;
import javax.swing.JTextField;

import com.cps706.MainConfiguration;
import com.sun.prism.impl.Disposer.Record;


public class Browser extends JFrame{
    private JTextField urlField;
    private JEditorPane editorPane;
    private JPanel panel;
    private List<Record> browserRecordTable;

    public Browser(){

    	panel = new JPanel();
    	browserRecordTable = new ArrayList<Record>();
        urlField = new JTextField(35);
        urlField.addKeyListener(new KeyAdapter(){
        	public void keyReleased(KeyEvent k){
        		if(k.getKeyCode() == KeyEvent.VK_ENTER){
        			try{
        				gotoLocalDNS();
        			}catch(Exception e){
        				e.printStackTrace();
        			}
        		}
        	}
        });
        editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditable(false);
        panel.add(editorPane);

        getContentPane().setLayout(new BorderLayout());
        add(urlField, BorderLayout.NORTH);
        add(editorPane, BorderLayout.CENTER);
        
    }

    /*load html to editor pane*/
    public void load(String html){
        editorPane.setText(html);
        
    }
    
    /*add records to the record table*/
    public void addRecord(Record record){
    	browserRecordTable.add(record);
    }
    
    public void gotoLocalDNS() throws Exception{
    	//Create client UDP socket 
    	DatagramSocket clientSocket = new DatagramSocket();
    	//Assuming that url value will be www.hiscinema.com
    	String url = urlField.getText().replace("www.", "");
    			
    	//Uses local IP address
    	InetAddress ipAddr = InetAddress.getByName(MainConfiguration.localdnsIP());
    	byte[] sendData = new byte[1024];
    	byte[] receiveData = new byte[1024];
    			
    	sendData = url.getBytes();
    	DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,ipAddr,MainConfiguration.udpPort());
    			
    	System.out.println("Contacting local DNS using UDP to resolve: "+url);
    	clientSocket.send(sendPacket);
    			
    	if(url.equals("hiscinema.com")){
    		//Receives UDP packet
    		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    		clientSocket.receive(receivePacket);
    				
    		//contains IP of hiscinema.com
    		String modifiedSentence = new String(receivePacket.getData());
    		System.out.println("FROM SERVER:" + modifiedSentence);
    		clientSocket.close();
    				
    		//getting the IP address of hiscinema.com
    		Socket TCPclientSocket = new Socket("modifiedSentence",MainConfiguration.hisCinemaServerPort());
    		
    		File indexHTML = new File ("index.html");
    		BufferedReader br = new BufferedReader(new FileReader (indexHTML));
    		String line = null;
    		while((line = br.readLine()) != null)	{	
    			System.out.println(line);	
    		}
    	}
    	
    	
    }
    
    
    
    

    
}