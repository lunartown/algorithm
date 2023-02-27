import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class Main {
	static void init(int[] set) {
		for(int i = 1; i < set.length; i++) {
			set[i] = i;
		}
	}
	
	static int find(int[] set, int a) {
		if(set[a] == a) return a;
		set[a] = find(set, set[a]);
		return(set[a]);
	}
	
	static void union(int[] set, int a, int b) {
		int rootA = find(set, a);
		int rootB = find(set, b);

		if(rootA != rootB) {
			set[rootB] = rootA;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
			
		st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
			
		int[] set = new int[N+1];
			
		init(set);
			
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			if(Integer.parseInt(st.nextToken()) == 0) {
				union(set, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}else {
				if(find(set, Integer.parseInt(st.nextToken())) == find(set,Integer.parseInt(st.nextToken()))) {
					sb.append("YES");
					}else sb.append("NO");
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}
}