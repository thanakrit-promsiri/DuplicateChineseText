package duplicationtext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DuplicateChineseTextProcess {

    public int lengthLineMax;
    Map<String, Integer> duplicateMap;

    public DuplicateChineseTextProcess() {
        lengthLineMax = -1;
        duplicateMap = new HashMap<>();
    }

    public String[] splitLine(String text, int limitLengthLine) {
        int count = 1;
        String[] lines = text.split("\\r?\\n");

        List<String> lineList = new ArrayList();

        for (String line : lines) {
            line = line.trim();
            if (!line.trim().isEmpty() && (line.length() >= limitLengthLine)) {
                // System.out.println("line " + count++ + " : " + line.trim());
                lineList.add(line);
            }
        }

        String[] array = lineList.toArray(new String[lineList.size()]);
        return array;
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
        Map<String, Integer> reduce = new HashMap<>();

        for (String text : textArray) {
            text = text.trim();
            if (reduce.get(text) == null) {
                reduce.put(text, 1);
            } else {
                reduce.put(text, reduce.get(text) + 1);
            }
        }
        int i = 0;
        int lengthMax = 0;
        StringBuilder returnStr = new StringBuilder();

        for (Map.Entry<String, Integer> entry : reduce.entrySet()) {

            if (entry.getValue() > 1) {
                //System.out.println((i++) + " : " + entry.getKey() + "  : " + entry.getValue());
                duplicateMap.put(entry.getKey(), entry.getValue());

            } else {
                if (entry.getKey().length() > lengthMax) {
                    lengthMax = entry.getKey().length();
                }
                returnStr.append(entry.getKey()).append("\n");
            }

        }

        lengthLineMax = lengthMax;

        return returnStr.toString();
    }

    public String prepareText(String text) {
        return text.replaceAll("。", "@").
                replaceAll(" ", "@").
                replaceAll("%", "@").
                replaceAll("#", "@").
                replaceAll("　", "@").
                replaceAll("\\t", "@").
                replaceAll("[0-9]", "@").
                replaceAll("\\(", "@").
                replaceAll("\\)", "@").
                replaceAll("@@@", "@").
                replaceAll("@@", "@").
                replaceAll("@@", "@");

    }

    public int findCount(String str, String findStr) {
        int lastIndex = 0;
        int count = 0;
        if (!findStr.trim().isEmpty()) {
            while (lastIndex != -1) {

                lastIndex = str.indexOf(findStr, lastIndex);

                if (lastIndex != -1) {
                    count++;
                    lastIndex += findStr.length();
                }
            }
        }

        return count;
    }

    public int findDupInline(String str) {
        int count = 1;
        //  System.out.println("xxxx");
        for (int i = lengthLineMax; i > 1; i--) {
            //System.out.println("i :" + i);
            int wordLength = i;
            String[] lines = this.splitLine(str, wordLength);
            String[] WordforSeek = this.setWordforSeekInline(lines, wordLength);

            for (String word : WordforSeek) {
                word = word.trim();
                int fc = findCount(str, word);
//               

                if (fc >= 2) {
                    str = str.replaceAll(word, "\n");
                    // System.out.println((count++) + " :" + word + " :" + fc);
                    duplicateMap.put(word, fc);
                } else {

                }

            }
        }

        //System.out.println("str :" + str.trim());
        return 0;
    }

    public String[] setWordforSeekInline(String[] lines, int wordLength) {

        List<String> lineList = new ArrayList();

        String tmp;
        for (String line : lines) {
            int roundSeek = line.length() - (wordLength - 1);
            for (int j = 0; j < roundSeek; j++) {
                tmp = line.substring(j, wordLength + j);
                // System.out.println(tmp);
                lineList.add(tmp);
            }
        }

        String[] array = lineList.toArray(new String[lineList.size()]);

        return array;
    }

    public String preparSummaryDuplicateMap(String original) {

        TreeMap<String, Integer> sorted = new TreeMap<>();

        // Copy all data from hashMap into TreeMap 
        sorted.putAll(duplicateMap);
        // StringBuilder sb = new StringBuilder();
        // Display the TreeMap which is naturally sorted 
        for (int i = 1; i <= lengthLineMax; i++) {
            for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
                if (entry.getKey().trim().length() == i) {
//                    System.out.println(entry.getKey() + " :" + entry.getValue());
                    //sb.append(entry.getKey().trim()).append("\n");

                    String strReplace = entry.getKey().trim();

                    original = original.replaceAll(strReplace, "%".concat(strReplace.concat("#")));
                }

            }
        }

        original = prepareText(original);

        mapReduceFinal(splitSentence(original));

       // System.out.println(original);
        return original;
    }

    public String mapReduceFinal(String[] textArray) {

        TreeMap<String, Integer> reduce = new TreeMap<>();

        for (String text : textArray) {
            text = text.trim();
            if (reduce.get(text) == null) {
                reduce.put(text, 1);
            } else {
                reduce.put(text, reduce.get(text) + 1);
            }
        }
        int i = 0;
        int lengthMax = 0;
        StringBuilder returnStr = new StringBuilder();

        for (Map.Entry<String, Integer> entry : reduce.entrySet()) {

            if (entry.getValue() > 1) {
                System.out.println((i++) + " : " + entry.getKey() + "  : " + entry.getValue());
            } else {
                System.err.println((i++) + " : " + entry.getKey() + "  : " + entry.getValue());
            }

        }

        lengthLineMax = lengthMax;

        return returnStr.toString();
    }
}
