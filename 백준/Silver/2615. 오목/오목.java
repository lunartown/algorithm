import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
    private static int[][] omok = new int[19][];
    private static boolean[][] vis = new boolean[19][19];
    private static int winner = -1;
    private static int[] position = null;
    private static int[][] dir = new int[][] {
            {}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}
    };

    private static void searchOmok() {
        for(int i = 0; i < 19; i++) {
            for(int j = 0; j < 19; j++) {
                if(omok[i][j] == 0) continue;
                checkOmok(i, j, 1, 1);
                if(winner != -1) {
                    position = new int[] {i + 1, j + 1};
                    return;
                }
                checkOmok(i, j, 1, 2);
                if(winner != -1) {
                    position = new int[] {i + 1, j + 1};
                    return;
                }
                checkOmok(i, j, 1, 3);
                if(winner != -1) {
                    position = new int[] {i + 1, j + 1};
                    return;
                }
                checkOmok(i, j, 1, 4);
                if(winner != -1) {
                    position = new int[] {i + 1, j + 1};
                    return;
                }
            }
        }

        winner = 0;
    }

    private static void checkOmok(int row, int col, int length, int direction) {
        int color = omok[row][col];
        if(length >= 6) {
            winner = -1;
            return;
        }

        if(length == 5) {
            int newRow = row - dir[direction][0];
            int newCol = col - dir[direction][1];
            if(newRow >= 0 && newRow < 19 && newCol >= 0 && newCol < 19) {
                if(omok[newRow][newCol] == color) {
                    winner = -1;
                    return;
                }
            }
            winner = color;
        }

        int newRow = row + dir[direction][0] * length;
        int newCol = col + dir[direction][1] * length;
        if(newRow < 0 || newRow >= 19 || newCol < 0 || newCol >= 19) return;

        if(omok[newRow][newCol] == color) {
            checkOmok(row, col, length + 1, direction);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 19; i++) {
            omok[i] = Stream.of(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        searchOmok();

        StringBuilder sb = new StringBuilder();
        sb.append(winner);
        if(position != null) {
            sb.append('\n').append(position[0]).append(' ').append(position[1]);
        }
        System.out.println(sb);
    }
}