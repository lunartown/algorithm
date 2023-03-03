import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class BabyShark implements Comparable<BabyShark>{
	int x, y, time, second;
	
	BabyShark(int x, int y, int time, int second) {
		this.x = x;
		this.y = y;
		this.time = time;
		this.second = second;
	}

	@Override
	public int compareTo(BabyShark o) {
		if(this.time == o.time) {
			if(this.second == o.second) {
				if(this.x == o.x) return this.y - o.y;
				else return this.x - o.x;
			}else return this.second - o.second;
		}else return this.time - o.time;
	}
	
}

public class Main {
	static int N, time, size, eaten, absTime, fish[];
	static boolean[][] vis;
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	
	static void bfs(BabyShark babyShark, int[][] space) {
		PriorityQueue<BabyShark> pq = new PriorityQueue<>();
		vis[babyShark.x][babyShark.y] = true;
		pq.offer(babyShark);
		int queueSize, last;
		label1:while(!pq.isEmpty()) {
//			System.out.println("\n1차 작업 \n");
			queueSize = pq.size();
			for(int i = 0; i < queueSize; i++) {
				BabyShark cur = pq.poll();
				
//				last = 0;
//				for(int j = 1; j < size && j < 7; j++) {
//					last += fish[j];
//				}
//				if(last == 0) break label1; 
				
				for(int j = 0; j < 4; j++) {
					int nx = cur.x + dx[j];
					int ny = cur.y + dy[j];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= N || vis[nx][ny] || space[nx][ny] > size) continue;
					vis[nx][ny] = true;
//					System.out.println(nx + " " + ny + " " + time);
					pq.offer(new BabyShark(nx, ny, time+1, 0));
				}
			}
			
//			System.out.println("\n2차 작업 \n");
			
			queueSize = pq.size();
			for(int i = 0; i < queueSize; i++){
				BabyShark n = pq.poll();
				int nx = n.x;
				int ny = n.y;
//				System.out.println("\n폴");
//				System.out.println(nx + " " + ny + " " + n.time);
				if(space[nx][ny] < size && space[nx][ny] > 0) {
					if(++eaten >= size) {
						size++;
						eaten = 0;
					}
					fish[space[nx][ny]]--;
					space[nx][ny] = 0;
					vis = new boolean[N][N];
					pq.clear();
					vis[nx][ny] = true;
					absTime = time+1;
					pq.offer(new BabyShark(nx, ny, time+1, 0));
					
//					System.out.println("size = " + size + " eaten =  " + eaten + " time = " + time+1);
//					for(int l = 0; l < N; l++) {
//						for(int k = 0; k < N; k++) {
//							if(nx == l && ny == k) System.out.print("A ");
//							else System.out.print(space[l][k] + " ");
//						}
//						System.out.println();
//					}
//					System.out.println();
					
					break;
				} else {
//					System.out.println("\n다시 푸시");
//					System.out.println(nx + " " + ny + " " + (time + 1));
					pq.offer(new BabyShark(nx, ny, time+1, 1));
				}
			}
			
			time++;
		}
		System.out.println(absTime);
	}
	
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
//		System.setOut(new PrintStream("res/output.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(in.readLine());
		int[][] space = new int[N][N];
		vis = new boolean[N][N];
		fish = new int[7];
		BabyShark babyShark = null;
		size = 2;
		
		for(int i = 0; i < N; i++) {
			st= new StringTokenizer(in.readLine());
			for(int j = 0; j < N; j++) {
				space[i][j] = Integer.parseInt(st.nextToken());
				if(space[i][j] == 9) {
					space[i][j] = 0;
					babyShark = new BabyShark(i, j, 0, 0);
				}else if(space[i][j] > 0) {
					fish[space[i][j]]++;
				}
			}
		}
		
//		for(int l = 0; l < N; l++) {
//			for(int k = 0; k < N; k++) {
//				if(babyShark.x == l && babyShark.y == k) System.out.print("A ");
//				else System.out.print(space[l][k] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		bfs(babyShark, space);
	}
}