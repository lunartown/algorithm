import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//파도반 수열
//1, 1, 1, 2, 2, 3, 4, 5, 7, 9
//P(N) = P(N - 1) + P(N - 5)
public class Main {
    static long[] sequence = new long[101];

    static long getP(int N) {
        if (sequence[N] == 0)
            sequence[N] = getP(N - 1) + getP(N - 5);
        return sequence[N];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        sequence[0] = 0;
        sequence[1] = 1;
        sequence[2] = 1;
        sequence[3] = 1;
        sequence[4] = 2;
        sequence[5] = 2;


        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            sb.append(getP(N)).append('\n');
        }
        System.out.println(sb);
    }
}