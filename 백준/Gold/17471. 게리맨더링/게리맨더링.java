import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, total, half, max, popul[];
	static List<Integer>[] adjoin;
	
	static void permutation(int flag, int sum, int flagFlag) {
		if(sum > half) return;
		
		if(sum > max) {
			if(connect(flag) && connect((1 << N) - 1 - flag)) {
				max = sum;
			}
		}
		
		for(int i = 0; i < N; i++) {
			if((flag & (1 << i)) != 0) continue;
			if((flagFlag & (1 << (flag & (1 << i)))) != 0) continue;
			permutation(flag | (1 << i), sum + popul[i+1], flagFlag | (1 << (flag | (1 << i))));
		}
	}
	
	static boolean connect(int flag) {
		int root = 32 - Integer.numberOfLeadingZeros(flag);
		Queue<Integer> bfs = new ArrayDeque<>();
		int newFlag = flag - (1 << (root - 1));
		int vis = 1 << (root - 1);
		bfs.offer(root);
		
		while(!bfs.isEmpty()) {
			int node = bfs.poll();
			for(Integer nextNode : adjoin[node]) {
				if((vis & (1 << (nextNode - 1))) != 0) continue;
				if((flag & (1 << (nextNode - 1))) == 0) continue;
				vis = vis | (1 << (nextNode - 1));
				bfs.offer(nextNode);
				newFlag = newFlag & ~(1 << (nextNode - 1));
			}
		}
		
		if(newFlag == 0) return true;
		else return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		N = Integer.parseInt(in.readLine());
		popul = new int[N+1];

	
		st = new StringTokenizer(in.readLine());
		for(int i = 1; i <= N; i++) {
		popul[i] = Integer.parseInt(st.nextToken());
		total += popul[i];
		}
		
		adjoin = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			adjoin[i] = new ArrayList<>();
			st = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(st.nextToken());
			for(int j = 0; j < num; j++) {
				adjoin[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		max = 0;
		half = total / 2;
		
		permutation(0, 0, 0);
		
		System.out.println(max == 0? -1: total - 2 * max);
	}
}