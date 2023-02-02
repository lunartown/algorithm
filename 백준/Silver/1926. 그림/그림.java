import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

 class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		Stack<Integer[]> bfs = new Stack<Integer[]>();
		int area = 0, paintings = 0, max = 0;
		Integer[] pair = new Integer[] {0,0};
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] paint = new int[n][m];
		boolean[][] vis = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < m; j++) {
				paint[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(paint[i][j] == 0 || vis[i][j] == true) continue; //검사 안 했고 색칠이라면
				pair = new Integer[] {i, j}; //  -> 검사시작, 순서쌍 만들고 푸쉬
				bfs.push(pair); //-> 여기부터 시작
				vis[i][j] = true;
				area = 1;
				
				
				while(!bfs.isEmpty()) {
					pair = bfs.pop(); // 현재 좌표를 pop, 색칠된 부분
					for(int k = 0; k < 4; k++) { // 상하좌우를 조사
						int nx = pair[0] + dx[k];
						int ny = pair[1] + dy[k];
						if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
						if(paint[nx][ny] != 1 || vis[nx][ny]) continue; 
						vis[nx][ny] = true; // 검사함
						bfs.push(new Integer[] {nx, ny}); // 다음 검사
						area++; // 면적 증가
					}
				}	
				paintings++;
				
				max = Math.max(area, max);
			}
		}
		
		System.out.println(paintings);
		System.out.println(max);
		
	}
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0 ,1, 0, -1};
}