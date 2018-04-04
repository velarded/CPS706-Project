import java.io.*;
import java.net.*;

class LocalDDNS{
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(53);
        byte[] receiveData = new byte[1024];

        while(true){
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            
            String sentence = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());

            InetAddress ip = receivePacket.getAddress();

            int port = receivePacket.getPort();

            System.out.println(ip.toString() + " " + port + " " + sentence);
            DNSMessage message = new DNSMessage(receivePacket.getData());
            System.out.println(message.getHeader().toString());
        }
    }
}