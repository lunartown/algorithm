import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class Main {
    static boolean isOperator(String str) {
        if(str.length() > 1) return false;
        return Pattern.matches("[+\\-*/()]", str);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] equation = br.readLine().split("");
        ArrayDeque<String> stack = new ArrayDeque<>();

        int length = equation.length;
        int idx = 0;
        String term = equation[0];

        while(true) {
            if(stack.isEmpty()) {
                stack.push(term);
            }

            else if(!isOperator(term)) {
                String op = stack.pop();
                if(op.equals("*") || op.equals("/")) {
                    String num = stack.pop();
                    term = num + term + op;
                    continue;
                } else {
                    stack.push(op);
                    stack.push(term);
                }
            }

            else if(term.equals(")")) {
                term = "";
                String num = stack.pop();
                String op = stack.pop();
                while(!op.equals("(")) {
                    term = num + op + term;
                    num = stack.pop();
                    op = stack.pop();
                }
                term = num + term;
                continue;
            }

            else {
                stack.push(term);
            }

            if(idx == length - 1) break;
            term = equation[++idx];
        }

        term = stack.pollLast();
        while(!stack.isEmpty()) {
            String op = stack.pollLast();
            String num = stack.pollLast();
            term = term + num + op;
        }

        System.out.println(term);
    }
}