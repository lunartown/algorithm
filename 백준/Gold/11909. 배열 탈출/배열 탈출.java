import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int MAX = 1_000_007;
    static int N;
    static int[][] dp;
    static int count = 0;

    static boolean isMovable(int x, int y) {
        return x < N && y < N;
    }

    static int minus(int a, int b) {
        return a <= b ? b - a + 1 : 0;
    }

    static int dfs(int[][] arr, int x, int y) {
        if(x == N - 1 && y == N - 1) {
            return 0;
        }

        if(dp[x][y] != MAX) {
            return dp[x][y];
        }

        for(int i = 0; i < 2; i++) {
            int nx = x + i;
            int ny = y + (i ^ 1);

            if(isMovable(nx, ny)) {
                if (dp[nx][ny] == MAX) {
                    dp[nx][ny] = dfs(arr, nx, ny);
                }

                dp[x][y] = Math.min(dp[x][y], dp[nx][ny] + minus(arr[x][y], arr[nx][ny]));
            }
        }

        return dp[x][y];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][];

        for(int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp = new int[N][N];
        dp[N - 1][N - 1] = 0;

        for(int i = 0; i < N; i++) {
            Arrays.fill(dp[i], MAX);
        }

        System.out.println(dfs(arr, 0, 0));
    }
}