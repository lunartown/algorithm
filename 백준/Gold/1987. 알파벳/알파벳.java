import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
	
public class Main {
	static int R, C, max = 1;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	private static void search(int curX, int curY, int len, int alpha, char[][] board, int[][] vis) {		
		max = Math.max(max, len);
		if(max == 26) return;
		
		for(int i = 0; i < 4; i++) {
			int nx = curX + dx[i];
			int ny = curY + dy[i];
			if((alpha & (1 << (board[nx][ny] - 'A'))) != 0
				|| (alpha | (1 << (board[nx][ny] - 'A'))) == vis[nx][ny]) continue;
			
			vis[nx][ny] = alpha;
			search(nx, ny, len + 1, alpha | (1 << (board[nx][ny] - 'A')), board, vis);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		char[][] board = new char[R+2][C+2];
		int[][] vis = new int[R+1][C+1];
		String str;
		
		for(int i = 1; i <= R; i++) {
			str = in.readLine();
			for(int j = 1; j <= C; j++) {
				board[i][j] = str.charAt(j-1);
			}
		}
		
		char wall = board[1][1];
        Arrays.fill(board[0], wall);
        Arrays.fill(board[R + 1], wall);
        for (int i = 0; i <= R + 1; i++){
            for (int j : new int[]{0, C+1}){
                board[i][j] = wall;
            }
        }
		
		int alpha = 1 << (board[0][0] - 'A');
		search(1, 1, 1, alpha, board, vis);
		System.out.println(max);
	}
}