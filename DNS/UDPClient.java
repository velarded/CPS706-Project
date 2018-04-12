package DNS;
import java.io.IOException;
import java.net.*;

public class UDPClient{
    private DatagramSocket socket;
    public UDPClient() throws SocketException{
        socket = new DatagramSocket();
    }

    public void sendPacket(InetAddress ip, int port, byte[] data) throws IOException{
        DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
        socket.send(packet);
    }

    public byte[] receivePacket() throws IOException{
        byte[] receiveBuf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(receiveBuf, receiveBuf.length);
        socket.receive(packet);
        return packet.getData();
    }

    public void close(){
        socket.close();
    }


}