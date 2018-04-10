package DNS;
import java.io.*;
import java.net.*;
import java.util.List;

import DNS.DNSMessage;
import DNS.Record;

import java.util.ArrayList;

public class DNSServer{
    private List<Record> recordTable;
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

            DNSMessage response = lookup(message);
            
            System.out.println(response.toString());
        }
    }

    public DNSMessage lookup(DNSMessage message){

        DNSMessage resp = new DNSMessage(message);
        Record question = message.getQuestion();
        Name qname = question.getName();

        System.out.println("TYPE: " + question.getType().toString());

        //loop through the name label by label
        //(Ex. na.abc.example.com -> abc.example.com -> example.com -> etc...)
        for(int i = 0; i < qname.labels()-1; i++){
            //if i is 0 that means we have not remove a label yet
            boolean isExact = (i == 0);

            for(int j = 0; j < recordTable.size(); j++){
                Record record = recordTable.get(j);
                System.out.println("Comparing... " + qname.toString() + " " + record.getName().toString());
                if(qname.equals(record.getName())){
                    if(isExact){
                        Type rtype = record.getType();
                        if(rtype == question.getType()){
                            //if the name and type match with the record we found an answer
                            resp.addRecord(record, 1);
                        }
                        else if(rtype == Type.CN || rtype == Type.R){
                            //if it is type CN or R then restart the search with new name
                            resp.addRecord(record, 1);
                            qname = record.getName();
                            i = 0;
                            break;
                        }
                    }
                    if(record.getType() == Type.NS && question.getType() != Type.NS){
                        //store any NS records if found
                        resp.addRecord(record, 2);
                    }
                }
            }
            //remove the first label from name
            qname = qname.offset(1);
        }

        //if there are auth records, search for their ip addresses
        if(resp.getCount(2) > 0){
            //get all authoritative nameserver records
            List<Record> NSRecords = resp.getAuthorities();
            
            //search through the record table
            for(int i = 0; i < NSRecords.size(); i++){
                //convert String to name object
                Name tname = new Name(NSRecords.get(i).getValue());
                System.out.println("Looking for " + tname.toString());

                for(int j = 0; j < recordTable.size(); j++){
                    //search for the type A record for nameserver
                    Record record = recordTable.get(j);

                    System.out.println("Comparing... " + tname.toString() + " " + record.getName().toString());

                    if(tname.equals(record.getName()) && record.getType() == Type.A){
                        resp.addRecord(record, 3);
                        break;
                    }
                }
            }
        }
        
        return resp;
    }

    public void addRecord(Record record){
        recordTable.add(record);
    }

}