import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

//알파벳 개수 세기
//String -> stream으로 바꿔 counting

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine().toUpperCase();
        int[] letters = new int[26];
        for(int i = 0; i < word.length(); i++) {
            letters[word.charAt(i) - 'A']++;
        }

        int max = 0;
        int maxchar = 0;
        for(int i = 0; i < 26; i++) {
            if(letters[i] > max) {
                max = letters[i];
                maxchar = i;
            } else if(letters[i] == max) {
                maxchar = '?' - 'A';
            }
        }

        System.out.println((char) (maxchar + 'A'));
    }
}