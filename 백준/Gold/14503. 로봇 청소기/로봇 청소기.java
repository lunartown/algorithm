import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

//1. 북0, 동1, 남2, 서3
//2. 현재 칸이 더러우면 청소
//3. 4분면 안에 더러운 칸 있으면 -1씩 돌다가 이동
//4. 없으면 1칸 후진
//5. 후진 못하면 정지
public class Main {
    static int N, M;
    static int[][] room;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static boolean isMovable(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= M
                || room[x][y] == 1)
            return false;
        return true;
    }

    static int rotate(int dir) {
        return (dir + 3) % 4;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //방의 크기 N, M
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][];

        //로봇 청소기의 위치, 방향
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        //방 정보 입력
        //청소 전 0, 청소 후 2, 벽 1
        for (int i = 0; i < N; i++) {
            room[i] = Stream.of(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int count = 0;
        loop:
        while (true) {
            //1. 현재 칸이 더러우면 청소한다.
            if (room[r][c] == 0) {
                room[r][c] = 2;
                count++;
            }

            //2. 주위를 둘러보며 더러운 칸이 있는지 확인한다.
            for (int i = 0; i < 4; i++) {
                //반시계 회전
                d = rotate(d);
                int nr = r + dx[d];
                int nc = c + dy[d];
                if (isMovable(nr, nc) && room[nr][nc] == 0) {
                    r = nr;
                    c = nc;
                    continue loop;
                }
            }

            //3. 더러운 칸이 없다면, 1칸 후진 혹은 정지
            int nr = r - dx[d];
            int nc = c - dy[d];
            if(isMovable(nr, nc)) {
                r = nr;
                c = nc;
            } else {
                break;
            }
        }

        System.out.println(count);
    }
}