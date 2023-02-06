import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

 class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		BigInteger K = new BigInteger("2");
		K = K.pow(N).subtract(new BigInteger("1"));
		
		System.out.println(K);
		if(N <= 20) {
			hanoi(N, 1, 3);
			System.out.println(sb);
		}
	}
	
	static StringBuilder sb = new StringBuilder();
	
	public static void hanoi(int n, int from, int to) {
		if(n==1) sb.append(from + " " + to + "\n");
		else {
			hanoi(n - 1, from, (6 - from - to));
			sb.append(from + " " + to + "\n");
			hanoi(n - 1, (6 - from - to), to);
		}
	}
}