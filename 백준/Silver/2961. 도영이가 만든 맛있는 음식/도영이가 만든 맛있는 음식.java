import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(in.readLine());
		
		int[][] ing = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			ing[i][0] = Integer.parseInt(st.nextToken());
			ing[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int sour;
		int bitter;
		int minDiff = 1000000000;
		String subSet;
		
		for(int i = 1; i < Math.pow(2, N); i++) {
			sour = 1;
			bitter = 0;
			subSet = String.format("%"+N+"s", Integer.toBinaryString(i)).replace(' ', '0');
			
			for(int j = 0; j < N; j++) {
				if(ing[j][0] * (subSet.charAt(j) - '0') == 0) continue;
				sour *= ing[j][0] * (subSet.charAt(j) - '0');
			}
			for(int j = 0; j < N; j++) {
				bitter += ing[j][1] * (subSet.charAt(j) - '0');
			}
			
			minDiff = Math.min(minDiff, Math.abs(sour - bitter));
		}
		System.out.println(minDiff);
	}
}
