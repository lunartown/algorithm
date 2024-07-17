import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 배열의 합 구하기
// 1) i, j 부터 x, y까지 정직하게 구한다
// 2) 누적합 배열을 만들어서 한 번에 구한다
// 두 가지 다 해보자

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N+1][M+1];
        st = new StringTokenizer(br.readLine());
        arr[1][1] = Integer.parseInt(st.nextToken());
        for(int i = 2; i <= M; i++) {
            arr[1][i] = arr[1][i - 1] + Integer.parseInt(st.nextToken());
        }

        for(int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][1] = arr[i - 1][1] + Integer.parseInt(st.nextToken());
            for(int j = 2; j <= M; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1] + Integer.parseInt(st.nextToken());
            }
        }

        int K = Integer.parseInt(br.readLine());
        for(int idx = 0; idx < K; idx++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            sb.append(arr[x][y] - arr[i - 1][y] - arr[x][j - 1] + arr[i - 1][j - 1]).append("\n");
        }

        System.out.println(sb);
    }
}