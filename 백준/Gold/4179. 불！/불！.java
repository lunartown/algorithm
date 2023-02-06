import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		Queue<int[]> bfsJ = new LinkedList<int[]>();
		Queue<int[]> bfsF = new LinkedList<>();
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] maze = new char[R][C];
		int[] J = null;
		int[] F = null;

//		maze를 입력받기
//		J는 bfsJ에, F는 bfsF라는 queue에 add한다.
		for (int i = 0; i < R; i++) {
			String str = in.readLine();
			for (int j = 0; j < C; j++) {
				maze[i][j] = str.charAt(j);
				if(maze[i][j] == 'J') {
					bfsJ.add(new int[] {i, j});
				}
				else if(maze[i][j] == 'F') bfsF.add(new int[] {i, j});
			}
		}
		
		int time = 0;
		boolean success = false;
		
//		먼저 F를 전염시킨 후
//		모든 가능한 J의 위치를 저장한다.
		label1:while(!bfsJ.isEmpty()) {
			time++;
			
			int size = bfsF.size(); //1시간 동안 전염되는 불
			for(int i = 0; i < size; i++) {
				F = bfsF.poll();
				for(int j = 0; j < 4; j++) {
					int nx = F[0] + dx[j];
					int ny = F[1] + dy[j];
					if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
					if (maze[nx][ny] != '.' && maze[nx][ny] != 'J') continue;
					maze[nx][ny] = 'F';
					bfsF.add(new int[] {nx, ny});
				}
			}
			
			int size2 = bfsJ.size(); //어떻게 하면 J가 전염되는 시간을 정확하게 잴 수 있을까?
			
			for(int i = 0; i < size2; i++) {
				J = bfsJ.poll();
				
				for(int j = 0; j < 4; j++) {
					int nx = J[0] + dx[j];
					int ny = J[1] + dy[j];
					if (nx < 0 || nx >= R || ny < 0 || ny >= C) { success = true; break label1; } //탈출 성공! 
					if (maze[nx][ny] != '.') continue;
					bfsJ.add(new int[] {nx, ny});
					maze[nx][ny] = 'J'; // 메모리 초과가 안 나게 하는 법!
				}
			}
		}
		if (success == true)
		System.out.println(time);
		else System.out.println("IMPOSSIBLE");
	}
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0 ,1, 0, -1};
}