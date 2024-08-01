import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            char[] arr = br.readLine().toCharArray();
            StringBuilder temp = new StringBuilder(String.valueOf(arr[0]));
            for (int i = 1; i < N; i++) {
                char c = arr[i * 2];
                if (c > temp.charAt(0)) {
                    temp.append(c);
                } else {
                    temp.reverse();
                    temp.append(c);
                    temp.reverse();
                }
            }
            sb.append(temp).append("\n");
        }
        System.out.println(sb);
    }
}