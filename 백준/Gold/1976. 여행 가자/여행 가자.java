import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;
	
public class Main {
	private static void init(int[] connect) {
		for(int i = 0; i < connect.length; i++) {
			connect[i] = -1;
		}
	}
	
	private static int find(int[] connect, int a) {
		if(connect[a] < 0) return a;
		return connect[a] = find(connect, connect[a]);
	}
	
	private static void union(int[] connect, int a, int b) {
		int rootA = find(connect, a);
		int rootB = find(connect, b);
		
		if(rootA != rootB) {
			if(connect[rootA] < connect[rootB]) {
				connect[rootA] = Math.min(connect[rootA], connect[rootB] - 1);
				connect[rootB] = rootA;
			}else {
				connect[rootB] = Math.min(connect[rootB], connect[rootA] - 1);
				connect[rootA] = rootB;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		
		int[][] city = new int[N][];
		
		for(int i = 0; i < N; i++) {
			city[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		int[] connect = new int[N];
		init(connect);
		
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				if(city[i][j] == 1) {
					union(connect, i, j);
				}
			}
		}
		
		st = new StringTokenizer(in.readLine());
		int firstCity = find(connect, Integer.parseInt(st.nextToken())-1);
		boolean able = true;
//		
//		System.out.println(Arrays.toString(connect));
//		System.out.println(firstCity);
		
		for(int i = 1; i < M; i++) {
			if(firstCity != find(connect, Integer.parseInt(st.nextToken())-1)){
				able = false;
				break;
			}
		}
		
		System.out.println(able?"YES":"NO");
	}
}