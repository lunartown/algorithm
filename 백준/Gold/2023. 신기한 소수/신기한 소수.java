import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		int[] prime = {2, 3, 5, 7};
		for(int i = 0; i < 4; i++)
			oddPrime(prime[i], N, 1);
		
		System.out.println(sb);
	}
	static StringBuilder sb = new StringBuilder();
	
	static boolean isPrime (int num) {
		for(int i = 2; i <= Math.sqrt(num); i++) {
			if(num % i == 0) return false;
		}
		return true;
	}
	
	static void oddPrime (int num, int N, int cnt) {
		if(cnt == N) {
			sb.append(num).append('\n');
			return;
		}
		
		for(int i = 1; i < 10; i+=2) {
			if(isPrime(10*num + i)) {
				oddPrime(10*num + i, N, cnt + 1);
			}
		}
	}
}
