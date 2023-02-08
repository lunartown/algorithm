import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
	
class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		long[] alpha = new long[26];
		
		String S = in.readLine();
		int N = Integer.parseInt(in.readLine());
		
		for(int i = 1; i <= N; i++)
			alpha[in.readLine().charAt(0) - 'A']++;
		
		long cons = 1;
		if(S.charAt(0) == S.charAt(1) || S.charAt(0) == S.charAt(2) || S.charAt(1) == S.charAt(2))
			if(S.charAt(0) == S.charAt(1) && S.charAt(0) == S.charAt(2))
				cons = 6;
			else cons = 2;
		
		System.out.println((alpha[S.charAt(0) - 'A']--) * (alpha[S.charAt(1) - 'A']--) * alpha[S.charAt(2) - 'A'] / cons);
	}
}
