import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
    static boolean bubbleSort(int[] arr, int K) {
        int N = arr.length;

        loop:
        for (int last = N; last >= 2; last--) {
            for (int i = 0; i < last - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    K--;
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    if (K == 0)
                        return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        if (bubbleSort(arr, K)) {
            System.out.println(-1);
        } else {
            Arrays.stream(arr).forEach(i -> sb.append(i).append(" "));
            System.out.println(sb);
        }
    }
}