import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
	
public class Main {	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int a, b, exist = 0;
		
		List<Integer>[] friend = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			friend[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			friend[a].add(b);
			friend[b].add(a);
		}
		
		for(int i = 0; i < N; i++) {
			for(int j : friend[i]) {
				for(int k : friend[j]) {
					if(k == i) continue;
					for(int l : friend[k]) {
						if(l == i || l == j) continue;
						for(int m : friend[l]) {
							if(m == i || m == j || m == k) continue;
							exist = 1;
						}
					}
				}
			}
		}
		
		System.out.println(exist);
		
	}
}