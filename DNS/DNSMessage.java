package DNS;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
public class DNSMessage{
    private DNSParser parser;
    private Header header;
    private List[] sections;
    
    public DNSMessage(byte[] data){
        parser = new DNSParser(data);
        header = new Header(parser);
        sections = new ArrayList[4];

        int[] counts = header.getCounts();
        
        //extract all the resource records and place them in the corresponding sections
        for(int i = 0; i < 4; i++){
            List entries = new ArrayList<Record>();
            for(int j = 0; j < counts[i]; j++){
                entries.add(new Record(parser, i));
            }
            sections[i] = entries;
        }
    }

    public Header getHeader(){
        return header;
    }

    public String toString(){
        String str = "HEADER = ";
        str += header.toString() + "\n";
        int[] counts = header.getCounts();
        for ( int i = 0; i < 4; i++){
            for ( int j = 0; j < counts[i]; j++)
                str += sections[i].get(j).toString() + "\n";
        }
        return str;
    }

}