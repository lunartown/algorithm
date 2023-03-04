import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Shark {
	int x, y, speed, dir, sign, size;
	
	Shark(int x, int y, int speed, int d, int size) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		
		if(d <= 2) dir = 0;
		else dir = 1;
		
		if(d / 2 == 1) sign = 1;
		else sign = -1;
		
		this.size = size;
	}	
}

public class Main {
	static int R, C, M, king, caught;
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};
	static Shark[][] map;
	
	static void catchShark() {
		for(int i = 0; i < R; i++) {
			if(map[i][king] != null) {
				caught += map[i][king].size;
				map[i][king] = null;
				return;
			}
		}
	}
	
	static void moveShark() {
		Queue<Shark> queue = new ArrayDeque<>();
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] != null) {
					Shark shark = map[i][j];
					map[i][j] = null;
					int x = i, y = j;
					int nx, ny;
					for(int k = 0; k < shark.speed; k++) {
						nx = x + dx[shark.dir] * shark.sign;
						ny = y + dy[shark.dir] * shark.sign;
						if(nx < 0 || nx >= R || ny < 0 || ny >= C) {
							shark.sign *= -1;
							nx = x + dx[shark.dir] * shark.sign;
							ny = y + dy[shark.dir] * shark.sign;
						}
						x = nx;
						y = ny;
					}
					shark.x = x;
					shark.y = y;
					queue.offer(shark);
				}
			}
		}
		
		map = new Shark[R][C];
		
		while(!queue.isEmpty()) {
			Shark shark = queue.poll();
			int x = shark.x;
			int y = shark.y;
			if(map[x][y] == null) map[x][y] = shark;
			else {
				if(map[x][y].size < shark.size) {
					map[x][y] = shark;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Shark[R][C];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			map[r - 1][c - 1] = new Shark(r -1, c - 1, s, d, z);
		}
		
		for(;king < C; king++) {
			catchShark();
			moveShark();
		}
		
		System.out.println(caught);
	}
}