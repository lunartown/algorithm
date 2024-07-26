import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//나이트 이동 수
//bfs
public class Main {
    private static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
    private static int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int I = Integer.parseInt(br.readLine());
            boolean[][] vis = new boolean[I][I];

            //출발, 도착
            st = new StringTokenizer(br.readLine());
            int[] start = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            st = new StringTokenizer(br.readLine());
            int[] end = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

            Queue<int[]> queue = new ArrayDeque<>();
            queue.offer(start);
            vis[start[0]][start[1]] = true;

            int times = 0;
            loop:
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int[] cur = queue.poll();
                    int x = cur[0];
                    int y = cur[1];
                    if (x == end[0] && y == end[1])
                        break loop;
                    for (int dir = 0; dir < 8; dir++) {
                        int nx = x + dx[dir];
                        int ny = y + dy[dir];
                        if (nx < 0 || ny < 0 || nx >= I || ny >= I || vis[nx][ny])
                            continue;
                        vis[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
                times++;
            }
            sb.append(times).append('\n');
        }
        System.out.println(sb);
    }
}