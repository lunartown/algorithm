import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//빙고
//줄마다 count 를 미리 설정하고, 5로 초기화한다
//값이 0이 되면 bingo를 카운트++한다
class Board {
    int x;
    int y;

    public Board(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Board[] board = new Board[26];

        //빙고판 입력
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                board[Integer.parseInt(st.nextToken())] = new Board(i, j);
            }
        }

        //행, 열, 대각선 초기화
        int[] row = new int[5];
        int[] col = new int[5];
        int[] diag = new int[2];
        Arrays.fill(row, 5);
        Arrays.fill(col, 5);
        Arrays.fill(diag, 5);

        //사회자 부르는 수 입력
        int[] nums = new int[26];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                nums[i * 5 + j + 1] = Integer.parseInt(st.nextToken());
            }
        }

        //빙고 맞추기
        int i = 1;
        int count = 0;
        for (i = 1; i <= 25; i++) {
            int num = nums[i];
            Board cur = board[num];
            //대각선
            if (cur.x == cur.y) {
                if (--diag[0] == 0 && ++count >= 3)
                    break;
            }
            if (cur.x + cur.y == 4) {
                if (--diag[1] == 0 && ++count >= 3)
                    break;
            }

            //행, 열
            if (--row[cur.x] == 0 && ++count >= 3)
                break;

            if (--col[cur.y] == 0 && ++count >= 3)
                break;

        }

        System.out.println(i);
    }
}