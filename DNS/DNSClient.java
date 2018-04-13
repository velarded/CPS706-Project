package DNS;
import java.net.*;

public class DNSClient
{
    private UDPClient udp;
    private int id;
    private InetAddress ip;
    private int port;

    public DNSClient(InetAddress ip, int port) throws Exception{
        udp = new UDPClient();
        id = 0;
        this.ip = ip;
        this.port = port;
    }

    public InetAddress resolve(String hostname, Type type) throws Exception{
        DNSMessage request = new DNSMessage(id);
        request.addRecord(new Record(new Name(hostname), "", type), 0);
        udp.sendPacket(ip, port, request.toByteArray());
        DNSMessage response = new DNSMessage(udp.receivePacket());
        Record answer = response.getAnswer(type);
        id++;
        if(answer != null){
            return InetAddress.getByName(answer.getValue());
        }
        return null;
    }
}