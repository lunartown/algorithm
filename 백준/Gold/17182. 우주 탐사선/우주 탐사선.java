import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static final int INF = 1_000_000_007;

    static int N;
    static int[][] dist;
    static int[][] dp;

    static int tsp(int cur, int mask) {
        // 모든 도시를 방문했을 경우 더 이상 비용을 추가하지 않음
        if (mask == (1 << N) - 1)
            return 0;

        // 이미 계산된 상태라면 저장된 값 반환
        if (dp[cur][mask] != -1)
            return dp[cur][mask];

        dp[cur][mask] = INF;

        for (int next = 0; next < N; next++) {
            if ((mask & (1 << next)) == 0) {
                int newCost = tsp(next, mask | (1 << next)) + dist[cur][next];
                // 가지치기 조건: 현재 경로가 이미 계산된 경로보다 비싸면 무시
                if (newCost < dp[cur][mask]) {
                    dp[cur][mask] = newCost;
                }
            }
        }

        return dp[cur][mask];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            dist[i] = Stream.of(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        // 플로이드-워셜 알고리즘을 사용하여 모든 쌍 최단 경로 계산
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        dp = new int[N][1 << N];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        System.out.println(tsp(K, 1 << K));
    }
}