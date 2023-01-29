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
		
		int N = Integer.parseInt(in.readLine());
		String str;
		LinkedList<Character> pw;
		
		for (int i = 0; i < N; i++) {
			str = in.readLine();
			pw = new LinkedList<Character>();
			ListIterator<Character> itr = pw.listIterator();
			
			for(int j = 0; j < str.length(); j++) {
				switch(str.charAt(j)) {
				case '<' : if(itr.hasPrevious()) itr.previous(); break;
				case '>' : if(itr.hasNext()) itr.next(); break;
				case '-' : if(itr.hasPrevious()) {itr.previous(); itr.remove();} break;
				default : itr.add(str.charAt(j));
				}
			}
			
			itr = pw.listIterator();
			
			while(itr.hasNext()) {
				sb.append(itr.next());
			}
			
			sb.append('\n');
		}	
		System.out.println(sb);
	}
}
