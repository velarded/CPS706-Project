package DNS;
import java.nio.ByteBuffer;

public class DNSParser{
    private ByteBuffer bbuf;
    private int saveIdx;
    public DNSParser(byte[] data){
        bbuf = ByteBuffer.wrap(data);
        saveIdx = -1;
    }
    
    public void save(){
        saveIdx = bbuf.position();
    }

    public void jumpTo(int index){
        bbuf.position(index);
    }

    public void restore(){
        if(saveIdx != -1)
            bbuf.position(saveIdx);
    }

    public int current(){
        return bbuf.position();
    }

    public int read8b(){
        return (bbuf.get() & 0xff);
    }

    public int read16b()
    {
        return (bbuf.getShort() & 0xffff);
    }

    public void getByteArray(byte[] dst, int offset, int len){
        bbuf.get(dst, offset, len);
    }


}