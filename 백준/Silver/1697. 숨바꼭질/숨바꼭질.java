import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = Integer.MAX_VALUE;
    static final int RANGE = 100_000;

    static boolean isMovable(int i) {
        if (i < 0 || i > RANGE) return false;
        return true;
    }

    static int nextMove(int i, int dir) {
        if (dir == 0) return i - 1;
        if (dir == 1) return i + 1;
        if (dir == 2) return i * 2;
        else return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //거리(시간) 배열
        int[] dis = new int[RANGE + 1];
        //bfs
        Queue<Integer> queue = new ArrayDeque<>();
        //초기값 무한대
        Arrays.fill(dis, MAX);
        dis[N] = 0;
        queue.offer(N);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == K)
                break;

            for (int i = 0; i < 3; i++) {
                int next = nextMove(cur, i);
                if (!isMovable(next))
                    continue;
                //갱신되는 상황에만
                if (dis[cur] + 1 < dis[next]) {
                    dis[next] = dis[cur] + 1;
                    queue.offer(next);
                }
            }
        }

        System.out.println(dis[K]);
    }
}