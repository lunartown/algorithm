import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int[] DNA = new int[5];
		int[] atLeast = new int[5];
		int cnt = 0;
		
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		String str = in.readLine();
		
		st = new StringTokenizer(in.readLine());
		for (int i = 1; i < 5; i++) {
			atLeast[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < P; i++) {
			switch(str.charAt(i)) {
			case 'A': DNA[1]++; break;
			case 'C': DNA[2]++; break;
			case 'G': DNA[3]++; break;
			case 'T': DNA[4]++; break;
			}
			
		}
		if(DNA[1] < atLeast[1] || DNA[2] < atLeast[2] || DNA[3] < atLeast[3] || DNA[4] < atLeast[4]) {}
		else cnt++;
		
		for(int i = 1; i <= S - P; i++) {
			switch(str.charAt(i-1)) {
			case 'A': DNA[1]--; break;
			case 'C': DNA[2]--; break;
			case 'G': DNA[3]--; break;
			case 'T': DNA[4]--; break;
			}
			switch(str.charAt(i+P-1)) {
			case 'A': DNA[1]++; break;
			case 'C': DNA[2]++; break;
			case 'G': DNA[3]++; break;
			case 'T': DNA[4]++; break;
			}
			if(DNA[1] < atLeast[1] || DNA[2] < atLeast[2] || DNA[3] < atLeast[3] || DNA[4] < atLeast[4])
				continue;
			cnt++;
		}
		
		System.out.println(cnt);
		
	}
}
