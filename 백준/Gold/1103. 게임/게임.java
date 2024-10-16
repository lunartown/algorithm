import java.io.*;
import java.util.*;

public class Main {
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    static int N, M;
    static char[][] board;

    static boolean isMovable(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M
                && !(board[x][y] == 'H');
    }

    static int toFlag(int x, int y) {
        return x * N + y;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][];
        for(int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        List<Edge> edges = new ArrayList<>();

        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(0, 0, 0));
        boolean[][] vis = new boolean[N][M];
        vis[0][0] = true;
        int max = 0;
        Set<Integer> nodes = new HashSet<>(Arrays.asList(0));

        loop:while(!queue.isEmpty()) {
            Point coin = queue.poll();
            int num = board[coin.x][coin.y] - '0';
            boolean isEnd = true;
            for(int i = 0; i < 4; i++) {
                int nx = coin.x + dx[i] * num;
                int ny = coin.y + dy[i] * num;
                if(!isMovable(nx, ny)) continue;

                nodes.add(nx * M + ny);
                edges.add(new Edge(coin.x, coin.y, nx, ny));
                if(vis[nx][ny]) {
                    max = Math.max(max, coin.dis + 1);
                    continue;
                }
                vis[nx][ny] = true;
                isEnd = false;
                queue.offer(new Point(nx, ny, coin.dis + 1));
            }

            if(isEnd) {
                max = Math.max(max, coin.dis + 1);
            }
        }

        boolean isLoop = false;

        int[][] dis = new int[N][M];
        for(int[] row : dis) {
            Arrays.fill(row, 1_000_007);
        }
        dis[0][0] = 0;

        for(int i = 0; i <= nodes.size(); i++) {
            for (Edge edge : edges) {
                if (dis[edge.x2][edge.y2] > dis[edge.x1][edge.y1] - 1) {
                    dis[edge.x2][edge.y2] = dis[edge.x1][edge.y1] - 1;
                    if (i == nodes.size()) {
                        isLoop = true;
                    }
                }
            }
        }

        max = 0;
        for(int num : nodes) {
            int x = num / M;
            int y = num % M;
            max = Math.min(max, dis[x][y]);
        }

        System.out.println(isLoop ? -1 : -max + 1);
    }
}

class Point {
    int x;
    int y;
    int dis;
    Point(int x, int y, int dis) {
        this.x = x;
        this.y = y;
        this.dis = dis;
    }
}

class Edge {
    int x1, y1, x2, y2;
    Edge(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public String toString() {
        return x1 + " " + y1 + " " + x2 + " " + y2;
    }
}