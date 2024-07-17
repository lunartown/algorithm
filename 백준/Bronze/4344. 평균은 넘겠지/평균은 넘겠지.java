import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int C = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < C; tc++) {
            double percent = 0;
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] scores = new int[N];
            int sum = 0;
            double average;

            for (int i = 0; i < N; i++) {
                scores[i] = Integer.parseInt(st.nextToken());
                sum += scores[i];
            }

            average = (double) sum / (double) N;

            for (int i = 0; i < N; i++) {
                if (scores[i] > average) {
                    percent += 1;
                }
            }

            percent = percent * 100 / N;
            sb.append(String.format("%.3f",percent)).append("%").append("\n");
        }

        System.out.println(sb);

    }
}