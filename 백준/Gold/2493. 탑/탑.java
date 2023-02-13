import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		ArrayDeque<int[]> tower = new ArrayDeque<>();
		int N = Integer.parseInt(in.readLine());
		int hgt = 0;
		
		st = new StringTokenizer(in.readLine());
		
		tower.push(new int[] {0, 0});
		
		for(int i = 1; i <= N; i++) {
			hgt = Integer.parseInt(st.nextToken());
			while(tower.peek()[0] != 0 && tower.peek()[1] < hgt) {
				tower.pop();
			}
			sb.append(tower.peek()[0]).append(" ");
			tower.push(new int[] {i, hgt});
		}
		
		System.out.println(sb);

	}

}
