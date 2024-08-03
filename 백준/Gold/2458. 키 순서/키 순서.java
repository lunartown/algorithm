import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] students = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            students[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    students[i][j] = students[i][j] | (students[i][k] & students[k][j]);
                }
            }
        }

        long count = IntStream.range(1, N + 1).filter(i ->
        {
            int shorter = IntStream.range(1, N + 1).map(j -> students[j][i]).sum();
            int taller = Arrays.stream(students[i]).sum();
            return shorter + taller == N - 1;
        }).count();

        System.out.println(count);
    }
}