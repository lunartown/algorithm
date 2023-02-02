import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

 class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        Queue<Integer[]> bfs = new LinkedList<>();
        Integer[] cur = new Integer[]{0, 0, 1};

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] maze = new int[N][M];
        boolean [][] vis = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            String str = in.readLine();
            for(int j = 0; j < M; j++) {
                maze[i][j] = str.charAt(j) - '0';
            }
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
            }
        }
        
        bfs.add(cur);
        vis[0][0] = true;

        while(!bfs.isEmpty()) {
        	cur = bfs.poll();
        	if(cur[0].equals(N-1) && cur[1].equals(M-1)) break;
        	
            for(int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(maze[nx][ny] != 1 || vis[nx][ny]) continue;
            	vis[nx][ny] = true;
                bfs.add(new Integer[] {nx, ny, cur[2]+1});
            }
        }

        System.out.println(cur[2]);


    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0 ,1, 0, -1};
}

