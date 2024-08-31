import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

//생태학
//https://www.acmicpc.net/problem/4358

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> map = new TreeMap<>();

        int total = 0;
        String tree = null;

        while ((tree = br.readLine()) != null) {
            total++;
            map.merge(tree, 1, Integer::sum);
        }

        for (String key : map.keySet()) {
            sb.append(key).append(' ').append(String.format("%.4f", (double) map.get(key) / total * 100)).append('\n');
        }

        System.out.println(sb);
    }
}