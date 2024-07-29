import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] node;
    static boolean[] virus;
    static int ans;

    static void dfs(int num) {
        for (int i = 0; i < node[num].size(); i++) {
            int next = node[num].get(i);
            if (!virus[next]) {
                virus[next] = true;
                ans++;
                dfs(next);
            }
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        virus = new boolean[N + 1];
        node = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            node[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            node[a].add(b);
            node[b].add(a);
        }

        virus[1] = true;
        dfs(1);

        System.out.println(ans);
    }
}