package DNS;
import java.nio.ByteBuffer;
public class Header{
    private int id;
    private int flags;
    private int[] counts;

    public Header(int id){
        this.id = id;
        resetFlags();
        resetCounts();
    }

    public Header(Header head){
        id = head.id;
        flags = head.flags;
        counts = head.counts.clone();
    }
    public Header(DNSParser in){
        id = in.read16b();
        flags = in.read16b();
        counts = new int[4];
        for(int i = 0; i < 4; i++){
            counts[i] = in.read16b();
        }
    }
    
    public int getID(){
        return id;
    }

    public int getFlags(){
        return flags;
    }

    public int[] getCounts(){
        return counts;
    }

    public int getCount(int field){
        return counts[field];
    }

    public void incCount(int field){
        counts[field]++;
    }

    public void resetFlags(){
        flags = 0x0000;
    }

    public void resetCounts(){
        for(int i = 0; i < 4; i++){
            counts[i] = 0;
        }
    }

    public byte[] toByteArray(){
        ByteBuffer buf = ByteBuffer.allocate(12);
        buf.putShort((short)id);
        buf.putShort((short)flags);
        for(int i = 0; i < 4; i++){
            buf.putShort((short)counts[i]);
        }
        byte[] array = new byte[buf.position()];
        buf.position(0);
        buf.get(array);
        return array;
    }

    public String toString(){
        String str = String.format("[id: 0x%04X, " + 
        "flags: 0x%04X, " +
        "QDCOUNT: 0x%04X, " + 
        "ANCOUNT: 0x%04X, " + 
        "NSCOUNT: 0x%04X, " + 
        "ARCOUNT: 0x%04X]", id, flags, counts[0], counts[1], counts[2], counts[3]);
        return str;
    }
}