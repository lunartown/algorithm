import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Stream;

//문제 : 길이가 짧은 것부터, 같으면 사전 순
//class로 감싼다
public class Main {
    static class Word implements Comparable<Word> {
        String str;

        public Word(String str) {
            this.str = str;
        }

        @Override
        public int compareTo(Word o) {
            if(this.str.length() == o.str.length()) {
                return this.str.compareTo(o.str);
            }

            return this.str.length() - o.str.length();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        TreeSet<Word> words = new TreeSet<>();
        for(int i = 0; i < N; i++) {
            words.add(new Word(br.readLine()));
        }

        while(!words.isEmpty()) {
            sb.append(words.pollFirst().str).append('\n');
        }
        System.out.println(sb);
    }
}