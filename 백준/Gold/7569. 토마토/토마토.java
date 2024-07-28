import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//토마토 익히기
//bfs로 푼다
public class Main {
    static int M;
    static int N;
    static int H;
    static int[] dx = {0, 1, 0, -1, 0, 0};
    static int[] dy = {1, 0, -1, 0, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};

    static boolean isMovable(int z, int x, int y) {
        if (x < 0 || y < 0 || z < 0 || x >= N || y >= M || z >= H) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        //가로, 세로, 높이
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        int[][][] tomatos = new int[H][N][M];
        Queue<int[]> queue = new ArrayDeque<>();
        int unriped = 0;

        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    int status = Integer.parseInt(st.nextToken());
                    tomatos[k][i][j] = status;
                    if (status == 1)
                        queue.add(new int[]{k, i, j});
                    else if (status == 0)
                        unriped++;
                }
            }
        }

        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] tomato = queue.poll();
                for (int dir = 0; dir < 6; dir++) {
                    int nz = tomato[0] + dz[dir];
                    int nx = tomato[1] + dx[dir];
                    int ny = tomato[2] + dy[dir];

                    if (!isMovable(nz, nx, ny)) continue;
                    if (tomatos[nz][nx][ny] == 0) {
                        tomatos[nz][nx][ny] = 1;
                        unriped--;
                        queue.offer(new int[]{nz, nx, ny});
                    }
                }
            }
            time++;
        }

        if(unriped == 0)
            System.out.println(time - 1);
        else
            System.out.println(-1);
    }
}