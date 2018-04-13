import DNS.*;
import java.net.*;

public class LocalDNS{

    public static void main(String[] args) throws Exception {
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        DNSServer server = new DNSServer(ip, 53, false);
        server.addRecord(new Record(new Name("herCDN.com"), "NSherCDN.com", Type.NS));
        server.addRecord(new Record(new Name("NSherCDN.com"), "127.0.0.3", Type.A));
        server.addRecord(new Record(new Name("hiscinema.com"), "NShiscinema.com", Type.NS));
        server.addRecord(new Record(new Name("NShiscinema.com"), "127.0.0.2", Type.A));

        server.start();
    }
}