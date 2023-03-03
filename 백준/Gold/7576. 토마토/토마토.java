import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
//		System.setOut(new PrintStream("res/output.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		Queue<int[]> queue = new ArrayDeque<>();
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] box = new int[N][M];
		int unripe = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j] == 0) unripe++;
				if(box[i][j] == 1) queue.offer(new int[] {i, j});
			}
		}
		
		int day = 0;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				int[] tomato = queue.poll();
				for(int j = 0; j < 4; j++) {
					int nx = tomato[0] + dx[j];
					int ny = tomato[1] + dy[j];
					if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
					if(box[nx][ny] == 0) {
						box[nx][ny] = 1;
						unripe--;
						queue.offer(new int[] {nx, ny});
					}
				}
			}
			day++;
		}
		if(unripe == 0)	System.out.println(day-1);
		else System.out.println(-1);
		
	}
}