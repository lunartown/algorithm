import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge {
	int to;
	int cost;
	
	Edge(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}
}

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(in.readLine());
		
		List<Edge>[] edges = new ArrayList[V+1];
		for(int i = 0; i <= V; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[u].add(new Edge(v, w));
		}
		
		int[] dist = new int[V+1];
		for(int i = 0; i <= V; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		PriorityQueue<Edge> queue = new PriorityQueue<>((Edge e1, Edge e2) -> e1.cost - e2.cost);
		
		queue.offer(new Edge(K, 0));
		dist[K] = 0;
		
		while(!queue.isEmpty()) {
			int node = queue.peek().to;
			int cost = queue.poll().cost;
			
			if(cost > dist[node]) continue;
			
			for(Edge edge : edges[node]) {
				if(cost + edge.cost < dist[edge.to]) {
					dist[edge.to] = cost + edge.cost;
					queue.offer(new Edge(edge.to, dist[edge.to]));
				}
			}
		}
		
		for(int i = 1; i <= V; i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				sb.append("INF").append('\n');
			}else sb.append(dist[i]).append('\n');
		}
		
		System.out.println(sb);
	}
}