import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static int[][] sudoku = new int[9][];
    static int[] numbers;

    static void solve(int row, int col) {
        if(col == 9) {
            solve(row + 1, 0);
            return;
        }

        if(row == 9) {
            StringBuilder sb = new StringBuilder();
            Arrays.stream(sudoku).forEach(sudokuRow -> {
                Arrays.stream(sudokuRow).forEach(number -> sb.append(number).append(' '));
                sb.append('\n');
            });
            System.out.println(sb);
            System.exit(0);
        }

        if(sudoku[row][col] == 0) {
            numbers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
            checkRow(row);
            checkCol(col);
            checkSquare(row, col);

            for(int number : numbers) {
                if(number == 0) continue;
                sudoku[row][col] = number;
                solve(row, col + 1);
                sudoku[row][col] = 0;
            }

            return;
        }

        solve(row, col + 1);
    }

    static void checkRow (int row) {
        Arrays.stream(sudoku[row]).forEach(number -> numbers[number] = 0);
    }

    static void checkCol (int col) {
        for(int i = 0; i < 9; i++) {
            numbers[sudoku[i][col]] = 0;
        }
    }

    static void checkSquare(int row, int col) {
        for(int i = (row / 3) * 3; i < (row / 3) * 3 + 3; i++) {
            for(int j = (col / 3) * 3; j < (col / 3) * 3 + 3; j++) {
                numbers[sudoku[i][j]] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 9; i++){
            sudoku[i] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        solve(0, 0);

        System.out.println(sb);
    }
}