import java.io.*;
import java.util.*;
import java.time.*;
import java.util.stream.*;

public class Main {
    static int operate(int num, int op) {
        switch(op) {
            case 0 : return num - 1;
            case 1 : return num + 1;
            case 2 : return num * 2;
            default : return -1;
        }
    }

    static String solution(int start, int end) {
        StringBuilder sb = new StringBuilder();

        if(start >= end) {
            sb.append(start - end).append("\n");
            for(int i = start; i >= end; i--) {
                sb.append(i).append(" ");
            }
            return sb.toString();
        }

        //bfs
        int[] dis = new int[end + 2];
        int[] parent = IntStream.range(0, end + 2).toArray();

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        dis[start] = 0;

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int i = 0; i < 3; i++) {
                int next = operate(cur, i);
                if(next <= 0 || next > end + 1) continue;
                if(dis[next] == 0 || dis[next] > dis[cur] + 1) {
                    dis[next] = dis[cur] + 1;
                    parent[next] = cur;
                    queue.offer(next);
                }
            }
        }

        sb.append(dis[end]).append("\n");

        Stack<Integer> stack = new Stack<>();
        stack.push(end);

        while(end != start) {
            end = parent[end];
            stack.push(end);
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        String answer = solution(start, end);
        System.out.println(answer);
    }
}