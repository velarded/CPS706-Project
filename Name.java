import java.util.*;

class Name{
    public static final int NORMAL = 0;
    public static final int COMPRESSED = 3;
    private List name;
    private int size;
    public Name(DNSParser in){
        name = new ArrayList<byte[]>();
        size = 0;

        // boolean finish = false;
        // while(!finish){
        //     int len = in.read8b();
        //     if( (len >> 6) == NORMAL){
        //         byte[] labels = new byte[len+1];
        //         labels[0] = (byte)len;
        //         in.getByteArray(labels, 1, len);
        //     }
        // }
    }
}