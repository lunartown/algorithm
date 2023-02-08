import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
	
class Main {
		
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int[] alpha = new int[26];
		
		int N = Integer.parseInt(in.readLine());
		
//		입력받은 알파벳 자리에 10 ^ 자릿수의 값 저장
		for(int i = 0; i < N; i++) {
			String str = in.readLine();
			for(int j = 0; j < str.length(); j++) {
				alpha[str.charAt(str.length() - j - 1) - 'A'] += (int) Math.pow(10, j);
			}
		}
		
//		9 ~ 1의 배열을 만들어서 큰 순서대로 입력
		int[][] big = new int[9][2];
		boolean[] vis = new boolean[26];
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 26; j++) {
				if(vis[j] || alpha[j] < big[i][1]) continue;
				big[i][0] = j;
				big[i][1] = alpha[j];
			}
			vis[big[i][0]] = true;
		}
		
//		배열에 저장된 값 * 9 ~ 1 을 더한다.
		int sum = 0;
		for(int i = 0; i < 9; i++) {
			sum += big[i][1] * (9 - i);
		}
		
		System.out.println(sum);
	}
}
	
	 
