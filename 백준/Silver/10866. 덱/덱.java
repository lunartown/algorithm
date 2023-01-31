import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.print.DocFlavor.STRING;


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());
		String cmd = null;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			cmd = st.nextToken();
			
			switch(cmd) {
			case "push_front" : push_front(Integer.parseInt(st.nextToken())); break;
			case "push_back" : push_back(Integer.parseInt(st.nextToken())); break;
			case "pop_front" : sb.append(pop_front() + "\n"); break;
			case "pop_back" : sb.append(pop_back() + "\n"); break;
			case "size" : sb.append(size() + "\n"); break;
			case "empty" : sb.append(empty() + "\n"); break;
			case "front" : sb.append(front() + "\n"); break;
			case "back" : sb.append(back() + "\n"); break;
			}	
		}
		
		System.out.println(sb);
	}
	
	static final int MAX = 10001;
	static int[] deque = new int[2*MAX + 1];
	static int head = MAX;
	static int tail = MAX;
	
	static void	push_front(int X) {
		deque[head - 1] = X;
		head--;
	}
	
	static void push_back(int X) {
		deque[tail] = X;
		tail++;
	}
	
	static int pop_front() {
		if(empty() == 1) return -1;
		else {
			head++;
			return deque[head - 1];
		}
	}
	
	static int pop_back() {
		if(empty() == 1) return -1;
		else {
			tail--;
			return deque[tail];
		}
	}
	
	static int size() {
		return (tail - head);
	}
	
	static int empty() {
		if(size() == 0) return 1;
		else return 0;
	}
	
	static int front() {
		if(empty() == 1) return -1;
		else return deque[head];
	}
	
	static int back() {
		if(empty() == 1) return -1;
		else return deque[tail - 1];		
	}
}
