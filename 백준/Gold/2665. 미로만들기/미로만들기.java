import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

//0-1bfs
class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N;
    static final int[] dx = new int[]{0, 1, 0, -1};
    static final int[] dy = new int[]{1, 0, -1, 0};

    static boolean isMovable(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[][] room = new int[N][];
        for (int i = 0; i < N; i++) {
            room[i] = br.readLine().chars().map(c -> (c - '0') ^ 1).toArray();
        }

        int[][] cost = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cost[i][j] = 1_000;
            }
        }

        Deque<Node> deque = new ArrayDeque<>();
        deque.push(new Node(0, 0));
        cost[0][0] = 0;

        while (!deque.isEmpty()) {
            Node cur = deque.pollFirst();
            int x = cur.x;
            int y = cur.y;
            int c = cost[x][y];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!isMovable(nx, ny))
                    continue;
                int nextC = c + room[nx][ny];
                if (nextC >= cost[nx][ny])
                    continue;
                cost[nx][ny] = nextC;
                if (room[nx][ny] == 1) {
                    deque.offerFirst(new Node(nx, ny));
                } else {
                    deque.offerLast(new Node(nx, ny));
                }
            }
        }

//        String result = Arrays.stream(cost)
//                .map((row) -> Arrays.stream(row)
//                        .mapToObj(String::valueOf)
//                        .collect(Collectors.joining(" ")))
//                .collect(Collectors.joining("\n"));
//
//        System.out.println(result);
        System.out.println(cost[N - 1][N - 1]);
    }
}