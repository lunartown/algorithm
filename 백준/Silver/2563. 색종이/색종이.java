import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int[][] whole = new int[101][101];
		int area = 0;
		
		int N = Integer.parseInt(in.readLine());
		
		int[][] paper = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			paper[i][0] = Integer.parseInt(st.nextToken());
			paper[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 1; j<= 10; j++) {
				for(int k = 1; k <= 10; k++)
					whole[paper[i][0] + j][paper[i][1] + k] = 1;
			}
		}
		
		for(int i = 1; i <= 100; i++) {
			for(int j = 1; j <= 100; j++) {
				area += whole[i][j];
			}
		}
		
		System.out.println(area);
	}
}