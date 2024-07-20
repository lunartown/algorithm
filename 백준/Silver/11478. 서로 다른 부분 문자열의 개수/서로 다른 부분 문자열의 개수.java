import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//부분 문자열 세기
//중복을 뺀다
//suffix array & lcp

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int length = str.length();

        String[] suffix = new String[length];
        for(int i = 0; i < length; i++) {
            suffix[i] = str.substring(i);
        }

        Arrays.sort(suffix);
        int dupl = 0;

        for(int i = 1; i < length; i++) {
            for(int j = 0; j < Math.min(suffix[i].length(), suffix[i - 1].length()); j++) {
                if(suffix[i].charAt(j) == suffix[i - 1].charAt(j)) {
                    dupl++;
                } else break;
            }
        }

        System.out.println((1 + length) * length / 2 - dupl);
    }
}