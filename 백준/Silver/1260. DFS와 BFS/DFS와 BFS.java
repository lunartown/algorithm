import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.ArrayDeque;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Queue;
	import java.util.StringTokenizer;
	
	public class Main {
		
		public static void main(String[] args) throws IOException {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N+1];
			for(int i = 0; i <= N; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			int a, b;
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				adjList[a].add(b);
				adjList[b].add(a);
			}
	
			for(int i = 0; i <= N; i++) {
				Collections.sort(adjList[i]);
			}
			
			boolean[] vis = new boolean[N+1];
			vis[V] = true;
			dfs(vis, V);
			
			sb.append('\n');
			
			bfs(V);
			
			System.out.println(sb.toString());
		}
		
		static int N, M, V;
		static ArrayList<Integer>[] adjList;
		static StringBuilder sb = new StringBuilder();
		
		static void dfs(boolean[] vis, int v) {
			sb.append(v).append(' ');
			
			if(adjList[v].isEmpty()) return;
			
			for(int i = 0; i < adjList[v].size(); i++) {
				if(vis[adjList[v].get(i)]) continue;
				vis[adjList[v].get(i)] = true;
				dfs(vis, adjList[v].get(i));
			}
		}
		
		static void bfs(int init) {
			Queue<Integer> queue = new ArrayDeque<>();
			boolean[] vis = new boolean[N+1];
			queue.offer(init);
			vis[init] = true;
			int v;
			
			while(!queue.isEmpty()) {
				v = queue.poll();
				sb.append(v).append(' ');
				
				if(adjList[v].isEmpty()) continue;
				
				for(int i = 0; i < adjList[v].size(); i++) {
					if(vis[adjList[v].get(i)]) continue;
					vis[adjList[v].get(i)] = true;
					queue.offer(adjList[v].get(i));
				}
			}
		}
	}