import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		int i = 0;
		while(mooLength(i) < N) i++;

		System.out.println(moo(N, i));
	}
	
	static int mooLength(int n) {
		if(n == 0) return 3;
		return mooLength(n - 1) * 2 + (3 + n);
	}
	
	static char moo(int N, int i) {
		if(i == 0) {
			if (N == 1) return 'm';
			else return 'o';
		}
		if(N <= mooLength(i-1)) return moo(N, i-1);
		else if(N > mooLength(i-1) + (i+3)) return moo(N - mooLength(i-1) - (i+3), i-1);
		else if(N == mooLength(i-1) + 1) return 'm';
		else return 'o';
	}
}