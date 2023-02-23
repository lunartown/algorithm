import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class Main {
	static int R, C, max = 1;
	
	private static void search(int curX, int curY, int len, int alpha, char[][] board, int[][] vis) {		
		max = Math.max(max, len);
		if(max == 26) return;
		int nextAlpha, nx, ny;
		
		if(curX > 0) {
			nx = curX - 1;
			nextAlpha = 1 << (board[nx][curY] - 'A');
			
			if((alpha & nextAlpha) == 0
					&& (alpha | nextAlpha) != vis[nx][curY]) {
				vis[nx][curY] = alpha;
				search(nx, curY, len + 1, alpha | (1 << (board[nx][curY] - 'A')), board, vis);
			}
		}
		
		if(curX < R - 1) {
			nx = curX + 1;
			nextAlpha = 1 << (board[nx][curY] - 'A');
			
			if((alpha & nextAlpha) == 0
					&& (alpha | nextAlpha) != vis[nx][curY]) {
				vis[nx][curY] = alpha;
				search(nx, curY, len + 1, alpha | (1 << (board[nx][curY] - 'A')), board, vis);
			}
		}
		
		if(curY > 0) {
			ny = curY - 1;
			nextAlpha = 1 << (board[curX][ny] - 'A');
			
			if((alpha & nextAlpha) == 0
					&& (alpha | nextAlpha) != vis[curX][ny]) {
				vis[curX][ny] = alpha;
				search(curX, ny, len + 1, alpha | (1 << (board[curX][ny] - 'A')), board, vis);
			}
		}
		
		if(curY < C - 1) {
			ny = curY + 1;
			nextAlpha = 1 << (board[curX][ny] - 'A');
			
			if((alpha & nextAlpha) == 0
					&& (alpha | nextAlpha) != vis[curX][ny]) {
				vis[curX][ny] = alpha;
				search(curX, ny, len + 1, alpha | (1 << (board[curX][ny] - 'A')), board, vis);
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		char[][] board = new char[R][C];
		int[][] vis = new int[R][C];
		String str;
		
		for(int i = 0; i < R; i++) {
			str = in.readLine();
			for(int j = 0; j < C; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		
		int alpha = 1 << (board[0][0] - 'A');
		search(0, 0, 1, alpha, board, vis);
		System.out.println(max);
	}
}