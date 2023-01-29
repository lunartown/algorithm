
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int[] num = new int[N];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		int v = Integer.parseInt(in.readLine());
		int cnt = 0;
		
		for(int i = 0; i < N ; i++) {
			if(num[i] == v) cnt++;
		}
		
		System.out.println(cnt);

	}

}
