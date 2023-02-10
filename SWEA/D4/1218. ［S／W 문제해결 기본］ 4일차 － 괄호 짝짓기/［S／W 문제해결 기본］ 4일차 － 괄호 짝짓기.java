import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = null;
		Stack<Character> bracket = null;
		
		int valid;
		for(int i = 1; i <= 10; i++) {
			valid = 1;
			int N = Integer.parseInt(in.readLine());
			bracket = new Stack<>();
			str = in.readLine();
			for(int j = 0; j < N; j++) {
				if(str.charAt(j) == '>') {
					if(bracket.pop() != '<') {
						valid = 0;
						break;
					}
				}else if(str.charAt(j) == ')') {
					if(bracket.pop() != '(') {
						valid = 0;
						break;
					}
				}else if(str.charAt(j) == ']') {
					if(bracket.pop() != '[') {
						valid = 0;
						break;
					}
				}else if(str.charAt(j) == '}') {
					if(bracket.pop() != '{') {
						valid = 0;
						break;
					}
				}else bracket.push(str.charAt(j));
			}
			
			sb.append('#').append(i).append(' ').append(valid).append('\n');
		}
		
		System.out.println(sb);
	}
}
