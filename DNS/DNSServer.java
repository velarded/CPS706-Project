package DNS;
import java.io.*;
import java.net.*;
import java.util.List;

import DNS.Record;
import sun.tools.jar.resources.jar_de;

import java.util.ArrayList;

public class DNSServer{
    private List recordTable;
    private int port;
    public DNSServer(int port){
        recordTable = new ArrayList<Record>();
        this.port = port;
    }

    public void start()  throws Exception{
        DatagramSocket serverSocket = new DatagramSocket(port);
        byte[] receiveData = new byte[1024];

        while(true){
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            
            String sentence = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());

            InetAddress ip = receivePacket.getAddress();

            int recPort = receivePacket.getPort();

            System.out.println(ip.toString() + " " + recPort + " " + sentence);
            DNSMessage message = new DNSMessage(receivePacket.getData());
            System.out.println(message.toString());

            DNSMessage response = lookup(message.getQuestion());
        }
    }

    public DNSMessage lookup(Record question){
        DNSMessage resp = new DNSMessage();
        Name qname = question.getName();
        for(int i = 0; i < qname.labels()-1; i++){
            boolean isExact = (i == 0);
            
            for(int j = 0; j < recordTable.size(); j++){
                Record record = recordTable.get(i);
                if(qname.equals(record.getName())){
                    if(isExact && record.getType() == question.getType()){
                        
                    }
                }
            }
        }
        
    }

    public void addRecord(Record record){
        recordTable.add(record);
    }

}