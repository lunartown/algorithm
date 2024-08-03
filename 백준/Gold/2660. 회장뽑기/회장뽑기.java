import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] members = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(members[i], 10_000_000);
            members[i][i] = 0;
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1)
                break;
            members[a][b] = 1;
            members[b][a] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (members[i][j] > members[i][k] + members[k][j])
                        members[i][j] = members[i][k] + members[k][j];
                }
            }
        }

        int[] maxInRows = IntStream.range(1, N + 1)
                .map(i -> IntStream.range(1, N + 1)
                        .map(j -> members[i][j])
                        .max()
                        .orElseThrow())
                .toArray();

        int minScore = Arrays.stream(maxInRows).min().orElseThrow();

        List<Integer> candidates = IntStream.range(1, N + 1)
                .filter(i -> maxInRows[i - 1] == minScore)
                .boxed()
                .collect(Collectors.toList());

        sb.append(minScore).append(" ").append(candidates.size()).append("\n");
        candidates.forEach(i -> sb.append(i).append(" "));
        System.out.println(sb);

    }
}