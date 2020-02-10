package duplicationtext;

public class DuplicateChineseText {

    public static void main(String[] args) throws Exception {
        DuplicateChineseText engine = new DuplicateChineseText();
        engine.run();
    }

    public void run() throws Exception {
        FileManager fileManager = new FileManager();
        DuplicateChineseTextProcess process = new DuplicateChineseTextProcess();
        String txtfileOrigin;

        txtfileOrigin = fileManager.readFileToText();
        String txtfile = process.prepareText(txtfileOrigin);

        String mapDupline = process.mapReduce(process.splitSentence(txtfile));

        int findDupInline = process.findDupInline(mapDupline);

       String preparDuplicateMap = process.preparSummaryDuplicateMap(txtfile);

       
        // System.out.println(findDupInline);

    }

}
