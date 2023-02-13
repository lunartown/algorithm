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
		
		int[] dolls = new int[N+1];
		
		st = new StringTokenizer(in.readLine());
		for(int i = 1; i <= N; i++)
			dolls[i] = Integer.parseInt(st.nextToken());
		
		int lions = 0;
		int min = Integer.MAX_VALUE;
		boolean set = false;
		
		int i = 1;
		
		for(int j = 1; j <= N; j++) {
			if(dolls[j] == 1) lions++;
			if(lions >= K) {
				set = true;
				for(; i < j; i++) {
					min = Math.min(min, j - i + 1);
					if(dolls[i] == 1) {
						lions--;
						i++;
						break;
					}
				}
			}
		}
		if(set)	System.out.println(min);
		else System.out.println(-1);
			
	}
}