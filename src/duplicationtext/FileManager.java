package duplicationtext;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileManager {

    public String readFileToText() throws Exception {

        return this.readFileToText(Constant.inputFile);
    }

    public String readFileToText(String inputFile) throws Exception {
        InputStream is = new FileInputStream(inputFile);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();
        while (line != null) {
            if (checkLineAddCond(line)) {
                sb.append(line);
                sb.append("\n");
            }

            line = buf.readLine();
        }
        return sb.toString();
    }

    protected boolean checkLineAddCond(String line) {
        return !line.trim().isEmpty();
    }

}
