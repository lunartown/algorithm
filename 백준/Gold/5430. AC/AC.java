import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		LinkedList<Integer> deque;
		String cmd, arr;
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int len, size, rev;
		boolean error;
		
		int T = Integer.parseInt(in.readLine());
		
		for(int i = 0; i < T; i++) {
			deque = new LinkedList<>();
			rev = 1;
			error = false;
			len = 0;
			size = 0;
			
			cmd = in.readLine();
			len = cmd.length();
			
			size = Integer.parseInt(in.readLine());
			arr = in.readLine();
			arr = arr.substring(1, arr.length() - 1);
					
			st = new StringTokenizer(arr, ",");
			
			char[] command = new char[len];
			cmd.getChars(0, len, command, 0);
			
			for(int j = 0; j < size; j++) {
				deque.add(Integer.parseInt(st.nextToken()));
			}
			
			label:for(int j = 0; j < len; j++) {
				switch(command[j]) {
				case 'R' : rev *= -1; break;
				case 'D' : try {
				if(rev == 1) { deque.removeFirst(); }
				else if(rev == -1) { deque.removeLast(); }
				} catch(Exception e) {
					error = true;
					break label;
				}
				}
			}
			
			if(error == true) sb.append("error\n");
			else if (rev == 1){
				sb.append("[");
				if (deque.isEmpty()) sb.append("]\n");
				else {
					Iterator<Integer> itr = deque.iterator();
					while(itr.hasNext()) {
						sb.append(itr.next() + ",");
					}
					sb.deleteCharAt(sb.length() - 1);
					sb.append("]\n");
					}
			}else if(rev == -1) {
				sb.append("[");
				if (deque.isEmpty()) sb.append("]\n");
				else {
					ListIterator<Integer> itr = deque.listIterator(deque.size());
					while(itr.hasPrevious())
						sb.append(itr.previous() + ",");
					sb.deleteCharAt(sb.length() - 1);
					sb.append("]\n");
					}
			}
		}
		
		System.out.println(sb);
	}
	
}