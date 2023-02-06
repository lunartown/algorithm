import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		Stack<Integer[]> tower = new Stack<>();
		int N = Integer.parseInt(in.readLine());
		int hgt = 0;
		
		st = new StringTokenizer(in.readLine());
		
		tower.push(new Integer[] {0, 0});
		
		for(int i = 1; i <= N; i++) {
			hgt = Integer.parseInt(st.nextToken());
			while(tower.peek()[0] != 0 && tower.peek()[1] < hgt) {
				tower.pop();
			}
			sb.append(tower.peek()[0] + " ");
			tower.push(new Integer[] {i, hgt});
		}
		
		System.out.println(sb);

	}

}
