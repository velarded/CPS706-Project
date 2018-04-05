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
	
	public int getType()
	{
		return type;
	}
	
	public void setType(int type)
	{
		this.type = type;
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