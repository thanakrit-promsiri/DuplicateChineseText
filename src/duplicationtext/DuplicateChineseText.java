package duplicationtext;

public class DuplicateChineseText {

    public static void main(String[] args) {
        DuplicateChineseText engine = new DuplicateChineseText();
        engine.run();
    }

    public void run() {
        FileManager fileManager = new FileManager();
        DuplicateChineseTextProcess process = new  DuplicateChineseTextProcess();
        String txtfile;
        try {
            txtfile = fileManager.readFileToText();
            process.splitLine(txtfile);
            //System.out.println(txtfile);
        } catch (Exception ex) {

        }
    }

}
