import java.nio.ByteBuffer;

class DNSParser{
    private ByteBuffer bbuf;
    public DNSParser(byte[] data){
        bbuf = ByteBuffer.wrap(data);
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