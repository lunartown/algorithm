import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		System.out.println(devide(N, r, c));
	}
	
	static int devide(int N, int r, int c) {
		int size = (int)Math.pow(2, N - 1);
		if(N == 1) return (r == 0 ? c == 0 ? 0:1 : c == 0? 2:3);
		if(r < size) {
			if(c < size) {
				return devide(N-1, r, c);
			}else {
				return devide(N-1, r, c - size) + size*size;
			}
		} else if(c < Math.pow(2, N - 1)) {
			return devide(N-1, r - size, c) + size*size*2;
		} else {
			return devide(N-1, r - size, c - size) + size*size*3;
		}
	}
}