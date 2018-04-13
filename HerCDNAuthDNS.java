import DNS.*;
import java.net.*;
public class HerCDNAuthDNS{
    public static void main(String[] args) throws Exception {
        InetAddress ip = InetAddress.getByName("127.0.0.3");
        DNSServer server = new DNSServer(ip, 53, true);
        server.addRecord(new Record(new Name("herCDN.com"), "www.herCDN.com", Type.CN));
        server.addRecord(new Record(new Name("www.herCDN.com"), "127.0.0.6", Type.V));

        server.start();
    }
}