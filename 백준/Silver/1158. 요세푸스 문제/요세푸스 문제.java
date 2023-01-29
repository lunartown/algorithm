import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		LinkedList<Integer> circle = new LinkedList<Integer>();
		for(int i = 1; i <= N; i++) {
			circle.add(i);
		}
		
		ListIterator<Integer> itr = circle.listIterator();
		sb.append("<");
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < K - 1 ; j++) {
				if(itr.hasNext()) itr.next();
				else {
					itr = circle.listIterator();
					itr.next();
				}
			}
			if(itr.hasNext()) sb.append(itr.next());
			else { 
				itr = circle.listIterator();
				sb.append(itr.next());
				}
			itr.remove();
			if(i != N - 1) sb.append(", ");
		}
		
		sb.append(">");
		System.out.println(sb);
	}
}
