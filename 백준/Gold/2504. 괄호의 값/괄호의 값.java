import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

//괄호의 값
//https://www.acmicpc.net/problem/2504
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int result = 0;
        int temp = 1;
        boolean isPossible = true;
        ArrayDeque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '(') {
                stack.push(c);
                temp *= 2;
            } else if (c == '[') {
                stack.push(c);
                temp *= 3;
            } else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    isPossible = false;
                    break;
                }

                if (input.charAt(i - 1) == '(') {
                    result += temp;
                }

                stack.pop();
                temp /= 2;
            } else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    isPossible = false;
                    break;
                }

                if (input.charAt(i - 1) == '[') {
                    result += temp;
                }

                stack.pop();
                temp /= 3;
            }
        }

        if (!isPossible || !stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }
    }
}