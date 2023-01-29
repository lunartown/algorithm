import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String str = in.readLine();
		LinkedList<Character> write = new LinkedList<Character>();
		for(int i = 0; i < str.length(); i++) {
			write.add(str.charAt(i));
		}
		
		ListIterator<Character> iterator = write.listIterator(str.length());
		
		int N = Integer.parseInt(in.readLine());
		char cmd;

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			cmd = st.nextToken().charAt(0);
			switch(cmd) {
			case 'L' : if(iterator.hasPrevious()) iterator.previous(); break;
			case 'D' : if(iterator.hasNext()) iterator.next(); break;
			case 'B' : if(iterator.hasPrevious()) { iterator.previous(); iterator.remove(); } break;
			case 'P' : iterator.add(st.nextToken().charAt(0));
			}
		}
		
		ListIterator<Character> printer = write.listIterator();
		
		while(printer.hasNext()) {
			sb.append(printer.next());
		}
		
		System.out.println(sb);
	}
}
