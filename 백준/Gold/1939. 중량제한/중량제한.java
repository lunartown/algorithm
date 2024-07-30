import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Edge {
		int node;
		long cost;
		
		Edge(int node, long cost) {
			this.node = node;
			this.cost = cost;
		}
	}
	
	static int N, M, factory1, factory2;
	static List<Edge>[] edges;
	static long maxCost;
	static boolean[] vis;
	
	static boolean bfs(long cost) {
		Queue<Integer> queue = new ArrayDeque<>();
		vis[factory1] = true;
		queue.offer(factory1);
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			if(node == factory2) return true;
			
			for(int i = 0; i < edges[node].size(); i++) {
				int nextNode = edges[node].get(i).node;
				long nextCost = edges[node].get(i).cost;
				
				 if (!vis[nextNode] && cost <= nextCost) {
                         vis[nextNode] = true;
                         queue.offer(nextNode);
                 }
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		vis = new boolean[N + 1];
		edges = new List[N + 1];
		for(int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			edges[a].add(new Edge(b, c));
			edges[b].add(new Edge(a, c));
			
			maxCost = Math.max(c, maxCost);
		}
		
		st = new StringTokenizer(in.readLine());
		factory1 = Integer.parseInt(st.nextToken());
		factory2 = Integer.parseInt(st.nextToken());
		
		long start = 0;
		long end = maxCost;
		
		while(start <= end) {
			long mid = (start + end) / 2;
			
			for(int i = 1; i <= N; i++) vis[i] = false;
			if(bfs(mid)) start = mid + 1;
			else end = mid - 1;
		}
		
		System.out.println(end);
	}
}