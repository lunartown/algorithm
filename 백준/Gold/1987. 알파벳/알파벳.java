import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		char[][] board = new char[R][C];
		boolean[][] vis = new boolean[R][C];
		boolean[] alpha = new boolean[26];
		String str;
		
		for(int i = 0; i < R; i++) {
			str = in.readLine();
			for(int j = 0; j < C; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		
		alpha[board[0][0] - 'A'] = true;
		search(0, 0, 1, alpha, board, vis);
		System.out.println(max);
	}

	static int R, C, max = 1;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	private static void search(int curX, int curY, int len, boolean[] alpha, char[][] board, boolean[][] vis) {		
		for(int i = 0; i < 4; i++) {
			int nx = curX + dx[i];
			int ny = curY + dy[i];
			if(nx < 0 || nx >= R || ny < 0 || ny >= C
					|| vis[nx][ny]
							|| alpha[board[nx][ny] - 'A']) continue;
			
			vis[nx][ny] = true;
			alpha[board[nx][ny] - 'A'] = true;
			search(nx, ny, len + 1, alpha, board, vis);
			vis[nx][ny] = false;
			alpha[board[nx][ny] - 'A'] = false;
		}
		
		max = Math.max(max, len);
	}
}