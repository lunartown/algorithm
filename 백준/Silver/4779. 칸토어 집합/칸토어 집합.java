import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class Main {
    private static Map<Integer, String> map = new HashMap<>();

    public static String Cantor (int N) {
        if(N == 0) return "-";
        if(map.containsKey(N)) return map.get(N);

        StringBuilder empty = new StringBuilder();
        for(int i = 0; i < Math.pow(3, N - 1); i++) {
            empty.append(" ");
        }

        String ans = Cantor(N - 1) + empty.toString() + Cantor(N - 1);
        map.put(N, ans);
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;
        while((input = br.readLine()) != null) {
            int N = Integer.parseInt(input);
            sb.append(Cantor(N)).append("\n");
        }
        System.out.println(sb);
    }
}