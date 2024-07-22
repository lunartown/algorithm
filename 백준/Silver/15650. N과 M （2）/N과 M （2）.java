import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	
	static StringBuilder sb = new StringBuilder();
	static int N, M, select[];
	
	static void combination(int start, int cnt) {
		if(cnt == M) {
			for(int i = 0; i < M; i++) {
				sb.append(select[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		if(start == N) return;

		select[cnt] = start + 1;
		combination(start + 1, cnt + 1);
		combination(start + 1, cnt);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		select = new int[M];
		combination(0, 0);
		System.out.println(sb);
	}
}