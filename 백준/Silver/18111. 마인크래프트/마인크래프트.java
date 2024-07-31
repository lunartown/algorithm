import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int range = N * M;
        int[] ground = new int[range];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ground[i * M + j] = Integer.parseInt(st.nextToken());
                sum += ground[i * M + j];
            }
        }

        Arrays.sort(ground);

        int idx = 1;
        int minHeight = ground[0];
        int dig = sum - range * minHeight;
        int pile = 0;
        int min = dig * 2 + pile;

        for(int height = ground[0] + 1; height <= ground[range - 1]; height++) {
            while(height > ground[idx]) idx++;
            dig -= range - idx;
            pile += idx;
            if(dig + B < pile) break;
            int time = dig * 2 + pile;
            if(time <= min) {
                min = time;
                minHeight = height;
            }
        }

        sb.append(min).append(' ').append(minHeight);
        System.out.println(sb);
    }
}