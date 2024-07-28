import java.io.*;

public class Main {
	static int N, count;
	static int[] mobius = new int[40558];
	
	static void generateMobius() {
		mobius[1] = 1;
		for(int i = 1; i < 40558; i++) {
			for(int j = 2 * i; j < 40558 ; j += i) {
				mobius[j] -= mobius[i];
			}
		}
	}
	
	static long countSquareNoNo(long n) {
		long count = 0;
		for(int i = 1; i * i <= n; i++) {
			count += mobius[i] * (n / (i * i));
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		
		generateMobius();
		
		long start = N;
		long end = 1644934081;
		long ans = end;
		
		while(start <= end) {
			long mid = (start + end) / 2;
			if(countSquareNoNo(mid) < N) {
				start = mid + 1;
			}else{
				end = mid - 1;
				ans = mid;
			}
		}
		
		System.out.println(ans);
	}

}