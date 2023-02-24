import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
	
public class Main {
	static int N, strong, weak;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static void search(int x, int y, char alpha, char[][] image, boolean[][] vis) {
		int nx, ny;
		for(int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if(nx < 0 || nx >= N || ny < 0 || ny >= N || vis[nx][ny]) continue;
			if(image[nx][ny] != alpha) continue;
			vis[nx][ny] = true;
			search(nx, ny, alpha, image, vis);
		}
	}
	
	static void searchRG(int x, int y, char alpha, char[][] image, boolean[][] vis) {
		int nx, ny;
		for(int i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if(nx < 0 || nx >= N || ny < 0 || ny >= N || vis[nx][ny]) continue;
			if(image[nx][ny] == 'B') continue;
			vis[nx][ny] = true;
			searchRG(nx, ny, alpha, image, vis);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		char[][] image = new char[N][];
		
		for(int i = 0; i < N; i++) {
			image[i] = in.readLine().toCharArray();
		}
		
//		not colorweak
		boolean[][] vis = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!vis[i][j]) {
					strong++;
					vis[i][j] = true;
					search(i, j, image[i][j], image, vis);
				}
			}
		}
		
//		colorweak
		vis = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!vis[i][j]) {
					weak++;
					vis[i][j] = true;
					if(image[i][j] == 'B') search(i, j, image[i][j], image, vis);
					else searchRG(i, j, image[i][j], image, vis);
				}
			}
		}
		
		System.out.println(strong + " " + weak);
	}
}