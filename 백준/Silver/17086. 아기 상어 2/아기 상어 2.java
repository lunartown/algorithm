import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//아기 상어 2
//https://www.acmicpc.net/problem/17086

public class Main {
    static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    static boolean isMovable(int x, int y, int N, int M) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<int[]> sharks = new ArrayList<>();

        int[][] map = new int[N][M];
        int[][] dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dist[i][j] = -1;
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    sharks.add(new int[]{i, j});
                    dist[i][j] = 0;
                }
            }
        }

        int answer = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int[] shark : sharks) {
            queue.add(shark);
        }

        while (!queue.isEmpty()) {
            int[] shark = queue.poll();
            int x = shark[0];
            int y = shark[1];

            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isMovable(nx, ny, N, M)) {
                    continue;
                }

                if (dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    answer = Math.max(answer, dist[nx][ny]);
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        System.out.println(answer);
    }
}