import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Stream;

class Edge {
    int x;
    int y;
    int cost;

    public Edge(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }
}

public class Main {
    static final int INF = 1_000_000_007;
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};

    static int N;
    static int[][] cave;
    static int[][] dist;

    static boolean isMovable(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Edge(0, 0, dist[0][0]));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!isMovable(nx, ny))
                    continue;
                int nextCost = cur.cost + cave[nx][ny];
                if (nextCost < dist[nx][ny]) {
                    dist[nx][ny] = nextCost;
                    pq.offer(new Edge(nx, ny, nextCost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = 0;
        while (true) {
            T++;
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            //동굴 입력
            cave = new int[N][];
            for (int i = 0; i < N; i++) {
                cave[i] = Stream.of(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            //최소 비용 계산
            dist = new int[N][N];
            for (int[] row : dist) {
                Arrays.fill(row, INF);
            }
            dist[0][0] = cave[0][0];
            dijkstra();

            sb.append("Problem ").append(T).append(": ").append(dist[N - 1][N - 1]).append("\n");
        }
        System.out.println(sb);
    }
}