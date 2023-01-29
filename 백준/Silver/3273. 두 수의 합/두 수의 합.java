import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());	
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] arr = new int[2000001];
		int[] num = new int[n];
		
		for(int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			arr[num[i]]++;
		}
		
		int cnt = 0;
		int x = Integer.parseInt(in.readLine());
		
		for(int i = 0; i < n; i++) {
			if(x > num[i] && arr[x - num[i]] == 1) cnt++;
		}
		
		System.out.println(cnt/2);
	}
	
}
