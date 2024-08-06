import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

//서강그라운드
//플로이드 워셜
//m 이상이면 중지
class Edge {
    int to;
    int cost;

    public Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

public class Main {
    static final int INF = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //노드 수
        int n = Integer.parseInt(st.nextToken());
        //수색범위
        int m = Integer.parseInt(st.nextToken());
        //길의 개수
        int r = Integer.parseInt(st.nextToken());

        //아이템 개수
        int[] items = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        //플로이드 워셜
        int[][] cost = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(cost[i], INF);
            cost[i][i] = 0;
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            cost[a][b] = c;
            cost[b][a] = c;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                }
            }
        }

        //비용 체크
        int[] maxItem = Arrays.stream(cost, 1, n + 1)
                .mapToInt(a -> IntStream.range(1, n + 1)
                        .filter(i -> a[i] <= m)
                        .map(i -> items[i])
                        .sum())
                .toArray();

        System.out.println(Arrays.stream(maxItem).max().getAsInt());
    }
}