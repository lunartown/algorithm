import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class Main {
	static int R, C, vis[][], max = 1;
	static char[][] board;
	
	private static void search(int curX, int curY, int len, int alpha) {		
		max = Math.max(max, len);
		if(max == 26) return;
		int nextAlpha, nx, ny;
		
		if(vis[curX][curY] == alpha) return;
		vis[curX][curY] = alpha;
		
		if(curX > 0) {
			nx = curX - 1;
			nextAlpha = 1 << (board[nx][curY] - 'A');
			
			if((alpha & nextAlpha) == 0) {
				search(nx, curY, len + 1, alpha | (1 << (board[nx][curY] - 'A')));
			}
		}
		
		if(curX < R - 1) {
			nx = curX + 1;
			nextAlpha = 1 << (board[nx][curY] - 'A');
			
			if((alpha & nextAlpha) == 0) {
				search(nx, curY, len + 1, alpha | (1 << (board[nx][curY] - 'A')));
			}
		}
		
		if(curY > 0) {
			ny = curY - 1;
			nextAlpha = 1 << (board[curX][ny] - 'A');
			
			if((alpha & nextAlpha) == 0) {
				search(curX, ny, len + 1, alpha | (1 << (board[curX][ny] - 'A')));
			}
		}
		
		if(curY < C - 1) {
			ny = curY + 1;
			nextAlpha = 1 << (board[curX][ny] - 'A');
			
			if((alpha & nextAlpha) == 0) {
				search(curX, ny, len + 1, alpha | (1 << (board[curX][ny] - 'A')));
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new char[R][];
		vis = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			board[i] = in.readLine().toCharArray();
		}
		
		search(0, 0, 1, 1 << (board[0][0] - 'A'));
		System.out.println(max);
	}
}