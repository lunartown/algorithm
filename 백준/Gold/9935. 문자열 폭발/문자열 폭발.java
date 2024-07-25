import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Stack;

//풀이 : 모르겠다
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] str = br.readLine().toCharArray();
        String regex = br.readLine();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length; i++) {
            stack.push(str[i]);
            int size = stack.size();
            int length = regex.length();
            if (size >= length) {
                boolean flag = true;
                for (int j = 0; j < length; j++) {
                    if (stack.get(size - length + j) != regex.charAt(j)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    for (int j = 0; j < length; j++) {
                        stack.pop();
                    }
                }
            }
        }
        stack.stream().forEach(i -> sb.append(i));
        if (sb.toString().equals("")) {
            System.out.println("FRULA");
        } else
            System.out.println(sb);
    }
}