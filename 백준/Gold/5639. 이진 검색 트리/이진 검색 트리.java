import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String str = in.readLine();
			if(str == null || str.equals("")) break;
			else tree.add(Integer.parseInt(str));
		}
		
		search(0, tree.size()-1);	
	}
	static ArrayList<Integer> tree = new ArrayList<>();
	
	public static void search(int in, int out) {
		if(in > out) return;
		
		int mid = in+1;
		while(mid<=out && tree.get(mid)<tree.get(in)) {
			mid++;
		}
		
		search(in + 1, mid - 1);
		search(mid, out);
		System.out.println(tree.get(in));
	}
}

 
