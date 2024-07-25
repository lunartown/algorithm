import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            String[] clothes = new String[n];
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                clothes[i] = st.nextToken();
            }
            HashMap <String, Long> map = Stream.of(clothes)
                    .collect(Collectors.groupingBy(
                            i -> i, HashMap::new, Collectors.counting()
                    ));

            int cases = 1;
            for(String cloth : map.keySet()) {
                cases *= map.get(cloth) + 1;
            }
            sb.append(--cases).append('\n');
        }
        System.out.println(sb);
    }
}