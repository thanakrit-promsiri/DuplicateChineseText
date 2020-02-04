package duplicationtext;

import java.util.HashMap;
import java.util.Map;

public class DuplicateChineseTextProcess {

    public String[] splitLine(String text) {
        int count = 1;
        String[] lines = text.split("\\r?\\n");
        for (String line : lines) {
            System.out.println("line " + count++ + " : " + line.trim());
        }
        return lines;
    }

    public String[] splitSentence(String text) {
        int count = 1;
        String[] lines = text.split("@");
        for (String line : lines) {
           // System.out.println("line " + count++ + " : " + line.trim());
        }
        return lines;
    }

    public String mapReduce(String[] textArray) {
        Map<String, String> reduce = new HashMap<String, String>();
       
        for (String text : textArray) {
            if (reduce.get(text) == null) {
                reduce.put(text.trim(), "|");
            }else{
                reduce.put(text, reduce.get(text)+"|");
            }
        }
        int i=0;
   	for (Map.Entry<String, String> entry : reduce.entrySet()) {
		System.out.println((i++) +" : " + entry.getKey() + " Value : " + entry.getValue());
	}
 

        return null;
    }

    public String prepareText(String text) {
        return text.replaceAll("。", "@").
                replaceAll(" ", "@").
                replaceAll("　", "@").
                replaceAll("\\t", "@").
                replaceAll("[0-9]", "@").
                replaceAll("@@", "@");

    }
}
