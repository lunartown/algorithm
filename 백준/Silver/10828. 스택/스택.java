import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class MyStack {
	int[] num = new int[10000];
	int size = 0;
	
	void push(int X) {
		num[size] = X;
		size++;
	}
	
	int pop() {
		if(size != 0) {
			size--;
			return num[size];
		} else return -1;
	}
	
	int size() {
		return this.size;
	}
	
	int empty() {
		if(size == 0)
			return 1;
		else return 0;
	}
	
	int top() {
		if(size != 0)
			return num[size - 1];
		else return -1;
	}
}


public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		MyStack s = new MyStack();
		
		int N = Integer.parseInt(in.readLine());
		String cmd;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			cmd = st.nextToken();
			
			switch(cmd) {
			case "push" : s.push(Integer.parseInt(st.nextToken())); break;
			case "pop" : sb.append(s.pop() + "\n"); break;
			case "size" : sb.append(s.size() + "\n"); break;
			case "empty" : sb.append(s.empty() + "\n"); break;
			case "top" : sb.append(s.top() + "\n");
			}
		}
		
		System.out.println(sb);
	}
}
