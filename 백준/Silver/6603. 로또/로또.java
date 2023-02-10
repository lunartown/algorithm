import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while(true) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N == 0) break;
			num = new int[N+1];
			
			for(int i = 1; i <= N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			lotto(1,0, "");
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	static StringBuilder sb = new StringBuilder();
	static int[] num;
	static int N;
	
	static void lotto(int idx, int cnt, String str) {
		if(cnt == 6) {
			sb.append(str).append('\n');
			return;
		}
		
		if(idx > N) return;
		
		lotto(idx+1, cnt+1, str + num[idx] +' ');
		lotto(idx+1, cnt, str);
	}
}