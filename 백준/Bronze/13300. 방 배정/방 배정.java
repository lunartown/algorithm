import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] stdnt = new int[2][6];
		
		for(int i = 0; i < N ; i++) {
			st = new StringTokenizer(in.readLine());
			stdnt[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())-1]++;
		}
		
		int cnt = 0;
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 6; j++) {
				cnt += Math.ceil((double) stdnt[i][j] / K);
			}
		}
		
		System.out.println(cnt);
	}
}
