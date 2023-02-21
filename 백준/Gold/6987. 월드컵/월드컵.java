import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Integer[][] result = new Integer[6][3];
		combination = new int[15][];
		vis = new boolean[15];
		for(int i = 0, cnt = 0; i < 6; i++) {
			for(int j = i + 1; j < 6; j++) {
				combination[cnt++] = new int[] {i, j};
			}
		}
		
		label:for(int tc = 0; tc < 4; tc++) {
			ans = 0;
			int sum = 0;
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int j = 0; j < 6; j++) {
				result[j][0] = Integer.parseInt(st.nextToken());
				result[j][1] = Integer.parseInt(st.nextToken());
				result[j][2] = Integer.parseInt(st.nextToken());
				sum += result[j][0] + result[j][1] + result[j][2];
				if(result[j][0] > 5 || result[j][1] > 5 || result[j][2] > 5) {
					sb.append(0).append(' ');
					continue label;
				}
			}
			if(sum != 30) {
				sb.append(0).append(' ');
				continue;
			}
			Arrays.sort(result, (Integer[]a, Integer[]b) -> a[0] == b[0]? a[1] - b[1] : a[0] - b[0]);
			isAble(result, 0);
			sb.append(ans).append(' ');
		}
		System.out.println(sb);
	}
	
	static int[][] combination;
	static boolean[] vis;
	static int ans;
	
	static void isAble(Integer[][] result, int k) {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 3; j++) {
				if(result[i][j] < 0) return;
			}
		}
		
		if(k == 15) {
			ans = 1;
			return;
		}
		
		result[combination[k][0]][0]--;
		result[combination[k][1]][2]--;
		isAble(result, k+1);
		result[combination[k][0]][0]++;
		result[combination[k][1]][2]++;
		
		
		result[combination[k][0]][1]--;
		result[combination[k][1]][1]--;
		isAble(result, k+1);
		result[combination[k][0]][1]++;
		result[combination[k][1]][1]++;
		
		
		result[combination[k][0]][2]--;
		result[combination[k][1]][0]--;
		isAble(result, k+1);
		result[combination[k][0]][2]++;
		result[combination[k][1]][0]++;
			
		
		
	}
}