import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

class Edge {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}

public class Main {
    static final int INF = 987654321;

    public static boolean bellmanFord(Edge[] edges, int N, int M, int W) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 0);  // 모든 정점의 거리를 0으로 초기화

        // N번 반복 (음수 사이클 확인을 위해 한 번 더 반복)
        for (int i = 0; i < N; i++) {
            boolean updated = false;
            for (Edge edge : edges) {
                if (dist[edge.to] > dist[edge.from] + edge.cost) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                    updated = true;
                    // N번째 반복에서도 업데이트가 발생하면 음수 사이클 존재
                    if (i == N - 1) return true;
                }
            }
            // 더 이상 업데이트가 없으면 조기 종료
            if (!updated) break;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            Edge[] edges = new Edge[2*M + W];

            int idx = 0;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges[idx++] = new Edge(from, to, cost);
                edges[idx++] = new Edge(to, from, cost);
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges[idx++] = new Edge(from, to, -cost);
            }

            boolean hasNegativeCycle = bellmanFord(edges, N, M, W);
            sb.append(hasNegativeCycle ? "YES\n" : "NO\n");
        }
        System.out.print(sb);
    }
}