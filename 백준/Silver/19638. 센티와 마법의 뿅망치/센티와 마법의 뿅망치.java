import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int count = 0;
        boolean success = false;
        for(int i = 0; i < T; i++) {
            int giant = pq.poll();
            if(giant < H) {
                success = true;
                pq.offer(giant);
                break;
            }
            if(giant != 1) giant >>>= 1;
            pq.offer(giant);
            count++;
        }
        if(pq.peek() < H) success = true;

        if(success)
            sb.append("YES").append('\n').append(count);
        else
            sb.append("NO").append('\n').append(pq.poll());

        System.out.println(sb);
    }
}