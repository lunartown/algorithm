import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int[] num = new int[400];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			num[Integer.parseInt(st.nextToken()) + 200]++;
		}
		
		int v = Integer.parseInt(in.readLine());
		
		System.out.println(num[v+200]);
		

	}

}