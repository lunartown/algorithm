import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
	
class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());
		int N, M;
		
		for(int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			sb.append(fact(M).divide(fact(N)).divide(fact(M-N))).append('\n');
		}
		
		System.out.println(sb);
	}
	
	private static BigInteger fact (int n) {
		BigInteger multi = new BigInteger("1");
		if(n == 0) return multi;
		for(int i = 1; i <= n; i++) {
			multi = multi.multiply(new BigInteger(String.valueOf(i)));
		}
		return multi;
	}
}