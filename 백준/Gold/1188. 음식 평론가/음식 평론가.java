import java.io.*;
import java.util.*;

public class Main {
    static int cut(int a, int b) {
        if(a % b == 0) return 0;

        if(a > b) {
            return cut(a % b, b);
        }

        if(b % a == 0) {
            return ((b / a) - 1) * a;
        }

        int q = b / a;
        int r = b % a;

        return (a * q) + cut(a, r);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        System.out.println(cut(N, M));
    }
}