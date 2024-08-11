import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//마법사 상어와 토네이도
//진짜 귀찮다
public class Main {
    static int N;
    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {-1, 0, 1, 0};
    //모래가 퍼지는 상대적인 방향 : dir 기준, (dir+1)%4 기준
    static final int[] dirtX = {-1, -1, 0, 0, 2, 0, 0, 1, 1};
    static final int[] dirtY = {1, -1, 2, -2, 0, 1, -1, 1, -1};
    //모래가 퍼지는 비율
    static final int[] percent = {1, 1, 2, 2, 5, 7, 7, 10, 10};

    static boolean isMovable(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //시작점
        int x = N / 2;
        int y = N / 2;
        int dir = 0;
        int turn = 0;
        int length = 1;
        //밖으로 나간 모래
        int out = 0;
        lable:
        while (true) {
            //이동
            for (int i = 0; i < length; i++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (!isMovable(nx, ny)) break lable;

                //모래 이동
                int sand = map[nx][ny];
                map[nx][ny] = 0;
                int sum = 0;

                for (int j = 0; j < 9; j++) {
                    int tx = nx + dx[dir] * dirtX[j] + dx[(dir + 1) % 4] * dirtY[j];
                    int ty = ny + dy[dir] * dirtX[j] + dy[(dir + 1) % 4] * dirtY[j];
                    int move = sand * percent[j] / 100;
                    sum += move;
                    if (!isMovable(tx, ty)) {
                        out += move;
                    } else {
                        map[tx][ty] += move;
                    }
                }

                //남은 모래
                int tx = nx + dx[dir];
                int ty = ny + dy[dir];
                if (!isMovable(tx, ty)) {
                    out += sand - sum;
                } else {
                    map[tx][ty] += sand - sum;
                }

                //이동
                x = nx;
                y = ny;
            }

            //방향 전환
            dir = (dir + 1) % 4;
            turn++;
            if (turn % 2 == 0) {
                length++;
            }
        }

        System.out.println(out);
    }
}