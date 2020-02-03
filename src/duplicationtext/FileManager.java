package duplicationtext;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileManager {

    public String readFileToText() throws Exception {
        InputStream is = new FileInputStream(Constant.inputFile);
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
