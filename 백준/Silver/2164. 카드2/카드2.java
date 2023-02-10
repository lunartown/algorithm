import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		ArrayDeque<Integer> card = new ArrayDeque<>();
		
		for (int i = 1; i < N + 1; i++) {
			card.offer(i);
		}
		
		for (int i = 0; i < N-1; i++) {
			card.poll();
			card.offer(card.poll());
		}
		
		System.out.println(card.peek());
	}
}
