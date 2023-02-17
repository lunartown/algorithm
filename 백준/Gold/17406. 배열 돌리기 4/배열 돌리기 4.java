import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;



public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N+1][M+1];
		arr2 = new int[N+1][M+1];
		copyArr = new int[N+1][M+1];
		permu = new int[6*5*4*3*2*1][3];
		op = new int[K][3];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < 3; j++) {
				op[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= M; j++)
//				System.out.print(arr[i][j] + " ");
//			System.out.println();
//		}
//		
//		System.out.println();
		
		permutation(0, 0);
		System.out.println(min);
		
	}
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][]arr, arr2, copyArr, op, permu;
	static int N, M, K, cnt, row;
	static int min = Integer.MAX_VALUE;
	
	private static void permutation(int depth, int flag) {
		if(depth >= K) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= M; j++) {
					arr2[i][j] = arr[i][j];
				}
			}
			for(int i = 0; i < K; i++)
				operation(permu[i][0] - permu[i][2], permu[i][1] - permu[i][2], permu[i][2]);
			for(int i = 1; i <= N; i++) {
				row = 0;
				for(int j = 1; j <= M; j++)
					row += copyArr[i][j];
				min = Math.min(min,row);
			}
			return;
		}
		
		for(int i = 0; i < K; i++) {
			if((flag & (1 << i)) != 0) continue;
			permu[depth] = op[i];
//			System.out.println(depth + " " + i);
			permutation(depth+1, flag | (1 << i));
		}
	}
	
	private static void operation(int x, int y, int width) {
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				copyArr[i][j] = arr2[i][j];
			}
		}
		for(int i = 0; i <= width; i++) {
			if(i==0) { copyArr[x+width][y+width] = arr2[x+width][y+width]; }else {
			int nx = width - i, ny = width - i, k = 0;
			for(int j = 0; j < 8 * i; j++) {
				if(nx + dx[k] <  width - i || nx + dx[k] >= width + i + 1 || ny + dy[k] <  width - i || ny + dy[k] >= width + i + 1) k = k==3? 0 : k+1;
				copyArr[x + nx + dx[k]][y + ny + dy[k]] = arr2[x + nx][y + ny];
				nx += dx[k];
				ny += dy[k];
			}
			}
		}
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				arr2[i][j] = copyArr[i][j];
			}
		}		
	}
}