import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] weight = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(weight);

        if (weight[0] != 1) {
            System.out.println(1);
        } else {
            int[] sum = new int[N];
            sum[0] = weight[0];
            for (int i = 1; i < N; i++) {
                sum[i] = sum[i - 1] + weight[i];
            }

            int i = 0;
            for (i = 0; i < N - 1; i++) {
                if (weight[i + 1] > sum[i] + 1)
                    break;
            }

            System.out.println(sum[i] + 1);
        }
    }
}