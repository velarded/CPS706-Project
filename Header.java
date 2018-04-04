class Header{
    private int id;
    private int flags;
    private int[] counts;

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

    public String toString(){
        String str = String.format("id: 0x%04X\nflags: 0x%04X\nQDCOUNT: 0x%04X", id, flags, counts[0]);
        return str;
    }
}