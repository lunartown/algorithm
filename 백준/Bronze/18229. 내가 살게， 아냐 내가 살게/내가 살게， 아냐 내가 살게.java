import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
	
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
	
		int[][] hand = new int[N][];
		for(int i = 0; i < N; i++) {
			hand[i] = Stream.of(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		int[] fingertip = new int[N];
		int person = 0, times = 0;
		
		label:for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				fingertip[j] += hand[j][i];
				if(fingertip[j] >= K) {
					person = j+1;
					times = i+1;
					break label;
				}
			}
		}
		
		sb.append(person).append(' ').append(times);
		System.out.println(sb);
	}
}