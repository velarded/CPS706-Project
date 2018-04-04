
class Record {
    public enum Type { A, NS, CNAME, MX};
    private Name name;
    private String value;
    private Type type;

    public Record(DNSParser in){
        name = new Name(in);
    }
}