import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//KMP
//매칭 성공했을 시, 뛰어넘는다
public class Main {
    //make table of number of duplicated letters
    static int[] makeTable(String pattern) {
        int length = pattern.length();
        int[] table = new int[length];

        int idx = 0;
        for (int i = 1; i < length; i++) {
            while (idx > 0 && pattern.charAt(idx) != pattern.charAt(i)) {
                idx = table[idx - 1];
            }

            if (pattern.charAt(idx) == pattern.charAt(i)) {
                table[i] = ++idx;
            }
        }

        return table;
    }

    //KMP
    static int KMP(int[] table, String string, String pattern) {
        int n = string.length();
        int m = pattern.length();
        int count = 0;

        int idx = 0;
        for (int i = 0; i < n; i++) {
            while (idx > 0 && pattern.charAt(idx) != string.charAt(i)) {
                idx = table[idx - 1];
            }

            if (pattern.charAt(idx) == string.charAt(i)) {
                if (idx == m - 1) {
                    count++;
                    idx = 0;
                } else {
                    idx++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //input strings
        String string = br.readLine();
        String pattern = br.readLine();

        //make table
        int[] table = makeTable(pattern);

        int count = KMP(table, string, pattern);
        System.out.println(count);
    }
}