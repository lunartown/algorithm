import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] dwarf = new int[9];
		int spy = -100;
		
		for(int i = 0; i < 9; i++) {
			dwarf[i] = Integer.parseInt(in.readLine());
			spy += dwarf[i];
		}
		
		int flag = permutation(0, 0, 0, spy, dwarf);
		
		for(int i = 0; i < 9; i++) {
			if((flag & (1 << i)) != 0) continue;
			sb.append(dwarf[i]).append('\n');
		}
		System.out.println(sb);
		
	}
	
	
	
	static int permutation(int cnt, int flag, int sum, int spy, int[] dwarf) {
		if(cnt >= 2) {
			if(sum == spy) {
				return flag;
			}
			return 0;
		}
		for(int i = 0; i < 9; i++) {
			if((flag & (1 << i)) != 0) continue;
			int ans = permutation(cnt+1, flag | (1 << i), sum + dwarf[i], spy, dwarf);
			if(ans != 0) return ans;
		}
		return 0;
	}
}