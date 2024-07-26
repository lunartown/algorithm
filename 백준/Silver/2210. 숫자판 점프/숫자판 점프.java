import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

//5x5 판을 돌아다니며, 6자리 숫자 만들기
//서로 다른 수 구하기
//그냥 다 돌면 되는 거 아녀?
public class Main {
    private static int[] dx = {0, -1, 0, 1};
    private static int[] dy = {1, 0, -1, 0};

    private static void makeNumber(int[][] board, Set<Integer> set, int x, int y, int length, int number) {
        //범위 벗어나면 탈락
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length)
            return;

        //길이 6 달성하면 성공
        if(length >= 6) {
            set.add(number);
            return;
        }

        number = number * 10 + board[x][y];

        for(int dir = 0; dir < 4; dir++) {
            makeNumber(board, set, x + dx[dir], y + dy[dir], length + 1, number);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[5][];
        for (int i = 0; i < 5; i++) {
            board[i] = Stream.of(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        //숫자를 받을 수 있는 set을 만들자
        Set<Integer> set = new HashSet<>();

        //배열을 순회하며 6자리 수를 만들자
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                makeNumber(board, set, x, y, 0, 0);
            }
        }

        System.out.println(set.size());
    }
}