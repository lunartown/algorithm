import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(in.readLine());
		int[][] schedule = new int[N+1][2];
		
//		스케쥴 입력받기
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			schedule[i][0] = Integer.parseInt(st.nextToken());
			schedule[i][1] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(money(schedule, 1));
		
	}
		
//	재귀함수
	static int money(int[][] schedule, int i) {
		int max = 0, money = 0;
		
//		i일부터 j-i일만큼 쉬고 일을 시작할 때
		for(int j = i; j < schedule.length; j++) {
			
//			날짜를 넘겨버리면 일 못함
			if(schedule[j][0] + j > schedule.length) money = 0;
			else money = schedule[j][1];
			
//			일하고 난 후 재귀함수 호출
			money += money(schedule, j + schedule[j][0]);
			
//			최선인지 확인
			max = Math.max(max, money);
		}
		return max;
	}
}