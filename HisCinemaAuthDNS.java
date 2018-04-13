import DNS.*;
import java.net.*;
public class HisCinemaAuthDNS{
    public static void main(String[] args) throws Exception {
        InetAddress ip = InetAddress.getByName("127.0.0.2");
        DNSServer server = new DNSServer(ip, 53, true);
        server.addRecord(new Record(new Name("video.hiscinema.com"), "herCDN.com", Type.R));
        server.addRecord(new Record(new Name("hiscinema.com"), "www.hiscinema.com", Type.CN));
        server.addRecord(new Record(new Name("www.hiscinema.com"), "127.0.0.5", Type.A));

        server.start();
    }
}