import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] sum = new int[N];
		
//		N개의 수의 누적합을 입력
		st = new StringTokenizer(in.readLine());
		sum [0] = Integer.parseInt(st.nextToken());
		for(int i = 1; i < N; i++)
			sum[i] = sum [i - 1] + Integer.parseInt(st.nextToken());
		
//		M번 동안 합을 출력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
//			합 = sum[to - 1] - sum[from - 2]
			if(from == 1) System.out.println(sum[to - 1]);
			else System.out.println((sum[to - 1] - sum[from - 2]));
		}
	}

}
