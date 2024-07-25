import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

//풀이 : 모래시계를 만들자
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> upper = new PriorityQueue<>();
        PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());

        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(br.readLine());
            int ans = num;

            if (i == 1) {
                ans = num;
                lower.offer(num);
            } else if (i % 2 == 0) {
                int low = lower.poll();
                if (low <= num) {
                    ans = low;
                    lower.offer(low);
                    upper.offer(num);
                } else {
                    lower.offer(num);
                    ans = lower.peek();
                    upper.offer(low);
                }
            } else if (i % 2 == 1) {
                int low = lower.poll();
                if (low <= num) {
                    lower.offer(low);
                } else {
                    lower.offer(num);
                    num = low;
                }

                int high = upper.poll();
                if (num <= high) {
                    upper.offer(high);
                    ans = num;
                    lower.offer(num);
                } else {
                    upper.offer(num);
                    ans = high;
                    lower.offer(high);
                }
            }
            sb.append(ans).append('\n');
        }

        System.out.println(sb);
    }
}