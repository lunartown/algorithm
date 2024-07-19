import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//별찍기
//너무 어려워보인다...
//재귀적으로 구성한다
//전부 String으로? 아니면 StringList로?
//답은 char[][]다

public class Main {
    private static char[][] tree;

    public static void buildTree(int x, int y, int N) {
        if (N == 3) {
            String smallTree ="  *    * *  ***** ";
            for(int i = 0; i < 3; i++)
                for(int j = 0; j < 6; j++) {
                    tree[x + i][y + j] = smallTree.charAt(i * 6 + j);
                }
            return;
            }

        for(int i = x; i < x + N / 2; i++) {
            for(int j = y; j < y + N / 2; j++) {
                tree[i][j] = ' ';
            }
        }

        buildTree(x, y + N / 2, N / 2);

        for(int i = x; i < x + N / 2; i++) {
            for(int j = y + N * 3 / 2; j < y + N * 2; j++) {
                tree[i][j] = ' ';
            }
        }

        buildTree(x + N / 2, y, N / 2);
        buildTree(x + N / 2, y + N, N / 2);
        }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        tree = new char[N][2 * N];

        buildTree(0, 0, N);

        sb = Arrays.stream(tree)
                .map((array) -> Stream.of(array).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append))
                        .collect(StringBuilder::new, (a, b) -> a.append(b).append("\n"), (a, b) -> a.append(b).append("\n"));

        System.out.print(sb);
    }
}