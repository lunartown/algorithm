import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//화성 탐사하기
//N-1 * M 배열을 만들어 dp 한다
public class Main {
    static int N, M;
    static int[][] sum;
    static int[][] dp;

    static void explore(int depth, int idx) {
        //내가 내려온 곳 +
        int value = dp[depth][idx];

        //다음 탐색지점
        for (int i = 1; i <= M; i++) {
            //다음 탐색 비용 : 지금 비용 + 합계
            int newValue = value;
            if (i < idx)
                newValue += sum[depth][idx] - sum[depth][i - 1];
            else
                newValue += sum[depth][i] - sum[depth][idx - 1];
            if (newValue > dp[depth + 1][i]) {
                dp[depth + 1][i] = newValue;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

//        누적합 입력
        sum = new int[N][M + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sum[i][0] = 0;
            for (int j = 1; j <= M; j++) {
                sum[i][j] = sum[i][j - 1] + Integer.parseInt(st.nextToken());
            }
        }

        //dp
        dp = new int[N + 1][M + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], -100001);
        }
        dp[0][1] = 0;
        explore(0, 1);
        for (int depth = 1; depth < N - 1; depth++) {
            for (int idx = 1; idx <= M; idx++) {
                explore(depth, idx);
            }
        }

        //depth == N - 1이면 종료
        for (int idx = 1; idx <= M; idx++) {
            int newValue = dp[N - 1][idx] + sum[N - 1][M] - sum[N - 1][idx - 1];
            if (newValue > dp[N][M])
                dp[N][M] = newValue;
        }


        System.out.println(dp[N][M]);
    }
}