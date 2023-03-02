import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge>{
	int from, to, cost;
	
	Edge(int from, int to, int cost){
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}
	
	@Override
	public String toString() {
		return from + " " + to + " " + cost;
	}
}

public class Main {
	static void init(int[] parent) {
		for(int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}
	}
	
	static int find(int[] parent, int a) {
		if(parent[a] < 0) return a;
		return parent[a] = find(parent, parent[a]);
	}
	
	static void union(int[] parent, int a, int b) {
		int rootA = find(parent, a);
		int rootB = find(parent, b);
		
		
		if(rootA != rootB) {
			if(parent[rootA] < parent[rootB]) {
				parent[rootB] = rootA;
			} else {
				parent[rootB] = Math.min(parent[rootB], parent[rootA] - 1);
				parent[rootA] = rootB;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int from, to, sum, num;
	
		st = new StringTokenizer(in.readLine());
			
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
			
		int[] parent = new int[N+1];
			
		init(parent);
		
		Edge[] edge = new Edge[M+N];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			edge[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++) {
			edge[M+i] = new Edge(i + 1, 0, Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(edge);
		
		sum = 0; num = 0;
		for(Edge e : edge) {
			from = e.from;
			to = e.to;
			
			if(find(parent, from) != find(parent, to)) {
				union(parent, from, to);
				sum += e.cost;
				num++;
			}
			
				if(num >= N) break;
		}
		
		System.out.println(sum);
		
	}
}