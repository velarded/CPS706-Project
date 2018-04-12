package DNS;
import java.util.*;
import java.nio.ByteBuffer;

public class Name{
    public static final int NORMAL = 0;
    public static final int COMPRESSED = 3;
    private List<byte[]> name;
    private int size;
    
    public Name(String str){
        name = new ArrayList<byte[]>();
        String[] labels = str.split("\\.");
        for (int i = 0; i < labels.length; i++){
            name.add(labels[i].getBytes());
        }
    }

    public Name(List labels){
        name = labels;
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

    public int labels(){
        return name.size();
    }

    public List getLabels(){
        return name;
    }

    public Name offset(int offset){
        List<byte[]> temp = new ArrayList<byte[]>(name);
        temp.subList(0, offset-1).clear();
        return new Name(temp);
    }

    public boolean equals(Name n){
        List name2 = n.getLabels();
        if(name2.size() == name.size()){
            for(int i = 0; i < name2.size(); i++){
                if(!Arrays.equals((byte[])name.get(i), (byte[])name2.get(i))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public byte[] toByteArray(){
        ByteBuffer buf = ByteBuffer.allocate(256);
        for(int i = 0; i < name.size(); i++){
            byte[] label = name.get(i);
            buf.put((byte) label.length);
            buf.put(label);
        }
        buf.put((byte)0);

        byte[] array = new byte[buf.position()];
        buf.position(0);
        buf.get(array);
        return array;
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