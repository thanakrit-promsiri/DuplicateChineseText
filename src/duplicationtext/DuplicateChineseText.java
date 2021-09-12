package duplicationtext;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DuplicateChineseText {

    public static void main(String[] args) throws Exception {
        DuplicateChineseText engine = new DuplicateChineseText();
        engine.runDict();
    }

    public void runDict() throws Exception {
        FileManager fileManager = new FileManager();
        DuplicateChineseTextProcess process = new DuplicateChineseTextProcess();
        String txtfileOrigin;

        txtfileOrigin = fileManager.readFileToText("txt/new.txt");
        /*txtfileOrigin = txtfileOrigin
                .replaceAll("\\[", " ")
                .replaceAll("\\]", " ")
                .replaceAll("\n", " ");*/
        String[] arrayString = txtfileOrigin.split("\\n");
        //String str = "[[如 (ดัง เหมือน ดังนี้ เหตุว่า)]是 (ด้วยอย่างนี้ )][語 (กล่าว วาจา)]。[諸 (ปวง ทั้งหลาย ทั้งหมด)][比丘 ภิกษุ][隨 (เป็นส่วนแห่ง ร่วมเป็น)]親厚。以[僧 (สงฆ์)] [物 (สิ่งของ)][與(มีส่วนร่วมใน พร้อมด้วย ด้วยกันกับ)] [者 (บุคคล ผู้ซึ่ง ผู้ )]。[波逸提 ปาจัตติกะ]。";
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(txtfileOrigin);
        StringBuilder bstr = new StringBuilder();
        while (matcher.find()) {
            bstr.append(matcher.group(1)).append("\n");
        }
        String[] arrayString1 = bstr.toString().replaceAll("\\[", "\n").split("\\n");
        Map<String, Integer> reduce2 = process.mapReduce(arrayString1);

        for (Map.Entry<String, Integer> entry : reduce2.entrySet()) {

            System.out.println(entry.getKey() + "  \t " + entry.getValue() + "  \t " + entry.getKey().length());

        }

    }

    public void run() throws Exception {
        FileManager fileManager = new FileManager();
        DuplicateChineseTextProcess process = new DuplicateChineseTextProcess();
        String txtfileOrigin;

        txtfileOrigin = fileManager.readFileToText();
        /* String txtfile = txtfileOrigin.replaceAll("\\t", " ").replaceAll("\\r", " ").replaceAll("\\n", " ");

        txtfile = txtfile
                .replaceAll("一", " 一 ")
                .replaceAll("二", " 二 ")
                .replaceAll("三", " 三 ")
                .replaceAll("四", " 四 ")
                .replaceAll("五", " 五 ")
                .replaceAll("六", " 六 ")
                .replaceAll("七", " 七 ")
                .replaceAll("八", " 八 ")
                .replaceAll("九", " 九 ")
                .replaceAll("十", " 十 ")
                .replaceAll("百", " 百 ")
                .replaceAll("比丘", " 比丘");

        String[] arrayString = txtfile.split(" ");
        Map<String, Integer> reduce = process.mapReduce(arrayString);
        txtfile = process.mapReducetext(reduce);*/

        String strreplac = txtfileOrigin;
        String[] special = "！()，、。：；??？「」『』??".split("");
        for (int i = 0; i < special.length; i++) {
            //strreplac = strreplac.replaceAll(special[i]., " ");
            strreplac = strreplac.replace(special[i].charAt(0), ' ');
        }

        String[] lekha = "一二三四五六七八九十百".split("");
        for (int i = 0; i < lekha.length; i++) {
            strreplac = strreplac.replaceAll(lekha[i], " " + lekha[i] + " ");

        }

        String[] kriyavisesa = "若".split("");
        for (int i = 0; i < kriyavisesa.length; i++) {
            strreplac = strreplac.replaceAll(kriyavisesa[i], " " + kriyavisesa[i] + " ");

        }
//2-1
        strreplac = splitword(3, strreplac);
        strreplac = splitword(2, strreplac);
        strreplac = splitword(1, strreplac);

        String[] arrayString2 = strreplac.split(" ");

        Map<String, Integer> reduce2 = process.mapReduce(arrayString2);

        for (Map.Entry<String, Integer> entry : reduce2.entrySet()) {
            System.out.println(entry.getKey() + "  \t " + entry.getValue() + "  \t " + entry.getKey().length());
        }

        // System.out.println(strreplac);
    }

    public static String splitword(int wordlenth, String strreplac) {
        DuplicateChineseTextProcess process = new DuplicateChineseTextProcess();
        String[] arrayString1 = strreplac.split(" ");

        Map<String, Integer> reduce1 = process.mapReduce(arrayString1);
        //String txtfile1 = process.mapReducetext(reduce1);
        StringBuilder wordtwo = new StringBuilder();
        for (Map.Entry<String, Integer> entry : reduce1.entrySet()) {

            // System.out.println(entry.getKey() + "  \t " + entry.getValue() + "  \t " + entry.getKey().length());
            if (entry.getKey().length() == wordlenth) {
                //  System.out.println(entry.getKey() + "  \t " + entry.getValue() + "  \t " + entry.getKey().length());
                wordtwo.append(entry.getKey() + " ");
            }

        }

        String[] two = wordtwo.toString().split(" ");
        for (int i = 0; i < two.length; i++) {
            strreplac = strreplac.replaceAll(two[i], " " + two[i]);

        }
        return strreplac;
    }

    public static boolean areBracketsBalanced(String expr) {

        /*if (areBracketsBalanced(entry.getKey())) {
                System.out.println("Balanced ");
            } else {
                System.out.println("Not Balanced ");
            }
         */
        // Using ArrayDeque is faster than using Stack class
        Deque<Character> stack
                = new ArrayDeque<Character>();

        // Traversing the Expression
        for (int i = 0; i < expr.length(); i++) {
            char x = expr.charAt(i);

            if (x == '(' || x == '[' || x == '{') {
                // Push the element in the stack
                stack.push(x);
                continue;
            }

            // If current character is not opening
            // bracket, then it must be closing. So stack
            // cannot be empty at this point.
            if (stack.isEmpty()) {
                return false;
            }
            char check;
            switch (x) {
                case ')':
                    check = stack.pop();
                    if (check == '{' || check == '[') {
                        return false;
                    }
                    break;

                case '}':
                    check = stack.pop();
                    if (check == '(' || check == '[') {
                        return false;
                    }
                    break;

                case ']':
                    check = stack.pop();
                    if (check == '(' || check == '{') {
                        return false;
                    }
                    break;
            }
        }

        // Check Empty Stack
        return (stack.isEmpty());
    }
}
