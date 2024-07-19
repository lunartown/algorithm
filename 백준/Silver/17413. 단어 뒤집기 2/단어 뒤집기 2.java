import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

//' ', tag를 제외한 문자 뒤집기
//한 글자씩 전진하며 읽기
//공백이나 태그가 나오면 뒤집어버려!

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        StringBuilder tag = new StringBuilder();
        StringBuilder subStr = new StringBuilder();
        boolean isTag = false;

        for (char c : str.toCharArray()) {
            if (isTag) {
                if (c == '>') {
                    sb.append(tag).append('>');
                    tag.setLength(0);
                    isTag = false;
                } else {
                    tag.append(c);
                }
                continue;
            }

            if (c == ' ') {
                sb.append(subStr.reverse());
                sb.append(' ');
                subStr.setLength(0);
            } else if (c == '<') {
                for(int i = subStr.length() - 1; i >= 0; i--) {
                    sb.append(subStr.charAt(i));
                }
                sb.append('<');
                subStr.setLength(0);
                isTag = true;
            } else {
                subStr.append(c);
            }
        }

        sb.append(subStr.reverse());
        System.out.println(sb);
    }
}