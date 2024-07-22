import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static boolean[] vis;
    static int[] arr;
    static int[] out;

    public static void permu(int k){
        if(k == M) {
            for(int i = 0; i < M; i++) {
                sb.append(out[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        int before = 0;

        for(int i = 0; i < N; i++) {
            if(vis[i]) continue;
            if(before == arr[i]) continue;

            before = arr[i];
            out[k] = arr[i];
            vis[i] = true;
            permu(k + 1);
            vis[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        vis = new boolean[N];
        out = new int[M];
        arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr);
        permu(0);
        System.out.println(sb);
    }
}