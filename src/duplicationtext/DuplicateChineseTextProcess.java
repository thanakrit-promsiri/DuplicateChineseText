package duplicationtext;

public class DuplicateChineseTextProcess {

    public String[] splitLine(String text) {
        int count = 1;
        String[] lines = text.split("\\r?\\n");
        for (String line : lines) {
            System.out.println("line " + count++ + " : " + line);
        }
        
        return lines;
    }
}
