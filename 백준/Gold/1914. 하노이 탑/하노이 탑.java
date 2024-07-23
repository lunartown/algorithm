import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    private static StringBuilder sb = new StringBuilder();

    private static void hanoi (int start, int temp, int dest, int num) {
        if(num == 0) return;

        hanoi(start, dest, temp, num - 1);
        sb.append(start).append(' ').append(dest).append('\n');
        hanoi(temp, start, dest, num - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        BigInteger K = new BigInteger("2");
        K = K.pow(N).subtract(new BigInteger("1"));
        sb.append(K).append('\n');
        if(N <= 20) {
            hanoi(1, 2, 3, N);
        }
        System.out.println(sb);
    }
}