import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int R, C, num, pipe[];
	static char[][] town;
	static boolean[][] vis, copy;
	static boolean complete;
	
	static void pipeline(int x, int y) {
		if(complete) return;
		
		if(y >= C - 1) {
			pipe[y] = x;
			complete = true;
			return;
		}
		
		pipe[y] = x;
		town[x][y] = '-';
		
		if(x >= 1 && town[x - 1][y + 1] == '.') pipeline(x - 1, y + 1);
		if(town[x][y + 1] == '.') pipeline(x, y + 1);
		if(x < R - 1 && town[x + 1][y + 1] == '.') pipeline(x + 1, y + 1);
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		town = new char[R][];
		pipe = new int[C];
		
		for(int i = 0; i < R; i++) {
			town[i] = in.readLine().toCharArray();
		}

		copy = new boolean[R][];
		
		for(int i = 0; i < R; i++) {
			complete = false;
			pipeline(i, 0);
			if(complete) {
				num++;
				for(int j = 0; j < C; j++) {
					town[pipe[j]][j] = 'P';
				}
			}
		}
		
		System.out.println(num);
	}
}