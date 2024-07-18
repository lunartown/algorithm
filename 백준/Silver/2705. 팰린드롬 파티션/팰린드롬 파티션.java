import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class Main {
    private static Map<Integer, Integer> memo = new HashMap<>();

    public static int sequence(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        int result = sequence(n - 2) + sequence(n / 2);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            sb.append(sequence(Integer.parseInt(br.readLine()))).append("\n");
        }

        System.out.println(sb);
    }
}