import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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
//			System.out.println(Arrays.toString(select) + " " + play(N, D, select, boardClone, 0));
			return;
		}
		
		combination(idx, cnt + 1, select, board);
		select[idx] = cnt;
		combination(idx + 1, cnt + 1,select, board);
	}

	private static int play(int N, int D, int[] select, int[][] board, int cnt) {
		if(N == 0) return cnt;
		Set<Pair> targets = new HashSet<>();
		
		Pair target1 = findtarget(N, D, select[0], board, 1);
		Pair target2 = findtarget(N, D, select[1], board, 1);
		Pair target3 = findtarget(N, D, select[2], board, 1);
		
		if(target1 != null) { targets.add(target1); board[target1.left][target1.right] = 0; }
		if(target2 != null) { targets.add(target2); board[target2.left][target2.right] = 0; }
		if(target3 != null) { targets.add(target3); board[target3.left][target3.right] = 0; }
		
//		System.out.println("N : " + N + " D : " + D);
		
//		for(Pair pair : targets) {
//			System.out.print(pair + " ");
//		}
		
//		System.out.print("size : " + targets.size() + " D : " + D);
//		System.out.println();
		cnt += targets.size();
		
		return play(N-1, D, select, board, cnt);
	}

	private static Pair findtarget(int N, int D, int pos, int[][] board, int dis) {
		if(dis > D) return null;
		for(int i = - (dis - 1); i <= dis - 1; i++) {
			if(pos + i < 0) continue;
			if(N - dis + Math.abs(i) < 0) continue;
			if(pos + i >= M) break;
			if(board[N - dis + Math.abs(i)][pos + i] == 1) 
				return new Pair(N - dis + Math.abs(i), pos + i);
		}
		return findtarget(N, D, pos, board, dis+1);
	}
}

class Pair {
	int left;
	int right;
	
	public Pair(int left, int right) {
		super();
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "Pair [left=" + left + ", right=" + right + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + left;
		result = prime * result + right;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (left != other.left)
			return false;
		if (right != other.right)
			return false;
		return true;
	}
}