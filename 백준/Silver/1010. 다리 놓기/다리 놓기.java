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
			
			sb.append(permu(M,N).divide(fact(N))).append('\n');
			
		}
		
		System.out.println(sb);
	}
	
	private static BigInteger fact (int n) {
		BigInteger multi = new BigInteger("1");
		for(int i = 2; i <= n; i++) {
			multi = multi.multiply(new BigInteger(String.valueOf(i)));
		}
		return multi;
	}
	
	private static BigInteger permu (int m, int n) {
		BigInteger multi = new BigInteger("1");
		for(int i = 0; i < n; i++) {
			multi = multi.multiply(new BigInteger(String.valueOf(m - i)));
		}
		return multi;
	}
}