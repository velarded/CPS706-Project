package DNS;
import java.util.*;

public class Name{
    public static final int NORMAL = 0;
    public static final int COMPRESSED = 3;
    private List name;
    private int size;
    
    public Name(String str){
        name = new ArrayList<byte[]>();
        String[] labels = str.split("\\.");
        for (int i = 0; i < labels.length; i++){
            name.add(labels[i].getBytes());
        }
    }

    public Name(DNSParser in){
        name = new ArrayList<byte[]>();
        size = 0;

        //TODO: Manage the size of labels (MAX 63 bytes) and name size (MAX 255 bytes)
        while(true){
            int len = in.read8b();
            if( len == 0 ){
                break;
            }
            else if( (len >> 6) == NORMAL){
                byte[] labels = new byte[len];
                in.getByteArray(labels, 0, len);
                name.add(labels);
            }
            else if( (len >> 6) == COMPRESSED){
                int offset = (len & 0x3f) | in.read8b();
                in.save();
                in.jumpTo(offset);
            }
        }
        in.restore();
    }

    public String toString(){
        String str = "";
        for(int i = 0; i < name.size(); i++){
            str += new String((byte[])name.get(i));
            if(i != name.size()-1){
                str += ".";
            }
        }
        return str;
    }
}