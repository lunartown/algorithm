import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

//고속도로 N개 센서, K개 집중국
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] sensors = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        if (K >= N) {
            System.out.println(0);
        } else {
            Arrays.sort(sensors);
            int[] gap = new int[N - 1];
            for (int i = 0; i < N - 1; i++) {
                gap[i] = sensors[i + 1] - sensors[i];
            }

            Arrays.sort(gap);
            System.out.println(Arrays.stream(Arrays.copyOfRange(gap, 0, N - K)).sum());
        }
    }
}