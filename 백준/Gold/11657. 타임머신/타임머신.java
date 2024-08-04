import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// Edge class
class Edge {
    int start;
    int end;
    int cost;

    public Edge(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}

public class Main {
    static final int INF = 1_000_000_007;

    //Bellman-Ford algorithm
    static boolean BellmanFord(long[] dist, Edge[] edges) {
        int N = dist.length - 1;
        int M = edges.length;

        for (int i = 1; i <= N; i++) {
            boolean updated = false;
            for (Edge edge : edges) {
                int start = edge.start;
                int end = edge.end;
                int cost = edge.cost;

                if (dist[start] != INF && dist[end] > dist[start] + cost) {
                    dist[end] = dist[start] + cost;
                    updated = true;
                    if (i == N) {
                        return false;
                    }
                }
            }
            if (!updated) break;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        //number of cities N, number of bus routes M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //initialize the distance array
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        //input the bus routes
        Edge[] edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[i] = (new Edge(start, end, cost));
        }

        //start Bellman-Ford algorithm
        if (BellmanFord(dist, edges)) {
            Arrays.stream(dist, 2, N + 1)
                    .forEach(a -> sb.append(a == INF ? -1 : a).append("\n"));
        } else {
            sb.append(-1);
        }

        System.out.println(sb);
    }
}