import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static int N, W[][];
	static int[][] dp;
	
	static int permutation(int idx, int flag) {
		if(dp[idx][flag] == 0) {
			dp[idx][flag] = INF;
		}else return dp[idx][flag];
		
		if(((1 << idx) | flag) == ((1 << (N + 1)) - 2)){
			return dp[idx][flag] = W[idx][1];
		}
		
		for(int i = 1; i <= N; i++) {
			if(i == idx) continue;
			if(((1 << i) & flag) != 0) continue;
			dp[idx][flag] = Math.min(dp[idx][flag], plus(W[idx][i], permutation(i, ((1 << idx) | flag))));
		}
		
		return dp[idx][flag];
	}
	
	private static int plus(int a, int b) {
		if(a == INF || b == INF) return INF;
		else return a + b;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(in.readLine());
		W = new int[N + 1][N + 1];
		dp = new int[N + 1][1 << (N + 1)];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 1; j <= N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
				if(W[i][j] == 0)
					W[i][j] = INF;
			}
		}

		System.out.println(permutation(1, 0));
	}
}