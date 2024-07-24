import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        CustomStack stack = new CustomStack();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push":
                    stack.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(stack.pop()).append('\n');
                    break;
                case "size":
                    sb.append(stack.size()).append('\n');
                    break;
                case "empty":
                    sb.append(stack.empty()).append('\n');
                    break;
                case "top":
                    sb.append(stack.top()).append('\n');
                    break;
                default:
                    throw new IOException("잘못된 입력입니다.");
            }
        }

        System.out.println(sb);
    }
}

class CustomStack {
    int[] arr = new int[10001];
    int size = 0;

    boolean push(int X) {
        try {
            arr[size] = X;
            size++;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    int pop() {
        if (size == 0) return -1;
        size--;
        return arr[size];
    }

    int size() {
        return size;
    }

    int empty() {
        if (size == 0) return 1;
        return 0;
    }

    int top() {
        if (size == 0) return -1;
        return arr[size - 1];
    }
}