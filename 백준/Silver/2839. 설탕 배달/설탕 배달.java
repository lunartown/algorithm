import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int sum, bags = -1;
		
		for(int i = 0; ; i++) {
			sum = 5 * i;
			if(sum > N) break;
			for(int j = 0; ; j++) {
				sum += 3 * j;
				if(sum == N) bags = i + j;
				else if(sum > N) break;
				sum -= 3 * j;
			}
		}
		
		System.out.println(bags);
	}
}