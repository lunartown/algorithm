import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		Queue<Integer> bfs = new LinkedList<>();
		int[] road = new int[100001];
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		road[N] = 1;
		int time = 0;
		int cur = 0;
		bfs.add(N);
		
		label:while(!bfs.isEmpty()) {
			int size = bfs.size();
			for(int i = 0; i < size; i++) {
			cur = bfs.poll(); 
//			System.out.println(time + " cur = " + cur);
				if(cur == K) break label;
				for(int j = 1; j <= 3; j++) {
					switch(j) {
					case 1: if(cur-1 < 0 || road[cur-1] != 0) continue;
					bfs.add(cur-1);
					road[cur-1] = 1; break;
					case 2: if(cur+1 > 100000 || road[cur+1] != 0) continue;
					bfs.add(cur+1);
					road[cur+1] = 1; break;
					case 3: if(cur*2 > 100000 || road[cur*2] != 0) continue;
					bfs.add(cur*2);
					road[cur*2] = 1;
					}
				}
			}
			time++;
		}
		System.out.println(time);
	}
}