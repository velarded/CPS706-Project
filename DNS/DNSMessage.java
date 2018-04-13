package DNS;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.nio.ByteBuffer;
public class DNSMessage{
    private DNSParser parser;
    private Header header;
    private ArrayList[] sections;

    
    public DNSMessage(DNSMessage message){
        header = new Header(message.getHeader());
        sections = new ArrayList[4];
        
        for(int i = 0; i < 4; i++){
            ArrayList<Record> entries = new ArrayList<Record>();
            ArrayList<Record> records = message.getSection(i);
            for(int j = 0; j < records.size(); j++){
                entries.add(new Record(records.get(j)));
            }
            sections[i] = entries;
        }
    }

    public DNSMessage(byte[] data){
        parser = new DNSParser(data);
        header = new Header(parser);
        sections = new ArrayList[4];

        int[] counts = header.getCounts();
        
        //extract all the resource records and place them in the corresponding sections
        for(int i = 0; i < 4; i++){
            ArrayList<Record> entries = new ArrayList<Record>();
            for(int j = 0; j < counts[i]; j++){
                entries.add(new Record(parser, i));
            }
            sections[i] = entries;
        }
    }

    public Header getHeader(){
        return header;
    }

    public ArrayList[] getSections(){
        return sections;
    }

    public ArrayList getSection(int field){
        return sections[field];
    }

    public int getCount(int field){
        return header.getCount(field);
    }

    public Record getQuestion(){
        //pratically there should be always 1 question in DNS messages,
        //but theoretically it can have more than 1 or none.
        if(!sections[0].isEmpty()){
            return (Record)sections[0].get(0);
        }
        return null;
    }

    public Record getAnswer(Type type){
        List<Record> ANRecords = sections[1];
        for (int i = 0; i < ANRecords.size(); i++){
            Record record = ANRecords.get(i);
            if(record.getType() == type){
                return record;
            }
        }
        return null;
    }

    public List getRecords(int field){
        return sections[field];
    }

    public boolean hasAnswer(Type type){
        List<Record> ANRecords = sections[1];
        for (int i = 0; i < ANRecords.size(); i++){
            Record record = ANRecords.get(i);
            if(record.getType() == type){
                return true;
            }
        }
        return false;
    }

    public void addRecord(Record record, int field){
        header.incCount(field);
        sections[field].add(record);
    }

    public void addRecords(List records, int field){
        for (int i = 0; i < records.size(); i++){
            addRecord((Record)records.get(i), field);
        }
    }

    public byte[] toByteArray(){
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(header.toByteArray());
        for ( int i = 0; i < 4; i++){
            List<Record> section = sections[i];
            
            for(int j = 0; j < section.size(); j++){
                buf.put(section.get(j).toByteArray());
            }
        }
        byte[] array = new byte[buf.position()];
        buf.position(0);
        buf.get(array);
        return array;
    }
    public String toString(){
        String str = "HEADER = ";
        str += header.toString() + "\n";
        int[] counts = header.getCounts();
        for ( int i = 0; i < 4; i++){
            if(i == 0)
                str += "QUESTIONS\n";
            else if(i == 1)
                str += "ANSWERS\n";
            else if(i == 2)
                str += "AUTHORITATIVE NAMESERVERS\n";
            else if(i == 3)
                str += "ADDITIONAL RECORDS\n";
            for ( int j = 0; j < counts[i]; j++)
                str += sections[i].get(j).toString() + "\n";
        }
        return str;
    }

}