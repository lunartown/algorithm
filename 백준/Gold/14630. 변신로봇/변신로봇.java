import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//변신로봇 코스트 최솟값
//다익스트라로 푼다
public class Main {
    private static class Node implements Comparable<Node> {
        int position;
        int cost;

        public Node(int position, int cost) {
            this.position = position;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    private static final int INF = Integer.MAX_VALUE - 8;
    private static int[][] edgeCost;
    private static int[] costs;
    private static int N;

    private static void dijkstra(int start) {
        costs[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.position;
            int cost = node.cost;
            if (cost > costs[cur])
                continue;
            for(int next = 0; next < N; next++) {
                int nextCost = cost + edgeCost[cur][next];
                if(nextCost < costs[next]) {
                    costs[next] = nextCost;
                    pq.offer(new Node(next, nextCost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //부품 입력
        N = Integer.parseInt(br.readLine());
        int[][] parts = new int[N][];
        for(int i = 0; i < N; i++) {
            parts[i] = br.readLine().chars()
                    .map(x -> x - '0')
                    .toArray();
        }

        //그래프 그리기
        edgeCost = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j) {
                    edgeCost[i][j] = 0;
                } else {
                    int sum = 0;
                    for(int k = 0; k < parts[i].length; k++) {
                        sum += (parts[i][k] - parts[j][k]) * (parts[i][k] - parts[j][k]);
                    }
                    edgeCost[i][j] = sum;
                    edgeCost[j][i] = sum;
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;

        //다익스트라 준비
        costs = new int[N];
        for(int i = 0; i < N; i++) {
            costs[i] = INF;
        }
        dijkstra(start);
        System.out.println(costs[end]);
    }
}