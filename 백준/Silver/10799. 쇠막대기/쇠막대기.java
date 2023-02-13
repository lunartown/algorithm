import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArrayDeque<int[]> stack = new ArrayDeque<>();
		List<Integer> laser = new ArrayList<>();
		List<int[]> stick = new ArrayList<>();
		String str = in.readLine();
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '(') {
				stack.push(new int[] {i, str.charAt(i)});
				continue;
			}
			if(stack.peek()[0] == i - 1) {
				stack.pop();
				laser.add(i);
				continue;
			}
			stick.add(new int[] {stack.pop()[0], i});
		}
		
		int piece = stick.size();
		
		for(int i = 0; i < stick.size(); i++) {
			for(int j = 0; j < laser.size(); j++) {
				if(laser.get(j) > stick.get(i)[0] && laser.get(j) < stick.get(i)[1])
					piece++;
			}
		}
		
		System.out.println(piece);

	}

}
