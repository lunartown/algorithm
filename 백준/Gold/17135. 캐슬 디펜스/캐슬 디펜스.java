import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
	
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[N][M];
		int[] select = new int[3];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
					
			}
		}
		
		combination(0, 0, select, board);
		
		System.out.println(max);
	}
	
	static int N, M, D, max;
	
	static void combination(int idx, int cnt, int[] select, int[][] board) {
		if(cnt > M) return;
		if(idx >= 3) {
			int[][] boardClone = new int[N][M];
			for(int i = 0; i < N; i++) {
				boardClone[i] = board[i].clone();
			}
			max = Math.max(max, play(N, D, select, boardClone, 0));
			return;
		}
		
		combination(idx, cnt + 1, select, board);
		select[idx] = cnt;
		combination(idx + 1, cnt + 1,select, board);
	}

	private static int play(int N, int D, int[] select, int[][] board, int cnt) {
		if(N == 0) return cnt;
		
		int[][] target = new int[3][];
		
		for(int i = 0; i < 3; i++)
			target[i] = findtarget(N, D, select[i], board, 1);
		
		for(int i = 0; i < 3; i++) {
			if(target[i] != null && board[target[i][0]][target[i][1]] == 1) {
				board[target[i][0]][target[i][1]] = 0;
				cnt++;
			}
		}
		
		return play(N-1, D, select, board, cnt);
	}

	private static int[] findtarget(int N, int D, int pos, int[][] board, int dis) {
		if(dis > D) return null;
		for(int i = - (dis - 1); i <= dis - 1; i++) {
			if(pos + i < 0) continue;
			if(N - dis + Math.abs(i) < 0) continue;
			if(pos + i >= M) break;
			
			if(board[N - dis + Math.abs(i)][pos + i] == 1) 
				return new int[] {N - dis + Math.abs(i), pos + i};
		}
		return findtarget(N, D, pos, board, dis+1);
	}
}