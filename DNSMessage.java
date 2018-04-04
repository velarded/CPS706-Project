import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
class DNSMessage{
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
                entries.add(new Record(parser));
            }
            sections[i] = entries;
        }
    }

    public Header getHeader(){
        return header;
    }

}