import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// i번째 수부터 j번째 수까지의 합 = M
// 직접 세기

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        for(int i = 0; i < N; i++) {
            int sum = 0;
            for(int j = i; j < N; j++) {
                sum += arr[j];
                if(sum < M) continue;
                if(sum == M) count++;
                break;
            }
        }

        System.out.println(count);
    }
}