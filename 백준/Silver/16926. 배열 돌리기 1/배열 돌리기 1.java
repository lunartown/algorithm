import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 기본 논리 :
 * 2차원 배열을 돌리기 싫다
 * 테두리를 한바퀴 도는 1차원 배열을 만들 겁니다
 * 예시 : 1 2 3 4
 *        5 6 7 8
 *        9 10 11 12
 *        13 14 15 16
 *        -> {1, 2, 3, 4, 8, 12, 16, 15, ..., 5}
 *        -> {6, 7, 11, 10} 이 경우 2개
 * 
 * 처음 받을 때부터 저 순서로 받고 싶다! 어떻게 할까
 * 이제 코드를 읽어주세요
 */

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		arr = new int[Math.min(N, M) / 2][];

//		0) 기본적으로 한 줄씩 읽을 겁니다.	
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			input(N, M, i, 0);
		}
		
		
		for(int i = 1; i <= N; i++) {
			output(N, M, i, R, 0);
			sb.append('\n');
		}
		
		System.out.println(sb);
	}

	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder();
	private static int[][] arr;
	
	private static void input(int n, int m, int l, int order) {
//		 1) 1열, 마지막열 : 먼저 배열 생성하고
//							그냥 인덱스 적당히 줘서 토크나이저로 받습니다
		if(l == 1) {
			arr[order] = new int[2*(n+m) - 4];
			for(int i = 0; i < m; i++) {
				arr[order][i] = Integer.parseInt(st.nextToken());
			}
		}else if(l == n) {
			for(int i = 0; i < m; i++)
				arr[order][2 * m + n - 3 - i] = Integer.parseInt(st.nextToken());
			
//			2) 그렇지 않은 경우 :
//			예시에서 2번째 줄 : 1) 5가 12번째 자리 = 2*(m+n)-4.. 인덱스 계산으로 적당히 받습니다
//								2) 두번째 배열로 들어갑니다.. 만약 더 이상 작은 배열로 들어갈 수
//								없다면 패스
//								3) 마찬가지로 인덱스 적당히 줘서 받습니다. 이 경우 6번째 자리
		}else {
			arr[order][2*(n+m) - 3 - l] = Integer.parseInt(st.nextToken());
			if(n>2 && m>2) input(n - 2, m - 2, l - 1, order + 1);
			arr[order][m - 2 + l] = Integer.parseInt(st.nextToken());
		}
	}
	
	private static void output(int n, int m, int l, int r, int order) {
		if(l == 1) {
			for(int i = 0; i < m; i++) {
				sb.append(arr[order][(i + r) % ((n + m) * 2 - 4)]).append(" ");
			}
		}else if(l == n) {
			for(int i = 0; i < m; i++)
				sb.append(arr[order][(2 * m + n - 3 - i + r) % ((n + m) * 2 - 4)]).append(" ");
		}else {
			sb.append(arr[order][(2*(n+m) - 3 - l + r) % ((n + m) * 2 - 4)]).append(" ");
			if (n>2 && m>2) output(n - 2, m - 2, l - 1, r, order + 1);
			sb.append(arr[order][(m - 2 + l + r) % ((n + m) * 2 - 4)]).append(" ");
		}
	}
}