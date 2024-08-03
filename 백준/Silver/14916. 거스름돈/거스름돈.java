import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int quotient = N / 5;
        int remainder = N % 5;
        if (remainder % 2 == 0) {
            System.out.println(quotient + remainder / 2);

        } else {
            if (quotient == 0) {
                System.out.println(-1);
            } else {
                System.out.println((quotient - 1) + (remainder + 5) / 2);
            }
        }
    }
}