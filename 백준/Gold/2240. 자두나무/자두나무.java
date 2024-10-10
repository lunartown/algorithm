import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    static int T, W;
    static int[][] tree, sumTree;
    static int[][][] dp;

    static int dfs(int cur, int idx, int cnt) {
//        System.out.println(cur + " " + idx + " " + cnt);

        if(idx >= T) return 0;

        if(cnt == 0 || sumTree[cur][idx] == T - idx) {
            return dp[cur][idx][cnt] = sumTree[cur][idx];
        }

        if(dp[cur][idx][cnt] != 0) {
            return dp[cur][idx][cnt];
        }

        dp[cur][idx][cnt] = Math.max(tree[cur][idx] + dfs(cur, idx + 1, cnt), dfs(cur ^ 1, idx, cnt - 1));

//        System.out.println(cur + " " + idx + " " + cnt + " " + dp[cur][idx][cnt]);

        return dp[cur][idx][cnt];
    }

    public static void main(String[] args) throws IOException {
        //dp[cur][idx][cnt] : cur, idx 위치에서 cnt만큼 이동 가능할 때 최대 먹을 수 있는 수
        //tree[cur][idx] : idx 위치에서 cur번 나무의 자두 수 (0, 1)
        //sumTree[cur][idx] : idx, cur 위치에서 계속 서있을 때 먹을 수 있는 자두 수
        //dp[cur][idx][cnt] = tree[cur][idx] + Math.max(dp[cur][idx + 1][cnt], dp[cur ^ 1][idx + 1][cnt - 1]);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        tree = new int[2][T];
        for(int i = 0; i < T; i++) {
            int treeNo = Integer.parseInt(br.readLine());
            switch(treeNo) {
                case 1:
                    tree[0][i] = 1;
                    break;
                case 2:
                    tree[1][i] = 1;
                    break;
                default:
            }
        }

        sumTree = new int[2][T];
        sumTree[0][T - 1] = tree[0][T - 1];
        sumTree[1][T - 1] = tree[1][T - 1];
        for(int i = T - 2; i >= 0; i--) {
            sumTree[0][i] = sumTree[0][i + 1] + tree[0][i];
            sumTree[1][i] = sumTree[1][i + 1] + tree[1][i];
        }

//        System.out.println(Arrays.toString(tree[0]));
//        System.out.println(Arrays.toString(tree[1]));
//        System.out.println(Arrays.toString(sumTree[0]));
//        System.out.println(Arrays.toString(sumTree[1]));

        dp = new int[2][T][W + 1];
        System.out.println(dfs(0, 0, W));
    }
}
