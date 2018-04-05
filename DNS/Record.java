package DNS;
public class Record {
    public static enum Type { A, NS, CNAME, MX };
    private Name name;
    private String value;
    private int type;

    public Record(DNSParser in, int section){
        name = new Name(in);
        type = in.read16b();
        if(section != 0)
        {
            int len = in.read16b();
            byte[] tmp = new byte[len];
            in.getByteArray(tmp, 0, len);
            value = new String(tmp);
            System.out.println(value);
        }
        
    }

    public String toString(){
        String str = "(" + name.toString();
        if(value != null){
            str += ", " + value;
        }
        str += ", " + type + ")";
        return str;
    }
}