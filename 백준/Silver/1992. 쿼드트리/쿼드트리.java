import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str;
		
		int N = Integer.parseInt(in.readLine());
		int[][] image = new int[N][N];
		for(int i = 0; i < N; i++) {
			str = in.readLine();
			for(int j = 0; j < N; j++) {
				image[i][j] = str.charAt(j) - '0';
			}
		}
		
		devide(image, 0, 0, N, N);
		
		System.out.println(sb);
		
	}
	
	static StringBuilder sb = new StringBuilder();
	
	static void devide(int[][] image, int startRow, int startCol, int endRow, int endCol) {
		int num = image[startRow][startCol];
		boolean same = true;
		for(int i = startRow; i < endRow; i++) {
			for(int j = startCol; j < endCol; j++) {
				if(image[i][j] != num) same = false;
			}
		}
		
		if(same) {
			sb.append(num);
			return;
		}
		
		sb.append('(');
		devide(image, startRow, startCol, startRow + (endRow - startRow) / 2, startCol + (endCol - startCol) / 2);
		devide(image, startRow, startCol + (endCol - startCol) / 2, startRow + (endRow - startRow) / 2, endCol);
		devide(image, startRow + (endRow - startRow) / 2, startCol, endRow, startCol + (endCol - startCol) / 2);
		devide(image, startRow + (endRow - startRow) / 2, startCol + (endCol - startCol) / 2, endRow, endCol);
		sb.append(')');
	}
}