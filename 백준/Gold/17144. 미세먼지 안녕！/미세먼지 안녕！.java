import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
    
class Dust {
	int x, y, amount;
	Dust(int x, int y, int amount) {
		this.x = x;
		this.y = y;
		this.amount = amount;
	}
}

public class Main {
	static BufferedReader in;
	static StringTokenizer st;
	static int R, C, T, cleaner1, cleaner2, room[][];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static void input() throws IOException {
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if(room[i][j] == -1) {
					if(cleaner1 == 0) cleaner1 = i;
					else cleaner2 = i;
				}
			}
		}
	}
	
	static void diffusion() {
		Queue<Dust> queue = new ArrayDeque<>();
		int nx, ny;
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				int amount = room[i][j];
				if(amount > 0) {
					for(int k = 0; k < 4; k++) {
						nx = i + dx[k];
						ny = j + dy[k];
						if(nx < 0 || nx >= R || ny < 0 || ny >= C
								|| (ny == 0 && (nx == cleaner1 || nx == cleaner2))) continue;
						queue.offer(new Dust(nx, ny, amount / 5));
						room[i][j] -= amount / 5;
					}
				}
			}
		}
		
		while(!queue.isEmpty()) {
			Dust dust = queue.poll();
			room[dust.x][dust.y] += dust.amount; 
		}
	}
	
	static void clean1() {
//		초기화
		int x = cleaner1 - 1;
		int y = 0;
		int nx = cleaner1 - 2;
		int ny = 0;
		int dir = 0;
		
		while(true){
			room[x][y] = room[nx][ny];
			
			x = nx;
			y = ny;
			nx = x + dx[dir];
			ny = y + dy[dir];
			
			if(nx < 0 || nx > cleaner1 || ny >= C) {
				dir += 1;
				nx = x + dx[dir];
				ny = y + dy[dir];
			}
			
			if(nx == cleaner1 && ny == 0) {
				room[x][y] = 0;
				break;
			}
		}
	}
	
	static void clean2() {
//		초기화
		int x = cleaner2 + 1;
		int y = 0;
		int nx = cleaner2 + 2;
		int ny = 0;
		int dir = 2;
		
		while(true){
			room[x][y] = room[nx][ny];
			
			x = nx;
			y = ny;
			nx = x + dx[dir];
			ny = y + dy[dir];
			
			if(nx < cleaner2 || nx >= R || ny >= C) {
				dir = (dir + 3) % 4;
				nx = x + dx[dir];
				ny = y + dy[dir];
			}
			
			if(nx == cleaner2 && ny == 0) {
				room[x][y] = 0;
				break;
			}
		}
	}
	
	static int countDust() {
		int remain = 0;
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(room[i][j] > 0) remain += room[i][j];
			}
		}
		
		return remain;
	}
	
    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(in.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        room = new int[R][C];
        
        input();
 
        for(int t = 0; t < T; t++) {
        	diffusion();
        	clean1();
        	clean2();
        }

        System.out.println(countDust());
        
    }
}