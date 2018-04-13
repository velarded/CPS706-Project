package DNS;
import java.nio.ByteBuffer;
public class Record {
    private Name name;
    private String value;
    private Type type;

    public Record(Record copy){
        name = copy.name;
        value = copy.value;
        type = copy.type;
    }

    public Record(Name name, String value, Type type){
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public Record(DNSParser in, int section){
        name = new Name(in);
        type = Type.fromInt(in.read16b());
        if(section != 0)
        {
            int len = in.read16b();
            byte[] tmp = new byte[len];
            in.getByteArray(tmp, 0, len);
            value = new String(tmp);
            System.out.println(value);
        }
        else{
            value = "";
        }
        
    }

    public void setName(Name name)
	{
		this.name = name;
	}
	
	public Name getName()
	{
		return name;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public Type getType()
	{
		return type;
	}
	
	public void setType(Type type)
	{
		this.type = type;
    }
    
    public byte[] toByteArray(){
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(name.toByteArray());
        buf.putShort((short) type.toInt());
        if(!value.isEmpty()){
            byte[] rdata = value.getBytes();
            buf.putShort((short)rdata.length);
            buf.put(rdata);
        }
        byte[] array = new byte[buf.position()];
        buf.position(0);
        buf.get(array);
        return array;
    }

    public String toString(){
        String str = "(" + name.toString();
        if(value != null){
            str += ", " + value;
        }
        str += ", " + type.toString() + ")";
        return str;
    }
}