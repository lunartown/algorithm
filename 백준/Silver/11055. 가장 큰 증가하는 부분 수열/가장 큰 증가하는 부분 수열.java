import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

//합이 가장 큰 증가하는 부분수열
//dp
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] sum = new int[N];
        for (int i = 0; i < N; i++) {
            sum[i] = arr[i];
            for(int j = 0; j < i; j++) {
                if(arr[j] < arr[i] && sum[j] + arr[i] > sum[i]) {
                    sum[i] = sum[j] + arr[i];
                }
            }
        }

        System.out.println(Arrays.stream(sum).max().getAsInt());
    }
}