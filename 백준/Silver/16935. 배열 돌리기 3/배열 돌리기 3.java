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
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
//		인덱스는 행 1~N 열 1~M
		arr = new int[N+1][M+1];
		
//		입력받기
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 1; j <= M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		for(int i = 1; i <= R; i++ ) {
			switch(st.nextToken()) {
			case "1" : op1();break;
			case "2" : op2();break;
			case "3" : op3();break;
			case "4" : op4();break; 
			case "5" : op5();break;
			case "6" : op6();
			}			
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++)
				sb.append(arr[i][j]).append(' ');
			sb.append('\n');
		}
		
		System.out.println(sb);
		
	}
	
	static int[][] arr;
	static int[][] copy;
	static int N, M, tmp;
	
	static void op1() {
		copy = new int[N+1][M+1];
		for(int i = 1; i <= N; i++)
			copy[i] = arr[N + 1 - i].clone();
		arr = copy;
	}
	
	static void op2() {
		copy = new int[N+1][M+1];
		for(int i = 1; i <= N; i++)
			for(int j = 1; j <= M; j++)
				copy[i][j] = arr[i][M + 1 - j];
		arr = copy;
	}
	
	static void op3() {
		copy = new int[M + 1][N + 1];
		for(int i = 1; i <= N; i++)
			for(int j = 1; j <= M; j++)
				copy[j][N + 1 - i] = arr[i][j];
		arr = copy;
		tmp = N; N = M; M = tmp;
	}
	
	static void op4() {
		copy = new int[M + 1][N + 1];
		for(int i = 1; i <= N; i++)
			for(int j = 1; j <= M; j++)
				copy[M + 1 - j][i] = arr[i][j];
		arr = copy;
		tmp = N; N = M; M = tmp;
	}
	
	static void op5() {
		copy = new int[N+1][M+1];
		for(int i = 1; i <= N/2; i++) {
			for(int j = 1; j <= M/2; j++) {
				copy[i][j + M/2] = arr[i][j];
				copy[i][j] = arr[i + N/2][j];
				copy[i + N/2][j] = arr[i + N/2][j + M/2];
				copy[i + N/2][j + M/2] = arr[i][j + M/2];
			}
		}
		arr = copy;
	}
	
	static void op6() {
		copy = new int[N+1][M+1];
		for(int i = 1; i <= N/2; i++) {
			for(int j = 1; j <= M/2; j++) {
				copy[i][j + M/2] = arr[i + N/2][j + M/2];
				copy[i][j] = arr[i][j + M/2];
				copy[i + N/2][j] = arr[i][j];
				copy[i + N/2][j + M/2] = arr[i + N/2][j];
			}
		}
		arr = copy;
	}
}