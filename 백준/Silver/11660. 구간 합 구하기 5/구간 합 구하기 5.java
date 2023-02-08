import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] sum = new int[N+1][N+1];
		int x1, x2, y1, y2;
		
//		N개의 입력 받기
//		2차원 배열에서 합은 (1,1)부터 (i,i)까지 정사각형의 합으로 정의한다.
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 1; j < N + 1; j++)
//				큰 사각형 = (i, i) + 길쭉한 사각형 2개 - 공통부분
				sum[i][j] = Integer.parseInt(st.nextToken()) + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
		}
		
//		M개의 (x1, y1) (x2, y2) 좌표 입력받기
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
//			큰 사각형 - 길쭉한 사각형 2개 + 공통부분
			sb.append(sum[x2][y2] - sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1] + "\n");
		}
		
		System.out.println(sb);
	}
}
