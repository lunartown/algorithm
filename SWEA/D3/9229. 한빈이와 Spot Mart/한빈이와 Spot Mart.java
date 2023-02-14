import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(in.readLine());
		for(int i = 1; i <= T; i++) {
			sb.append('#').append(i).append(' ');
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			max = -1;
			
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < N; j++)
				bags[j] = Integer.parseInt(st.nextToken());
			
			comb(0, 0, 0);
			
			sb.append(max).append('\n');
		}
		
		System.out.println(sb);
	}
	
	static int[] bags = new int[1000];
	static int N, M, max;
	
	static void comb(int start, int idx, int sum) {
		if(idx == 2) {
			if(sum >= max && sum <= M) max = sum;
			return;
		}
		
		for(int i = start ; i < N ; i++) {
			comb(i + 1, idx + 1, sum + bags[i]);
		}
	}
}