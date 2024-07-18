import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static StringBuilder Cantor (int N) {
        if(N == 0) return new StringBuilder("-");
        StringBuilder empty = new StringBuilder();
        for(int i = 0; i < Math.pow(3, N - 1); i++) {
            empty.append(" ");
        }
        return Cantor(N - 1).append(empty).append(Cantor(N - 1));
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