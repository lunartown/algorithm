import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		String A = st.nextToken();
		int B = Integer.parseInt(st.nextToken());
		
		int[] num = new int[A.length()];
		int[] out = new int[A.length()];
		boolean[] vis = new boolean[A.length()];
		for(int i = 0; i < A.length(); i++)
			num[i] = A.charAt(i) - '0';
		
		
		permu(num, out, vis, 0, B, A.length());
		
		if(check == -1) System.out.println(check);
		else System.out.println(max);
	}
	
	static StringBuilder sb = null;
	static int max;
	static int check = -1;
	
	static void permu(int[] num, int[] out, boolean[] vis, int depth, int B, int n) {
		if(depth == n) {
			sb = new StringBuilder();
			for(int i = 0; i < n; i++) {
				sb.append(out[i]);
			}
			if(out[0]!= 0 && Integer.parseInt(sb.toString()) < B) {
				check = 1;
				max = Math.max(max, Integer.parseInt(sb.toString()));
			}
		}
		
		for(int i = 0; i < n; i++) {
			if(!vis[i]) {
				vis[i] = true;
				out[depth] = num[i];
				permu(num, out, vis, depth + 1, B, n);
				vis[i] = false;
			}
		}
	}
	
}